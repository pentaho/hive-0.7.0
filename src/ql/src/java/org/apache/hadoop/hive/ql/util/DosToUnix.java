/*! ******************************************************************************
 *
 * Pentaho
 *
 * Copyright (C) 2010 - 2026 by Pentaho Canada Inc. : http://www.pentaho.com
 *
 * Use of this software is governed by the Business Source License included
 * in the LICENSE.TXT file.
 *
 * Change Date: 2030-06-15
 ******************************************************************************/


package org.apache.hadoop.hive.ql.util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.InputStreamReader;

public class DosToUnix {

  public static String convertWindowsScriptToUnix(File windowsScriptFile) throws Exception {
    String windowsScriptFilename = windowsScriptFile.getName();
    String unixScriptFilename = getUnixScriptNameFor(windowsScriptFilename);
    File unixScriptFile = null;
    if (windowsScriptFile.getParent() != null) {
      unixScriptFile = new File(windowsScriptFile.getParent() + "/" + unixScriptFilename);
    } else {
      unixScriptFile = new File(unixScriptFilename);
    }
    BufferedWriter writer = new BufferedWriter(new FileWriter(unixScriptFile));
    try {
      BufferedReader reader = new BufferedReader(new FileReader(windowsScriptFile));
      try {
        int prev = reader.read();
        int next = reader.read();
        while( prev != -1 ) {
          if ( prev != -1 && ( prev != '\r' || next != '\n' ) ) {
            writer.write(prev);
          }
          prev = next;
          next = reader.read();
        }
      }
      finally {
        reader.close();
      }
    }
    finally {
      writer.close();
    }
    unixScriptFile.setExecutable(true);
    return unixScriptFile.getAbsolutePath();
  }

  public static String getUnixScriptNameFor(String windowsScriptFilename) {
    int pos = windowsScriptFilename.indexOf(".");
    String unixScriptFilename;
    if ( pos >= 0 ) {
      unixScriptFilename = windowsScriptFilename.substring(0, pos) + "_unix" + windowsScriptFilename.substring(pos);
    }
    else {
      unixScriptFilename = windowsScriptFilename + "_unix";
    }
    return unixScriptFilename;
  }

  public static boolean isWindowsScript(File file) {
    try {
      BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
      char[] buffer = new char[4096];
      int readLength = reader.read(buffer);
      if (readLength >= 2 && buffer[0] == '#' && buffer[1] == '!') {
        for(int i=2; i<readLength; ++i) {
          switch(buffer[i]) {
          case '\r':
            return true;
          case '\n':
            return false;
          }
        }
      }
    } catch (Exception e) {
      // ignore error reading file, if we can't read it then it isn't a windows script
    }
    return false;
  }

}
