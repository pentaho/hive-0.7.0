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


package org.apache.hadoop.hive.ql.optimizer.physical;

import java.io.Serializable;
import java.util.Stack;

import org.apache.hadoop.hive.ql.exec.JoinOperator;
import org.apache.hadoop.hive.ql.exec.Task;
import org.apache.hadoop.hive.ql.lib.Node;
import org.apache.hadoop.hive.ql.lib.NodeProcessor;
import org.apache.hadoop.hive.ql.lib.NodeProcessorCtx;
import org.apache.hadoop.hive.ql.optimizer.physical.SkewJoinResolver.SkewJoinProcCtx;
import org.apache.hadoop.hive.ql.parse.ParseContext;
import org.apache.hadoop.hive.ql.parse.SemanticException;

/**
 * Node processor factory for skew join resolver.
 */
public final class SkewJoinProcFactory {

  public static NodeProcessor getDefaultProc() {
    return new SkewJoinDefaultProcessor();
  }

  public static NodeProcessor getJoinProc() {
    return new SkewJoinJoinProcessor();
  }

  /**
   * SkewJoinJoinProcessor.
   *
   */
  public static class SkewJoinJoinProcessor implements NodeProcessor {
    public Object process(Node nd, Stack<Node> stack, NodeProcessorCtx ctx,
        Object... nodeOutputs) throws SemanticException {
      SkewJoinProcCtx context = (SkewJoinProcCtx) ctx;
      JoinOperator op = (JoinOperator) nd;
      ParseContext parseContext = context.getParseCtx();
      Task<? extends Serializable> currentTsk = context.getCurrentTask();
      GenMRSkewJoinProcessor.processSkewJoin(op, currentTsk, parseContext);
      return null;
    }
  }

  /**
   * SkewJoinDefaultProcessor.
   *
   */
  public static class SkewJoinDefaultProcessor implements NodeProcessor {
    public Object process(Node nd, Stack<Node> stack, NodeProcessorCtx ctx,
        Object... nodeOutputs) throws SemanticException {
      return null;
    }
  }

  private SkewJoinProcFactory() {
    // prevent instantiation
  }
}
