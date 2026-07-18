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


package org.apache.hadoop.hive.ql.optimizer.unionproc;

import java.io.Serializable;
import java.util.List;
import java.util.Stack;

import org.apache.hadoop.hive.ql.exec.Operator;
import org.apache.hadoop.hive.ql.exec.UnionOperator;
import org.apache.hadoop.hive.ql.lib.Node;
import org.apache.hadoop.hive.ql.lib.NodeProcessor;
import org.apache.hadoop.hive.ql.lib.NodeProcessorCtx;
import org.apache.hadoop.hive.ql.optimizer.unionproc.UnionProcContext.UnionParseContext;
import org.apache.hadoop.hive.ql.parse.SemanticException;

/**
 * Operator factory for union processing.
 */
public final class UnionProcFactory {

  private UnionProcFactory() {
    // prevent instantiation
  }

  public static int getPositionParent(UnionOperator union, Stack<Node> stack) {
    int pos = 0;
    int size = stack.size();
    assert size >= 2 && stack.get(size - 1) == union;
    Operator<? extends Serializable> parent = (Operator<? extends Serializable>) stack
        .get(size - 2);
    List<Operator<? extends Serializable>> parUnion = union
        .getParentOperators();
    pos = parUnion.indexOf(parent);
    assert pos < parUnion.size();
    return pos;
  }

  /**
   * MapRed subquery followed by Union.
   */
  public static class MapRedUnion implements NodeProcessor {

    @Override
    public Object process(Node nd, Stack<Node> stack, NodeProcessorCtx procCtx,
        Object... nodeOutputs) throws SemanticException {
      UnionOperator union = (UnionOperator) nd;
      UnionProcContext ctx = (UnionProcContext) procCtx;

      // find the branch on which this processor was invoked
      int pos = getPositionParent(union, stack);
      UnionParseContext uCtx = ctx.getUnionParseContext(union);
      if (uCtx == null) {
        uCtx = new UnionParseContext(union.getConf().getNumInputs());
      }

      ctx.setMapOnlySubq(false);
      uCtx.setMapOnlySubq(pos, false);
      uCtx.setRootTask(pos, false);
      ctx.setUnionParseContext(union, uCtx);
      return null;
    }
  }

  /**
   * Map-only subquery followed by Union.
   */
  public static class MapUnion implements NodeProcessor {

    @Override
    public Object process(Node nd, Stack<Node> stack, NodeProcessorCtx procCtx,
        Object... nodeOutputs) throws SemanticException {
      UnionOperator union = (UnionOperator) nd;
      UnionProcContext ctx = (UnionProcContext) procCtx;

      // find the branch on which this processor was invoked
      int pos = getPositionParent(union, stack);
      UnionParseContext uCtx = ctx.getUnionParseContext(union);
      if (uCtx == null) {
        uCtx = new UnionParseContext(union.getConf().getNumInputs());
      }

      uCtx.setMapOnlySubq(pos, true);
      uCtx.setRootTask(pos, true);
      ctx.setUnionParseContext(union, uCtx);
      return null;
    }
  }

  /**
   * Map-join subquery followed by Union.
   */
  public static class MapJoinUnion implements NodeProcessor {

    @Override
    public Object process(Node nd, Stack<Node> stack, NodeProcessorCtx procCtx,
        Object... nodeOutputs) throws SemanticException {
      UnionOperator union = (UnionOperator) nd;
      UnionProcContext ctx = (UnionProcContext) procCtx;

      // find the branch on which this processor was invoked
      int pos = getPositionParent(union, stack);
      UnionParseContext uCtx = ctx.getUnionParseContext(union);
      if (uCtx == null) {
        uCtx = new UnionParseContext(union.getConf().getNumInputs());
      }

      uCtx.setMapJoinSubq(pos, true);
      ctx.setUnionParseContext(union, uCtx);
      return null;
    }
  }

  /**
   * Union subquery followed by Union.
   */
  public static class UnknownUnion implements NodeProcessor {

    @Override
    public Object process(Node nd, Stack<Node> stack, NodeProcessorCtx procCtx,
        Object... nodeOutputs) throws SemanticException {
      UnionOperator union = (UnionOperator) nd;
      UnionProcContext ctx = (UnionProcContext) procCtx;

      // find the branch on which this processor was invoked
      int pos = getPositionParent(union, stack);
      UnionParseContext uCtx = ctx.getUnionParseContext(union);
      if (uCtx == null) {
        uCtx = new UnionParseContext(union.getConf().getNumInputs());
      }

      uCtx.setMapOnlySubq(pos, true);
      uCtx.setRootTask(pos, false);
      ctx.setUnionParseContext(union, uCtx);
      return null;
    }
  }

  /**
   * Default processor.
   */
  public static class NoUnion implements NodeProcessor {

    @Override
    public Object process(Node nd, Stack<Node> stack, NodeProcessorCtx procCtx,
        Object... nodeOutputs) throws SemanticException {
      return null;
    }
  }

  public static NodeProcessor getMapRedUnion() {
    return new MapRedUnion();
  }

  public static NodeProcessor getMapUnion() {
    return new MapUnion();
  }

  public static NodeProcessor getMapJoinUnion() {
    return new MapJoinUnion();
  }

  public static NodeProcessor getUnknownUnion() {
    return new UnknownUnion();
  }

  public static NodeProcessor getNoUnion() {
    return new NoUnion();
  }

}
