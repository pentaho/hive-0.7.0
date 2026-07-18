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


package org.apache.hadoop.hive.ql.optimizer.pcr;

import java.io.Serializable;
import java.util.List;

import org.apache.hadoop.hive.ql.exec.FilterOperator;
import org.apache.hadoop.hive.ql.exec.Operator;
import org.apache.hadoop.hive.ql.lib.NodeProcessorCtx;
import org.apache.hadoop.hive.ql.parse.ParseContext;

/**
 * Context class for operator tree walker for partition condition remover.
 */
public class PcrOpWalkerCtx implements NodeProcessorCtx {

  static public class OpToDeleteInfo {
    private final Operator<? extends Serializable> parent;
    private final FilterOperator operator;

    public OpToDeleteInfo(Operator<? extends Serializable> parent, FilterOperator operator) {
      super();
      this.parent = parent;
      this.operator = operator;
    }
    public Operator<? extends Serializable> getParent() {
      return parent;
    }
    public FilterOperator getOperator() {
      return operator;
    }
  }

  private final ParseContext parseContext;
  private final List<OpToDeleteInfo> opToRemove;

  /**
   * Constructor.
   */
  public PcrOpWalkerCtx(ParseContext parseContext,
      List<OpToDeleteInfo> opToRemove) {
    this.parseContext = parseContext;
    this.opToRemove = opToRemove;
  }

  public ParseContext getParseContext() {
    return parseContext;
  }

  public List<OpToDeleteInfo> getOpToRemove() {
    return opToRemove;
  }
}
