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
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

import org.apache.hadoop.hive.ql.exec.Operator;
import org.apache.hadoop.hive.ql.exec.ReduceSinkOperator;
import org.apache.hadoop.hive.ql.exec.Task;
import org.apache.hadoop.hive.ql.lib.Node;
import org.apache.hadoop.hive.ql.lib.NodeProcessor;
import org.apache.hadoop.hive.ql.lib.NodeProcessorCtx;
import org.apache.hadoop.hive.ql.optimizer.GenMRProcContext.GenMapRedCtx;
import org.apache.hadoop.hive.ql.parse.SemanticException;
import org.apache.hadoop.hive.ql.plan.MapredWork;

/**
 * Processor for the rule - table scan followed by reduce sink.
 */
public class GenMRRedSink1 implements NodeProcessor {

  public GenMRRedSink1() {
  }

  /**
   * Reduce Scan encountered.
   * 
   * @param nd
   *          the reduce sink operator encountered
   * @param opProcCtx
   *          context
   */
  public Object process(Node nd, Stack<Node> stack, NodeProcessorCtx opProcCtx,
      Object... nodeOutputs) throws SemanticException {
    ReduceSinkOperator op = (ReduceSinkOperator) nd;
    GenMRProcContext ctx = (GenMRProcContext) opProcCtx;

    Map<Operator<? extends Serializable>, GenMapRedCtx> mapCurrCtx = ctx
        .getMapCurrCtx();
    GenMapRedCtx mapredCtx = mapCurrCtx.get(stack.get(stack.size() - 2));
    Task<? extends Serializable> currTask = mapredCtx.getCurrTask();
    MapredWork currPlan = (MapredWork) currTask.getWork();
    Operator<? extends Serializable> currTopOp = mapredCtx.getCurrTopOp();
    String currAliasId = mapredCtx.getCurrAliasId();
    Operator<? extends Serializable> reducer = op.getChildOperators().get(0);
    HashMap<Operator<? extends Serializable>, Task<? extends Serializable>> opTaskMap = ctx
        .getOpTaskMap();
    Task<? extends Serializable> opMapTask = opTaskMap.get(reducer);

    ctx.setCurrTopOp(currTopOp);
    ctx.setCurrAliasId(currAliasId);
    ctx.setCurrTask(currTask);

    // If the plan for this reducer does not exist, initialize the plan
    if (opMapTask == null) {
      if (currPlan.getReducer() == null) {
        GenMapRedUtils.initPlan(op, ctx);
      } else {
        GenMapRedUtils.splitPlan(op, ctx);
      }
    } else {
      // This will happen in case of joins. The current plan can be thrown away
      // after being merged with the original plan
      GenMapRedUtils.joinPlan(op, null, opMapTask, ctx, -1, false, false, false);
      currTask = opMapTask;
      ctx.setCurrTask(currTask);
    }

    mapCurrCtx.put(op, new GenMapRedCtx(ctx.getCurrTask(), ctx.getCurrTopOp(),
        ctx.getCurrAliasId()));
    return null;
  }

}
