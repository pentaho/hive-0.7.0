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


package org.apache.hadoop.hive.serde2;

/**
 * Generic exception class for SerDes.
 * 
 */

public class SerDeException extends Exception {
  private static final long serialVersionUID = 1L;

  public SerDeException() {
    super();
  }

  public SerDeException(String message) {
    super(message);
  }

  public SerDeException(Throwable cause) {
    super(cause);
  }

  public SerDeException(String message, Throwable cause) {
    super(message, cause);
  }
}
