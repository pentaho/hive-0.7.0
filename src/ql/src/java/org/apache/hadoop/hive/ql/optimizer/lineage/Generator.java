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


package org.apache.hadoop.hive.ql.optimizer.lineage;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

import org.apache.hadoop.hive.ql.lib.DefaultRuleDispatcher;
import org.apache.hadoop.hive.ql.lib.Dispatcher;
import org.apache.hadoop.hive.ql.lib.GraphWalker;
import org.apache.hadoop.hive.ql.lib.Node;
import org.apache.hadoop.hive.ql.lib.NodeProcessor;
import org.apache.hadoop.hive.ql.lib.PreOrderWalker;
import org.apache.hadoop.hive.ql.lib.Rule;
import org.apache.hadoop.hive.ql.lib.RuleRegExp;
import org.apache.hadoop.hive.ql.optimizer.Transform;
import org.apache.hadoop.hive.ql.parse.ParseContext;
import org.apache.hadoop.hive.ql.parse.SemanticException;
import org.apache.hadoop.hive.ql.session.SessionState;

/**
 * This class generates the lineage information for the columns
 * and tables from the plan before it goes through other
 * optimization phases.
 */
public class Generator implements Transform {

  /* (non-Javadoc)
   * @see org.apache.hadoop.hive.ql.optimizer.Transform#transform(org.apache.hadoop.hive.ql.parse.ParseContext)
   */
  @Override
  public ParseContext transform(ParseContext pctx) throws SemanticException {

    // Create the lineage context
    LineageCtx lCtx = new LineageCtx(pctx);

    Map<Rule, NodeProcessor> opRules = new LinkedHashMap<Rule, NodeProcessor>();
    opRules.put(new RuleRegExp("R1", "TS%"), OpProcFactory.getTSProc());
    opRules.put(new RuleRegExp("R2", "SCR%"), OpProcFactory.getTransformProc());
    opRules.put(new RuleRegExp("R3", "UDTF%"), OpProcFactory.getTransformProc());
    opRules.put(new RuleRegExp("R4", "SEL%"), OpProcFactory.getSelProc());
    opRules.put(new RuleRegExp("R5", "GBY%"), OpProcFactory.getGroupByProc());
    opRules.put(new RuleRegExp("R6", "UNION%"), OpProcFactory.getUnionProc());
    opRules.put(new RuleRegExp("R7", "JOIN%|MAPJOIN%"), OpProcFactory.getJoinProc());
    opRules.put(new RuleRegExp("R8", "RS%"), OpProcFactory.getReduceSinkProc());
    opRules.put(new RuleRegExp("R9", "LVJ%"), OpProcFactory.getLateralViewJoinProc());

    // The dispatcher fires the processor corresponding to the closest matching rule and passes the context along
    Dispatcher disp = new DefaultRuleDispatcher(OpProcFactory.getDefaultProc(), opRules, lCtx);
    GraphWalker ogw = new PreOrderWalker(disp);

    // Create a list of topop nodes
    ArrayList<Node> topNodes = new ArrayList<Node>();
    topNodes.addAll(pctx.getTopOps().values());
    ogw.startWalking(topNodes, null);

    // Transfer the index from the lineage context to the session state.
    if (SessionState.get() != null) {
      SessionState.get().getLineageState().setIndex(lCtx.getIndex());
    }

    return pctx;
  }

}
