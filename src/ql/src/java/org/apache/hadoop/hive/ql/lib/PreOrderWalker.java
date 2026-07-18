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

import org.apache.hadoop.hive.ql.parse.SemanticException;

/**
 * base class for operator graph walker this class takes list of starting ops
 * and walks them one by one.
 */
public class PreOrderWalker extends DefaultGraphWalker {

  /*
   * Since the operator tree is a DAG, nodes with mutliple parents will be
   * visited more than once. This can be made configurable.
   */

  /**
   * Constructor.
   * 
   * @param disp
   *          dispatcher to call for each op encountered
   */
  public PreOrderWalker(Dispatcher disp) {
    super(disp);
  }

  /**
   * Walk the current operator and its descendants.
   * 
   * @param nd
   *          current operator in the graph
   * @throws SemanticException
   */
  @Override
  public void walk(Node nd) throws SemanticException {
    opStack.push(nd);
    dispatch(nd, opStack);

    // move all the children to the front of queue
    if (nd.getChildren() != null) {
      for (Node n : nd.getChildren()) {
        walk(n);
      }
    }

    opStack.pop();
  }
}
