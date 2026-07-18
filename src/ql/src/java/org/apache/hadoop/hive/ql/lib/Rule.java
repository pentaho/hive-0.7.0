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


package org.apache.hadoop.hive.ql.lib;

import java.util.Stack;

import org.apache.hadoop.hive.ql.parse.SemanticException;

/**
 * Rule interface for Operators Used in operator dispatching to dispatch
 * process/visitor functions for operators.
 */
public interface Rule {

  /**
   * @return the cost of the rule - the lower the cost, the better the rule
   *         matches
   * @throws SemanticException
   */
  int cost(Stack<Node> stack) throws SemanticException;

  /**
   * @return the name of the rule - may be useful for debugging
   */
  String getName();
}
