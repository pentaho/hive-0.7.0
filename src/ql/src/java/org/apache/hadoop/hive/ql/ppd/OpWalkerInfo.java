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


package org.apache.hadoop.hive.ql.ppd;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import org.apache.hadoop.hive.ql.exec.Operator;
import org.apache.hadoop.hive.ql.lib.Node;
import org.apache.hadoop.hive.ql.lib.NodeProcessorCtx;
import org.apache.hadoop.hive.ql.parse.OpParseContext;
import org.apache.hadoop.hive.ql.parse.ParseContext;
import org.apache.hadoop.hive.ql.parse.RowResolver;

/**
 * Context class for operator walker of predicate pushdown.
 */
public class OpWalkerInfo implements NodeProcessorCtx {
  /**
   * Operator to Pushdown Predicates Map. This keeps track of the final pushdown
   * predicates for each operator as you walk the Op Graph from child to parent
   */
  private final HashMap<Operator<? extends Serializable>, ExprWalkerInfo> opToPushdownPredMap;
  private final Map<Operator<? extends Serializable>, OpParseContext> opToParseCtxMap;
  private final ParseContext pGraphContext;

  public OpWalkerInfo(ParseContext pGraphContext) {
    this.pGraphContext = pGraphContext;
    opToParseCtxMap = pGraphContext.getOpParseCtx();
    opToPushdownPredMap = new HashMap<Operator<? extends Serializable>, ExprWalkerInfo>();
  }

  public ExprWalkerInfo getPrunedPreds(Operator<? extends Serializable> op) {
    return opToPushdownPredMap.get(op);
  }

  public ExprWalkerInfo putPrunedPreds(Operator<? extends Serializable> op,
      ExprWalkerInfo value) {
    return opToPushdownPredMap.put(op, value);
  }

  public RowResolver getRowResolver(Node op) {
    return opToParseCtxMap.get(op).getRowResolver();
  }

  public OpParseContext put(Operator<? extends Serializable> key,
      OpParseContext value) {
    return opToParseCtxMap.put(key, value);
  }

  public ParseContext getParseContext() {
    return pGraphContext;
  }
}
