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

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.Writer;

import junit.framework.TestCase;

public class TestDosToUnix extends TestCase {

  private static final String dataFile = System.getProperty("test.data.dir", ".") + "data_TestDosToUnix";
  @Override
  protected void setUp() throws Exception {
    super.setUp();
    Writer output = null;
    String text = "#!/usr/bin/env ruby \r\n Test date \r\n More test data.\r\n";
    File file = new File(dataFile);
    output = new BufferedWriter(new FileWriter(file));
    output.write(text);
    output.close();
  }

  @Override
  protected void tearDown() throws Exception {
    super.tearDown();
    File f = new File(dataFile);
    if(!f.delete()) {
      throw new RuntimeException("Could not delete the data file");
    }
  }

  public void testIsWindowsScript() {
    File file = new File(dataFile);
    assertEquals(true, DosToUnix.isWindowsScript(file));
  }

  public void testGetUnixScriptNameFor() {
    assertEquals("test_unix", DosToUnix.getUnixScriptNameFor("test"));
    assertEquals("test_unix.rb", DosToUnix.getUnixScriptNameFor("test.rb"));
  }

  public void testConvertWindowsScriptToUnix() {
    File file = new File(dataFile);
    try {
      assertEquals(true, DosToUnix.isWindowsScript(file));
      String convertedFile = DosToUnix.convertWindowsScriptToUnix(file);
      File cFile = new File(convertedFile);
      assertEquals(false, DosToUnix.isWindowsScript(cFile));
      if(!cFile.delete()) {
        throw new RuntimeException("Could not delete the converted data file");
      }
    } catch (Exception e) {
      e.printStackTrace();
      fail();
    }

  }
}
