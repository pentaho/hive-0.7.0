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
 * Base class for processing operators which is no-op. The specific processors
 * can register their own context with the dispatcher.
 */
public interface NodeProcessor {

  /**
   * Generic process for all ops that don't have specific implementations.
   * 
   * @param nd
   *          operator to process
   * @param procCtx
   *          operator processor context
   * @param nodeOutputs
   *          A variable argument list of outputs from other nodes in the walk
   * @return Object to be returned by the process call
   * @throws SemanticException
   */
  Object process(Node nd, Stack<Node> stack, NodeProcessorCtx procCtx,
      Object... nodeOutputs) throws SemanticException;
}
