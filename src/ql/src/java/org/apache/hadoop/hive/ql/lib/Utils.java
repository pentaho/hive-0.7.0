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

import java.util.Stack;

/**
 * Contains common utility functions to manipulate nodes, walkers etc.
 */
public class Utils {

  /**
   * Gets the nth ancestor (the parent being the 1st ancestor) in the traversal
   * path. n=0 returns the currently visited node.
   * 
   * @param st The stack that encodes the traversal path.
   * @param n The value of n (n=0 is the currently visited node).
   * 
   * @return Node The Nth ancestor in the path with respect to the current node.
   */
  public static Node getNthAncestor(Stack<Node> st, int n) {
    assert(st.size() - 1 >= n);
    
    Stack<Node> tmpStack = new Stack<Node>();
    for(int i=0; i<=n; i++)
      tmpStack.push(st.pop());
   
    Node ret_nd = tmpStack.peek();
    
    for(int i=0; i<=n; i++)
      st.push(tmpStack.pop());
    
    assert(tmpStack.isEmpty());
    
    return ret_nd;
  }
}
