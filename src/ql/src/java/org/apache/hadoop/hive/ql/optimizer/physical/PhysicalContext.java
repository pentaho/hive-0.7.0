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


package org.apache.hadoop.hive.ql.optimizer.physical;

import java.io.Serializable;
import java.util.List;

import org.apache.hadoop.hive.conf.HiveConf;
import org.apache.hadoop.hive.ql.Context;
import org.apache.hadoop.hive.ql.exec.Task;
import org.apache.hadoop.hive.ql.parse.ParseContext;

/**
 * physical context used by physical resolvers.
 */
public class PhysicalContext {

  protected HiveConf conf;
  private ParseContext parseContext;
  private Context context;
  protected List<Task<? extends Serializable>> rootTasks;
  protected Task<? extends Serializable> fetchTask;

  public PhysicalContext(HiveConf conf, ParseContext parseContext,
      Context context, List<Task<? extends Serializable>> rootTasks,
      Task<? extends Serializable> fetchTask) {
    super();
    this.conf = conf;
    this.parseContext = parseContext;
    this.context = context;
    this.rootTasks = rootTasks;
    this.fetchTask = fetchTask;
  }

  public HiveConf getConf() {
    return conf;
  }

  public void setConf(HiveConf conf) {
    this.conf = conf;
  }

  public ParseContext getParseContext() {
    return parseContext;
  }

  public void setParseContext(ParseContext parseContext) {
    this.parseContext = parseContext;
  }

  public Context getContext() {
    return context;
  }

  public void setContext(Context context) {
    this.context = context;
  }

  public void addToRootTask(Task<? extends Serializable> tsk){
    rootTasks.add(tsk);
  }
  public void removeFromRootTask(Task<? extends Serializable> tsk){
    rootTasks.remove(tsk);
  }

}
