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


package org.apache.hadoop.hive.ql.exec;

/**
 * Exception thrown by any task processor to signal a problem with task
 * execution.
 */
public class TaskExecutionException extends RuntimeException {

  private static final long serialVersionUID = 1L;

  public TaskExecutionException(String msg) {
    super(msg);
  }

  public TaskExecutionException(String msg, Throwable cause) {
    super(msg, cause);
  }

  public TaskExecutionException(Throwable cause) {
    super(cause);
  }

}
