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


package org.apache.hadoop.hive.ql.parse;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.apache.hadoop.fs.Path;
import org.apache.hadoop.hive.conf.HiveConf;
import org.apache.hadoop.hive.ql.exec.Task;
import org.apache.hadoop.hive.ql.exec.TaskFactory;
import org.apache.hadoop.hive.ql.plan.ExplainWork;

/**
 * ExplainSemanticAnalyzer.
 *
 */
public class ExplainSemanticAnalyzer extends BaseSemanticAnalyzer {

  public ExplainSemanticAnalyzer(HiveConf conf) throws SemanticException {
    super(conf);
  }

  @Override
  public void analyzeInternal(ASTNode ast) throws SemanticException {
    ctx.setExplain(true);

    // Create a semantic analyzer for the query
    BaseSemanticAnalyzer sem = SemanticAnalyzerFactory.get(conf, (ASTNode) ast
        .getChild(0));
    sem.analyze((ASTNode) ast.getChild(0), ctx);

    boolean extended = false;
    if (ast.getChildCount() > 1) {
      extended = true;
    }

    ctx.setResFile(new Path(ctx.getLocalTmpFileURI()));
    List<Task<? extends Serializable>> tasks = sem.getRootTasks();
    Task<? extends Serializable> fetchTask = sem.getFetchTask();
    if (tasks == null) {
      if (fetchTask != null) {
        tasks = new ArrayList<Task<? extends Serializable>>();
        tasks.add(fetchTask);
      }
    } else if (fetchTask != null) {
      tasks.add(fetchTask);
    }

    rootTasks.add(TaskFactory.get(new ExplainWork(ctx.getResFile().toString(),
        tasks, ((ASTNode) ast.getChild(0)).toStringTree(), extended), conf));
  }
}
