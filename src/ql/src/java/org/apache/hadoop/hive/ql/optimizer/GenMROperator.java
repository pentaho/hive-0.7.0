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


package org.apache.hadoop.hive.ql.optimizer;

import java.io.Serializable;
import java.util.Map;
import java.util.Stack;

import org.apache.hadoop.hive.ql.exec.Operator;
import org.apache.hadoop.hive.ql.lib.Node;
import org.apache.hadoop.hive.ql.lib.NodeProcessor;
import org.apache.hadoop.hive.ql.lib.NodeProcessorCtx;
import org.apache.hadoop.hive.ql.optimizer.GenMRProcContext.GenMapRedCtx;
import org.apache.hadoop.hive.ql.parse.SemanticException;

/**
 * Processor for the rule - no specific rule fired.
 */
public class GenMROperator implements NodeProcessor {

  public GenMROperator() {
  }

  /**
   * Reduce Scan encountered.
   * 
   * @param nd
   *          the reduce sink operator encountered
   * @param procCtx
   *          context
   */
  public Object process(Node nd, Stack<Node> stack, NodeProcessorCtx procCtx,
      Object... nodeOutputs) throws SemanticException {
    GenMRProcContext ctx = (GenMRProcContext) procCtx;

    Map<Operator<? extends Serializable>, GenMapRedCtx> mapCurrCtx = ctx
        .getMapCurrCtx();
    GenMapRedCtx mapredCtx = mapCurrCtx.get(stack.get(stack.size() - 2));
    mapCurrCtx.put((Operator<? extends Serializable>) nd, new GenMapRedCtx(
        mapredCtx.getCurrTask(), mapredCtx.getCurrTopOp(), mapredCtx
        .getCurrAliasId()));
    return null;
  }
}
