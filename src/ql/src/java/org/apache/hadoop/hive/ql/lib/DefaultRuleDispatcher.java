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

import java.util.Map;
import java.util.Stack;

import org.apache.hadoop.hive.ql.parse.SemanticException;

/**
 * Dispatches calls to relevant method in processor. The user registers various
 * rules with the dispatcher, and the processor corresponding to closest
 * matching rule is fired.
 */
public class DefaultRuleDispatcher implements Dispatcher {

  private final Map<Rule, NodeProcessor> procRules;
  private final NodeProcessorCtx procCtx;
  private final NodeProcessor defaultProc;

  /**
   * Constructor.
   * 
   * @param defaultProc
   *          default processor to be fired if no rule matches
   * @param rules
   *          operator processor that handles actual processing of the node
   * @param procCtx
   *          operator processor context, which is opaque to the dispatcher
   */
  public DefaultRuleDispatcher(NodeProcessor defaultProc,
      Map<Rule, NodeProcessor> rules, NodeProcessorCtx procCtx) {
    this.defaultProc = defaultProc;
    procRules = rules;
    this.procCtx = procCtx;
  }

  /**
   * Dispatcher function.
   * 
   * @param nd
   *          operator to process
   * @param ndStack
   *          the operators encountered so far
   * @throws SemanticException
   */
  public Object dispatch(Node nd, Stack<Node> ndStack, Object... nodeOutputs)
      throws SemanticException {

    // find the firing rule
    // find the rule from the stack specified
    Rule rule = null;
    int minCost = Integer.MAX_VALUE;
    for (Rule r : procRules.keySet()) {
      int cost = r.cost(ndStack);
      if ((cost >= 0) && (cost <= minCost)) {
        minCost = cost;
        rule = r;
      }
    }

    NodeProcessor proc;

    if (rule == null) {
      proc = defaultProc;
    } else {
      proc = procRules.get(rule);
    }

    // Do nothing in case proc is null
    if (proc != null) {
      // Call the process function
      return proc.process(nd, ndStack, procCtx, nodeOutputs);
    } else {
      return null;
    }
  }
}
