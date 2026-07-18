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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Stack;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.hive.ql.exec.FilterOperator;
import org.apache.hadoop.hive.ql.exec.TableScanOperator;
import org.apache.hadoop.hive.ql.lib.DefaultGraphWalker;
import org.apache.hadoop.hive.ql.lib.DefaultRuleDispatcher;
import org.apache.hadoop.hive.ql.lib.Dispatcher;
import org.apache.hadoop.hive.ql.lib.GraphWalker;
import org.apache.hadoop.hive.ql.lib.Node;
import org.apache.hadoop.hive.ql.lib.NodeProcessor;
import org.apache.hadoop.hive.ql.lib.NodeProcessorCtx;
import org.apache.hadoop.hive.ql.lib.Rule;
import org.apache.hadoop.hive.ql.lib.RuleRegExp;
import org.apache.hadoop.hive.ql.metadata.Partition;
import org.apache.hadoop.hive.ql.parse.ParseContext;
import org.apache.hadoop.hive.ql.parse.SemanticException;
import org.apache.hadoop.hive.ql.plan.FilterDesc;
import org.apache.hadoop.hive.ql.plan.FilterDesc.sampleDesc;

/**
 * The transformation step that does sample pruning.
 * 
 */
public class SamplePruner implements Transform {

  /**
   * SamplePrunerCtx.
   *
   */
  public static class SamplePrunerCtx implements NodeProcessorCtx {
    HashMap<TableScanOperator, sampleDesc> opToSamplePruner;

    public SamplePrunerCtx(
        HashMap<TableScanOperator, sampleDesc> opToSamplePruner) {
      this.opToSamplePruner = opToSamplePruner;
    }

    /**
     * @return the opToSamplePruner
     */
    public HashMap<TableScanOperator, sampleDesc> getOpToSamplePruner() {
      return opToSamplePruner;
    }

    /**
     * @param opToSamplePruner
     *          the opToSamplePruner to set
     */
    public void setOpToSamplePruner(
        HashMap<TableScanOperator, sampleDesc> opToSamplePruner) {
      this.opToSamplePruner = opToSamplePruner;
    }
  }

  // The log
  private static final Log LOG = LogFactory
      .getLog("hive.ql.optimizer.SamplePruner");

  /*
   * (non-Javadoc)
   * 
   * @see
   * org.apache.hadoop.hive.ql.optimizer.Transform#transform(org.apache.hadoop
   * .hive.ql.parse.ParseContext)
   */
  @Override
  public ParseContext transform(ParseContext pctx) throws SemanticException {

    // create a the context for walking operators
    SamplePrunerCtx samplePrunerCtx = new SamplePrunerCtx(pctx
        .getOpToSamplePruner());

    Map<Rule, NodeProcessor> opRules = new LinkedHashMap<Rule, NodeProcessor>();
    opRules.put(new RuleRegExp("R1", "(TS%FIL%FIL%)"), getFilterProc());

    // The dispatcher fires the processor corresponding to the closest matching
    // rule and passes the context along
    Dispatcher disp = new DefaultRuleDispatcher(getDefaultProc(), opRules,
        samplePrunerCtx);
    GraphWalker ogw = new DefaultGraphWalker(disp);

    // Create a list of topop nodes
    ArrayList<Node> topNodes = new ArrayList<Node>();
    topNodes.addAll(pctx.getTopOps().values());
    ogw.startWalking(topNodes, null);
    return pctx;
  }

  /**
   * FilterPPR filter processor.
   *
   */
  public static class FilterPPR implements NodeProcessor {

    @Override
    public Object process(Node nd, Stack<Node> stack, NodeProcessorCtx procCtx,
        Object... nodeOutputs) throws SemanticException {
      FilterOperator filOp = (FilterOperator) nd;
      FilterDesc filOpDesc = filOp.getConf();
      sampleDesc sampleDescr = filOpDesc.getSampleDescr();

      if ((sampleDescr == null) || !sampleDescr.getInputPruning()) {
        return null;
      }

      assert stack.size() == 3;
      TableScanOperator tsOp = (TableScanOperator) stack.get(0);
      ((SamplePrunerCtx) procCtx).getOpToSamplePruner().put(tsOp, sampleDescr);
      return null;
    }
  }

  public static NodeProcessor getFilterProc() {
    return new FilterPPR();
  }

  /**
   * DefaultPPR default processor which does nothing.
   *
   */
  public static class DefaultPPR implements NodeProcessor {

    @Override
    public Object process(Node nd, Stack<Node> stack, NodeProcessorCtx procCtx,
        Object... nodeOutputs) throws SemanticException {
      // Nothing needs to be done.
      return null;
    }
  }

  public static NodeProcessor getDefaultProc() {
    return new DefaultPPR();
  }

  /**
   * Prunes to get all the files in the partition that satisfy the TABLESAMPLE
   * clause.
   * 
   * @param part
   *          The partition to prune
   * @return Path[]
   * @throws SemanticException
   */
  @SuppressWarnings("nls")
  public static Path[] prune(Partition part, sampleDesc sampleDescr)
      throws SemanticException {
    int num = sampleDescr.getNumerator();
    int den = sampleDescr.getDenominator();
    int bucketCount = part.getBucketCount();
    String fullScanMsg = "";

    // check if input pruning is possible
    if (sampleDescr.getInputPruning()) {
      LOG.trace("numerator = " + num);
      LOG.trace("denominator = " + den);
      LOG.trace("bucket count = " + bucketCount);
      if (bucketCount == den) {
        Path[] ret = new Path[1];
        ret[0] = part.getBucketPath(num - 1);
        return (ret);
      } else if (bucketCount > den && bucketCount % den == 0) {
        int numPathsInSample = bucketCount / den;
        Path[] ret = new Path[numPathsInSample];
        for (int i = 0; i < numPathsInSample; i++) {
          ret[i] = part.getBucketPath(i * den + num - 1);
        }
        return ret;
      } else if (bucketCount < den && den % bucketCount == 0) {
        Path[] ret = new Path[1];
        ret[0] = part.getBucketPath((num - 1) % bucketCount);
        return ret;
      } else {
        // need to do full scan
        fullScanMsg = "Tablesample denominator " + den
            + " is not multiple/divisor of bucket count " + bucketCount
            + " of table " + part.getTable().getTableName();
      }
    } else {
      // need to do full scan
      fullScanMsg = "Tablesample not on clustered columns";
    }
    LOG.warn(fullScanMsg + ", using full table scan");
    Path[] ret = part.getPath();
    return ret;
  }

}
