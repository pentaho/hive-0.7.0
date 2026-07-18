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


package org.apache.hadoop.hive.ql.parse;

import java.util.List;

import org.apache.hadoop.hive.ql.exec.ReduceSinkOperator;
import org.apache.hadoop.hive.ql.lib.DefaultGraphWalker;
import org.apache.hadoop.hive.ql.lib.Dispatcher;
import org.apache.hadoop.hive.ql.lib.Node;

/**
 * Walks the operator tree in pre order fashion.
 */
public class GenMapRedWalker extends DefaultGraphWalker {

  /**
   * constructor of the walker - the dispatcher is passed.
   * 
   * @param disp
   *          the dispatcher to be called for each node visited
   */
  public GenMapRedWalker(Dispatcher disp) {
    super(disp);
  }

  /**
   * Walk the given operator.
   * 
   * @param nd
   *          operator being walked
   */
  @Override
  public void walk(Node nd) throws SemanticException {
    List<? extends Node> children = nd.getChildren();

    // maintain the stack of operators encountered
    opStack.push(nd);
    dispatch(nd, opStack);

    // kids of reduce sink operator need not be traversed again
    if ((children == null)
        || ((nd instanceof ReduceSinkOperator) && (getDispatchedList()
        .containsAll(children)))) {
      opStack.pop();
      return;
    }

    // move all the children to the front of queue
    for (Node ch : children) {
      walk(ch);
    }

    // done with this operator
    opStack.pop();
  }
}
