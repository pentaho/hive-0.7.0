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


package org.apache.hadoop.hive.ql.plan;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.apache.hadoop.hive.ql.exec.Task;

/**
 * ExplainWork.
 *
 */
public class ExplainWork implements Serializable {
  private static final long serialVersionUID = 1L;

  private String resFile;
  private ArrayList<Task<? extends Serializable>> rootTasks;
  private String astStringTree;
  boolean extended;

  public ExplainWork() {
  }

  public ExplainWork(String resFile,
      List<Task<? extends Serializable>> rootTasks, String astStringTree,
      boolean extended) {
    this.resFile = resFile;
    this.rootTasks = new ArrayList<Task<? extends Serializable>>(rootTasks);
    this.astStringTree = astStringTree;
    this.extended = extended;
  }

  public String getResFile() {
    return resFile;
  }

  public void setResFile(String resFile) {
    this.resFile = resFile;
  }

  public ArrayList<Task<? extends Serializable>> getRootTasks() {
    return rootTasks;
  }

  public void setRootTasks(ArrayList<Task<? extends Serializable>> rootTasks) {
    this.rootTasks = rootTasks;
  }

  public String getAstStringTree() {
    return astStringTree;
  }

  public void setAstStringTree(String astStringTree) {
    this.astStringTree = astStringTree;
  }

  public boolean getExtended() {
    return extended;
  }

  public void setExtended(boolean extended) {
    this.extended = extended;
  }
}
