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
import org.apache.hadoop.hive.ql.session.SessionState;
import org.apache.hadoop.hive.ql.session.SessionState.LogHelper;

public class DummySemanticAnalyzerHook1 extends AbstractSemanticAnalyzerHook {
  static int count = 0;
  int myCount = -1;
  boolean isCreateTable;

  @Override
  public ASTNode preAnalyze(HiveSemanticAnalyzerHookContext context, ASTNode ast)
      throws SemanticException {
    LogHelper console = SessionState.getConsole();
    isCreateTable = (ast.getToken().getType() == HiveParser.TOK_CREATETABLE);
    myCount = count++;
    if (isCreateTable) {
      console.printError("DummySemanticAnalyzerHook1 Pre: Count " + myCount);
    }
    return ast;
  }

  public DummySemanticAnalyzerHook1() {
  }

  @Override
  public void postAnalyze(HiveSemanticAnalyzerHookContext context,
      List<Task<? extends Serializable>> rootTasks) throws SemanticException {
    count = 0;
    if (!isCreateTable) {
      return;
    }

    CreateTableDesc desc = ((DDLTask) rootTasks.get(rootTasks.size() - 1)).getWork()
        .getCreateTblDesc();
    Map<String, String> tblProps = desc.getTblProps();
    if (tblProps == null) {
      tblProps = new HashMap<String, String>();
    }
    tblProps.put("createdBy", DummyCreateTableHook.class.getName());
    tblProps.put("Message", "Hive rocks!! Count: " + myCount);

    LogHelper console = SessionState.getConsole();
    console.printError("DummySemanticAnalyzerHook1 Post: Hive rocks!! Count: " + myCount);
  }
}
