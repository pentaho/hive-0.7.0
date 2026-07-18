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


package org.apache.hadoop.hive.ql.metadata;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.hadoop.hive.ql.exec.DDLTask;
import org.apache.hadoop.hive.ql.exec.Task;
import org.apache.hadoop.hive.ql.parse.ASTNode;
import org.apache.hadoop.hive.ql.parse.AbstractSemanticAnalyzerHook;
import org.apache.hadoop.hive.ql.parse.HiveParser;
import org.apache.hadoop.hive.ql.parse.HiveSemanticAnalyzerHookContext;
import org.apache.hadoop.hive.ql.parse.SemanticException;
import org.apache.hadoop.hive.ql.plan.CreateTableDesc;

public class DummySemanticAnalyzerHook extends AbstractSemanticAnalyzerHook{

  private AbstractSemanticAnalyzerHook hook;

  @Override
  public ASTNode preAnalyze(HiveSemanticAnalyzerHookContext context, ASTNode ast)
  throws SemanticException {

    switch (ast.getToken().getType()) {

    case HiveParser.TOK_CREATETABLE:
      hook = new DummyCreateTableHook();
      return hook.preAnalyze(context, ast);

    case HiveParser.TOK_DROPTABLE:
    case HiveParser.TOK_DESCTABLE:
      return ast;

    default:
      throw new SemanticException("Operation not supported.");
    }
  }

  public DummySemanticAnalyzerHook() {

  }

  @Override
  public void postAnalyze(HiveSemanticAnalyzerHookContext context,
      List<Task<? extends Serializable>> rootTasks) throws SemanticException {

    if(hook != null) {
      hook.postAnalyze(context, rootTasks);
    }
  }
}

class DummyCreateTableHook extends AbstractSemanticAnalyzerHook{

  @Override
  public ASTNode preAnalyze(HiveSemanticAnalyzerHookContext context, ASTNode ast)
  throws SemanticException {

    int numCh = ast.getChildCount();

    for (int num = 1; num < numCh; num++) {
      ASTNode child = (ASTNode) ast.getChild(num);

      switch (child.getToken().getType()) {

      case HiveParser.TOK_QUERY:
        throw new SemanticException("CTAS not supported.");
      }
    }
    return ast;
  }

  @Override
  public void postAnalyze(HiveSemanticAnalyzerHookContext context,
      List<Task<? extends Serializable>> rootTasks) throws SemanticException {
    CreateTableDesc desc = ((DDLTask)rootTasks.get(rootTasks.size()-1)).getWork().getCreateTblDesc();
    Map<String,String> tblProps = desc.getTblProps();
    if(tblProps == null) {
      tblProps = new HashMap<String, String>();
    }
    tblProps.put("createdBy", DummyCreateTableHook.class.getName());
    tblProps.put("Message", "Open Source rocks!!");
    desc.setTblProps(tblProps);
  }
}
