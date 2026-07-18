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


package org.apache.hadoop.hive.metastore;


public class ProtectMode {
  public static String PARAMETER_NAME = "PROTECT_MODE";

  public static String FLAG_OFFLINE = "OFFLINE";
  public static String FLAG_NO_DROP = "NO_DROP";
  public static String FLAG_READ_ONLY = "READ_ONLY";

  public boolean offline = false;
  public boolean readOnly = false;
  public boolean noDrop = false;

  static public ProtectMode getProtectModeFromString(String sourceString) {
    return new ProtectMode(sourceString);
  }

  private ProtectMode(String sourceString) {
    String[] tokens = sourceString.split(",");
    for (String token: tokens) {
      if (token.equalsIgnoreCase(FLAG_OFFLINE)) {
        offline = true;
      } else if (token.equalsIgnoreCase(FLAG_NO_DROP)) {
        noDrop = true;
      } else if (token.equalsIgnoreCase(FLAG_READ_ONLY)) {
        readOnly = true;
      }
    }
  }

  public ProtectMode() {
  }

  @Override
  public String toString() {
    String retString = null;

    if (offline) {
        retString = FLAG_OFFLINE;
    }

    if (noDrop) {
      if (retString != null) {
        retString = retString + "," + FLAG_NO_DROP;
      }
      else
      {
        retString = FLAG_NO_DROP;
      }
    }

    if (readOnly) {
      if (retString != null) {
        retString = retString + "," + FLAG_READ_ONLY;
      }
      else
      {
        retString = FLAG_READ_ONLY;
      }
    }

    return retString;
  }
}
