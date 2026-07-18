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

/**
 * HiveSemanticAnalyzerHook allows Hive to be extended with custom
 * logic for semantic analysis of QL statements.  This interface
 * and any Hive internals it exposes are currently
 * "limited private and evolving" (unless otherwise stated elsewhere)
 * and intended mainly for use by the Howl project.
 *
 *<p>
 *
 * Note that the lifetime of an instantiated hook object is scoped to
 * the analysis of a single statement; hook instances are never reused.
 */
public interface HiveSemanticAnalyzerHook {
  /**
   * Invoked before Hive performs its own semantic analysis on
   * a statement.  The implementation may inspect the statement AST and
   * prevent its execution by throwing a SemanticException.
   * Optionally, it may also augment/rewrite the AST, but must produce
   * a form equivalent to one which could have
   * been returned directly from Hive's own parser.
   *
   * @param context context information for semantic analysis
   *
   * @param ast AST being analyzed and optionally rewritten
   *
   * @return replacement AST (typically the same as the original AST unless the
   * entire tree had to be replaced; must not be null)
   */
  public ASTNode preAnalyze(
    HiveSemanticAnalyzerHookContext context,
    ASTNode ast) throws SemanticException;

  /**
   * Invoked after Hive performs its own semantic analysis on a
   * statement (including optimization).
   * Hive calls postAnalyze on the same hook object
   * as preAnalyze, so the hook can maintain state across the calls.
   *
   * @param context context information for semantic analysis
   * @param rootTasks root tasks produced by semantic analysis;
   * the hook is free to modify this list or its contents
   */
  public void postAnalyze(
    HiveSemanticAnalyzerHookContext context,
    List<Task<? extends Serializable>> rootTasks) throws SemanticException;
}
