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


package org.apache.hadoop.hive.ql.hooks;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.apache.hadoop.hive.conf.HiveConf;
import org.apache.hadoop.hive.ql.QueryPlan;
import org.apache.hadoop.hive.ql.exec.TaskRunner;
import org.apache.hadoop.hive.ql.session.SessionState;
import org.apache.hadoop.hive.shims.ShimLoader;
import org.apache.hadoop.security.UserGroupInformation;
/**
 * Hook Context keeps all the necessary information for all the hooks.
 * New implemented hook can get the query plan, job conf and the list of all completed tasks from this hook context
 */
public class HookContext {
  private QueryPlan queryPlan;
  private HiveConf conf;
  private List<TaskRunner> completeTaskList;
  private Set<ReadEntity> inputs;
  private Set<WriteEntity> outputs;
  private LineageInfo linfo;
  private UserGroupInformation ugi;


  public HookContext(QueryPlan queryPlan, HiveConf conf) throws Exception{
    this.queryPlan = queryPlan;
    this.conf = conf;
    completeTaskList = new ArrayList<TaskRunner>();
    inputs = queryPlan.getInputs();
    outputs = queryPlan.getOutputs();
    ugi = ShimLoader.getHadoopShims().getUGIForConf(conf);
    linfo= null;
    if(SessionState.get() != null){
      linfo = SessionState.get().getLineageState().getLineageInfo();
    }
  }

  public QueryPlan getQueryPlan() {
    return queryPlan;
  }

  public void setQueryPlan(QueryPlan queryPlan) {
    this.queryPlan = queryPlan;
  }

  public HiveConf getConf() {
    return conf;
  }

  public void setConf(HiveConf conf) {
    this.conf = conf;
  }

  public List<TaskRunner> getCompleteTaskList() {
    return completeTaskList;
  }

  public void setCompleteTaskList(List<TaskRunner> completeTaskList) {
    this.completeTaskList = completeTaskList;
  }

  public void addCompleteTask(TaskRunner completeTaskRunner) {
    completeTaskList.add(completeTaskRunner);
  }

  public Set<ReadEntity> getInputs() {
    return inputs;
  }

  public void setInputs(Set<ReadEntity> inputs) {
    this.inputs = inputs;
  }

  public Set<WriteEntity> getOutputs() {
    return outputs;
  }

  public void setOutputs(Set<WriteEntity> outputs) {
    this.outputs = outputs;
  }

  public LineageInfo getLinfo() {
    return linfo;
  }

  public void setLinfo(LineageInfo linfo) {
    this.linfo = linfo;
  }

  public UserGroupInformation getUgi() {
    return ugi;
  }

  public void setUgi(UserGroupInformation ugi) {
    this.ugi = ugi;
  }


}
