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

import static org.apache.hadoop.hive.ql.parse.HiveParser.TOK_IFEXISTS;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.hadoop.hive.conf.HiveConf;
import org.apache.hadoop.hive.conf.HiveConf.ConfVars;
import org.apache.hadoop.hive.ql.exec.FunctionRegistry;
import org.apache.hadoop.hive.ql.exec.TaskFactory;
import org.apache.hadoop.hive.ql.plan.CreateFunctionDesc;
import org.apache.hadoop.hive.ql.plan.DropFunctionDesc;
import org.apache.hadoop.hive.ql.plan.FunctionWork;

/**
 * FunctionSemanticAnalyzer.
 *
 */
public class FunctionSemanticAnalyzer extends BaseSemanticAnalyzer {
  private static final Log LOG = LogFactory
      .getLog("hive.ql.parse.FunctionSemanticAnalyzer");

  public FunctionSemanticAnalyzer(HiveConf conf) throws SemanticException {
    super(conf);
  }

  @Override
  public void analyzeInternal(ASTNode ast) throws SemanticException {
    if (ast.getToken().getType() == HiveParser.TOK_CREATEFUNCTION) {
      analyzeCreateFunction(ast);
    }
    if (ast.getToken().getType() == HiveParser.TOK_DROPFUNCTION) {
      analyzeDropFunction(ast);
    }

    LOG.info("analyze done");
  }

  private void analyzeCreateFunction(ASTNode ast) throws SemanticException {
    String functionName = ast.getChild(0).getText();
    String className = unescapeSQLString(ast.getChild(1).getText());
    CreateFunctionDesc desc = new CreateFunctionDesc(functionName, className);
    rootTasks.add(TaskFactory.get(new FunctionWork(desc), conf));
  }

  private void analyzeDropFunction(ASTNode ast) throws SemanticException {
    String functionName = ast.getChild(0).getText();
    boolean ifExists = (ast.getFirstChildWithType(TOK_IFEXISTS) != null);
    // we want to signal an error if the function doesn't exist and we're
    // configured not to ignore this
    boolean throwException =
      !ifExists && !HiveConf.getBoolVar(conf, ConfVars.DROPIGNORESNONEXISTENT);
    if (throwException && FunctionRegistry.getFunctionInfo(functionName) == null) {
      throw new SemanticException(ErrorMsg.INVALID_FUNCTION.getMsg(functionName));
    }

    DropFunctionDesc desc = new DropFunctionDesc(functionName);
    rootTasks.add(TaskFactory.get(new FunctionWork(desc), conf));
  }
}
