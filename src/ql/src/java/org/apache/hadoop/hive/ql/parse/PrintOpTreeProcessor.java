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

import java.io.PrintStream;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Stack;

import org.apache.hadoop.hive.ql.exec.Operator;
import org.apache.hadoop.hive.ql.lib.Node;
import org.apache.hadoop.hive.ql.lib.NodeProcessor;
import org.apache.hadoop.hive.ql.lib.NodeProcessorCtx;

/**
 * PrintOpTreeProcessor.
 *
 */
public class PrintOpTreeProcessor implements NodeProcessor {

  private final PrintStream out;
  private final HashMap<Operator<? extends Serializable>, Integer> opMap = new HashMap<Operator<? extends Serializable>, Integer>();
  private Integer curNum = 0;

  public PrintOpTreeProcessor() {
    out = System.out;
  }

  public PrintOpTreeProcessor(PrintStream o) {
    out = o;
  }

  private String getParents(Operator<? extends Serializable> op) {
    StringBuilder ret = new StringBuilder("[");
    boolean first = true;
    if (op.getParentOperators() != null) {
      for (Operator<? extends Serializable> parent : op.getParentOperators()) {
        if (!first) {
          ret.append(",");
        }
        ret.append(opMap.get(parent));
        first = false;
      }
    }
    ret.append("]");
    return ret.toString();
  }

  private String getChildren(Operator<? extends Serializable> op) {
    StringBuilder ret = new StringBuilder("[");
    boolean first = true;
    if (op.getChildOperators() != null) {
      for (Operator<? extends Serializable> child : op.getChildOperators()) {
        if (!first) {
          ret.append(",");
        }
        ret.append(opMap.get(child));
        first = false;
      }
    }
    ret.append("]");
    return ret.toString();
  }

  public Object process(Node nd, Stack<Node> stack, NodeProcessorCtx ctx,
      Object... nodeOutputs) throws SemanticException {
    Operator<? extends Serializable> op = (Operator<? extends Serializable>) nd;
    if (opMap.get(op) == null) {
      opMap.put(op, curNum++);
    }
    out.println("[" + opMap.get(op) + "] " + op.getClass().getName() + " =p=> "
        + getParents(op) + " =c=> " + getChildren(op));
    if (op.getConf() == null) {
      return null;
    }
    return null;
  }
}
