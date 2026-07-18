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
 * TaskResult implementation.
 **/

public class TaskResult {
  protected int exitVal;
  protected boolean runStatus;

  public TaskResult() {
    exitVal = -1;
    setRunning(true);
  }

  public void setExitVal(int exitVal) {
    this.exitVal = exitVal;
    setRunning(false);
  }

  public int getExitVal() {
    return exitVal;
  }

  public boolean isRunning() {
    return runStatus;
  }

  public void setRunning(boolean val) {
    runStatus = val;
  }
}
