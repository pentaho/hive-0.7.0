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


package org.apache.hadoop.hive.ql.metadata;

/**
 * Generic exception class for Hive.
 */

public class HiveException extends Exception {
  public HiveException() {
    super();
  }

  public HiveException(String message) {
    super(message);
  }

  public HiveException(Throwable cause) {
    super(cause);
  }

  public HiveException(String message, Throwable cause) {
    super(message, cause);
  }
}
