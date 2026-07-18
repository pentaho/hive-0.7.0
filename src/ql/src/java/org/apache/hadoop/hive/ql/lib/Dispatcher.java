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
 * Dispatcher interface for Operators Used in operator graph walking to dispatch
 * process/visitor functions for operators.
 */
public interface Dispatcher {

  /**
   * Dispatcher function.
   *
   * @param nd
   *          operator to process.
   * @param stack
   *          operator stack to process.
   * @param nodeOutputs
   *          The argument list of outputs from processing other nodes that are
   *          passed to this dispatcher from the walker.
   * @return Object The return object from the processing call.
   * @throws SemanticException
   */
  Object dispatch(Node nd, Stack<Node> stack, Object... nodeOutputs)
      throws SemanticException;

}
