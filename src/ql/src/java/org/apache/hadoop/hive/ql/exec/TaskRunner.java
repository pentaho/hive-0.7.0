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

import java.io.Serializable;

import org.apache.hadoop.hive.ql.session.SessionState;

/**
 * TaskRunner implementation.
 **/

public class TaskRunner extends Thread {
  protected Task<? extends Serializable> tsk;
  protected TaskResult result;
  protected SessionState ss;

  public TaskRunner(Task<? extends Serializable> tsk, TaskResult result) {
    this.tsk = tsk;
    this.result = result;
    ss = SessionState.get();
  }

  public Task<? extends Serializable> getTask() {
    return tsk;
  }

  @Override
  public void run() {
    SessionState.start(ss);
    runSequential();
  }

  /**
   * Launches a task, and sets its exit value in the result variable.
   */

  public void runSequential() {
    int exitVal = -101;
    try {
      exitVal = tsk.executeTask();
    } catch (Throwable t) {
      t.printStackTrace();
    }
    result.setExitVal(exitVal);
  }

}
