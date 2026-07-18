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


package org.apache.hadoop.hive.ql.lockmgr;

import org.apache.hadoop.hive.ql.metadata.HiveException;

/**
 * Exception from lock manager.
 */

public class LockException extends HiveException {

  private static final long serialVersionUID = 1L;

  public LockException() {
    super();
  }

  public LockException(String message) {
    super(message);
  }

  public LockException(Throwable cause) {
    super(cause);
  }

  public LockException(String message, Throwable cause) {
    super(message, cause);
  }

}
