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

import java.io.Serializable;

import org.apache.hadoop.hive.ql.exec.Operator;
import org.apache.hadoop.hive.ql.lib.NodeProcessorCtx;

/**
 * The processor context for the lineage information. This contains the
 * lineage context and the column info and operator information that is
 * being used for the current expression.
 */
public class ExprProcCtx implements NodeProcessorCtx {

  /**
   * The lineage context that is being populated.
   */
  private LineageCtx lctx;
  
  /**
   * The input operator in case the current operator is not a leaf.
   */
  private Operator<? extends Serializable> inpOp;
  
  /**
   * Constructor.
   * 
   * @param lctx The lineage context thatcontains the dependencies for the inputs.
   * @param inpOp The input operator to the current operator.
   */
  public ExprProcCtx(LineageCtx lctx,
      Operator<? extends Serializable> inpOp) {
    this.lctx = lctx;
    this.inpOp = inpOp;
  }
  
  /**
   * Gets the lineage context.
   * 
   * @return LineageCtx The lineage context.
   */
  public LineageCtx getLineageCtx() {
    return lctx;
  }
 
  /**
   * Gets the input operator.
   * 
   * @return Operator The input operator - this is null in case the current 
   * operator is a leaf.
   */
  public Operator<? extends Serializable> getInputOperator() {
    return inpOp;
  }
}
