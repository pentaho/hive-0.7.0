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
import java.util.List;

import org.apache.hadoop.hive.ql.exec.Task;

public abstract class AbstractSemanticAnalyzerHook implements
HiveSemanticAnalyzerHook {

  public ASTNode preAnalyze(HiveSemanticAnalyzerHookContext context,ASTNode ast)
  throws SemanticException {
    return ast;
  }

  public void postAnalyze(HiveSemanticAnalyzerHookContext context,
      List<Task<? extends Serializable>> rootTasks) throws SemanticException {
  }
}
