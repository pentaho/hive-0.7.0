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


package org.apache.hadoop.hive.ql;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.Queue;

import org.apache.hadoop.hive.ql.exec.Task;
import org.apache.hadoop.mapred.JobConf;

/**
 * DriverContext.
 *
 */
public class DriverContext {

  Queue<Task<? extends Serializable>> runnable = new LinkedList<Task<? extends Serializable>>();

  // how many jobs have been started
  int curJobNo;

  Context ctx;

  public DriverContext() {
    this.runnable = null;
    this.ctx = null;
  }

  public DriverContext(Queue<Task<? extends Serializable>> runnable, Context ctx) {
    this.runnable = runnable;
    this.ctx = ctx;
  }

  public Queue<Task<? extends Serializable>> getRunnable() {
    return runnable;
  }

  /**
   * Checks if a task can be launched.
   *
   * @param tsk
   *          the task to be checked
   * @return true if the task is launchable, false otherwise
   */

  public static boolean isLaunchable(Task<? extends Serializable> tsk) {
    // A launchable task is one that hasn't been queued, hasn't been
    // initialized, and is runnable.
    return !tsk.getQueued() && !tsk.getInitialized() && tsk.isRunnable();
  }

  public void addToRunnable(Task<? extends Serializable> tsk) {
    runnable.add(tsk);
    tsk.setQueued();
  }

  public int getCurJobNo() {
    return curJobNo;
  }

  public Context getCtx() {
    return ctx;
  }

  public void incCurJobNo(int amount) {
    this.curJobNo = this.curJobNo + amount;
  }
  
}
