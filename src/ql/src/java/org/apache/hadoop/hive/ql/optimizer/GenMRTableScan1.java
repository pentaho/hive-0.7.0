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
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.Stack;

import org.apache.hadoop.hive.ql.exec.Operator;
import org.apache.hadoop.hive.ql.exec.TableScanOperator;
import org.apache.hadoop.hive.ql.exec.Task;
import org.apache.hadoop.hive.ql.exec.TaskFactory;
import org.apache.hadoop.hive.ql.lib.Node;
import org.apache.hadoop.hive.ql.lib.NodeProcessor;
import org.apache.hadoop.hive.ql.lib.NodeProcessorCtx;
import org.apache.hadoop.hive.ql.metadata.Partition;
import org.apache.hadoop.hive.ql.optimizer.GenMRProcContext.GenMapRedCtx;
import org.apache.hadoop.hive.ql.parse.BaseSemanticAnalyzer.tableSpec;
import org.apache.hadoop.hive.ql.parse.ParseContext;
import org.apache.hadoop.hive.ql.parse.PrunedPartitionList;
import org.apache.hadoop.hive.ql.parse.QBParseInfo;
import org.apache.hadoop.hive.ql.parse.SemanticException;
import org.apache.hadoop.hive.ql.plan.MapredWork;
import org.apache.hadoop.hive.ql.plan.StatsWork;
/**
 * Processor for the rule - table scan.
 */
public class GenMRTableScan1 implements NodeProcessor {
  public GenMRTableScan1() {
  }

  /**
   * Table Sink encountered.
   *
   * @param nd
   *          the table sink operator encountered
   * @param opProcCtx
   *          context
   */
  public Object process(Node nd, Stack<Node> stack, NodeProcessorCtx opProcCtx,
      Object... nodeOutputs) throws SemanticException {
    TableScanOperator op = (TableScanOperator) nd;
    GenMRProcContext ctx = (GenMRProcContext) opProcCtx;
    ParseContext parseCtx = ctx.getParseCtx();
    Map<Operator<? extends Serializable>, GenMapRedCtx> mapCurrCtx = ctx.getMapCurrCtx();

    // create a dummy MapReduce task
    MapredWork currWork = GenMapRedUtils.getMapRedWork(parseCtx.getConf());
    Task<? extends Serializable> currTask = TaskFactory.get(currWork, parseCtx.getConf());
    Operator<? extends Serializable> currTopOp = op;
    ctx.setCurrTask(currTask);
    ctx.setCurrTopOp(currTopOp);

    for (String alias : parseCtx.getTopOps().keySet()) {
      Operator<? extends Serializable> currOp = parseCtx.getTopOps().get(alias);
      if (currOp == op) {
        String currAliasId = alias;
        ctx.setCurrAliasId(currAliasId);
        mapCurrCtx.put(op, new GenMapRedCtx(currTask, currTopOp, currAliasId));

        QBParseInfo parseInfo = parseCtx.getQB().getParseInfo();
        if (parseInfo.isAnalyzeCommand()) {

          //   ANALYZE TABLE T [PARTITION (...)] COMPUTE STATISTICS;
          // The plan consists of a simple MapRedTask followed by a StatsTask.
          // The MR task is just a simple TableScanOperator

          StatsWork statsWork = new StatsWork(parseCtx.getQB().getParseInfo().getTableSpec());
          statsWork.setAggKey(op.getConf().getStatsAggPrefix());
          Task<StatsWork> statsTask = TaskFactory.get(statsWork, parseCtx.getConf());
          currTask.addDependentTask(statsTask);
          ctx.getRootTasks().add(currTask);
          currWork.setGatheringStats(true);
          // NOTE: here we should use the new partition predicate pushdown API to get a list of pruned list,
          // and pass it to setTaskPlan as the last parameter
          Set<Partition> confirmedPartns = new HashSet<Partition>();
          tableSpec tblSpec = parseInfo.getTableSpec();
          if (tblSpec.specType == tableSpec.SpecType.STATIC_PARTITION) {
            // static partition
            confirmedPartns.add(tblSpec.partHandle);
          } else if (tblSpec.specType == tableSpec.SpecType.DYNAMIC_PARTITION) {
            // dynamic partition
            confirmedPartns.addAll(tblSpec.partitions);
          }
          if (confirmedPartns.size() > 0) {
            PrunedPartitionList partList = new PrunedPartitionList(confirmedPartns, new HashSet<Partition>(), null);
            GenMapRedUtils.setTaskPlan(currAliasId, currTopOp, currWork, false, ctx, partList);
          } else { // non-partitioned table
            GenMapRedUtils.setTaskPlan(currAliasId, currTopOp, currWork, false, ctx);
          }
        }
        return null;
      }
    }
    assert false;
    return null;
  }

}
