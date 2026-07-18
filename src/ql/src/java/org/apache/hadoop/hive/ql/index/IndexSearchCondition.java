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


package org.apache.hadoop.hive.ql.index;

import org.apache.hadoop.hive.ql.plan.ExprNodeColumnDesc;
import org.apache.hadoop.hive.ql.plan.ExprNodeConstantDesc;
import org.apache.hadoop.hive.ql.plan.ExprNodeDesc;

/**
 * IndexSearchCondition represents an individual search condition
 * found by {@link IndexPredicateAnalyzer}.
 *
 * @author John Sichi
 * @version $Id:$
 */
public class IndexSearchCondition
{
  private ExprNodeColumnDesc columnDesc;
  private String comparisonOp;
  private ExprNodeConstantDesc constantDesc;
  private ExprNodeDesc comparisonExpr;

  /**
   * Constructs a search condition, which takes the form
   * <pre>column-ref comparison-op constant-value</pre>.
   *
   * @param columnDesc column being compared
   *
   * @param comparisonOp comparison operator, e.g. "="
   * (taken from GenericUDFBridge.getUdfName())
   *
   * @param constantDesc constant value to search for
   *
   * @Param comparisonExpr the original comparison expression
   */
  public IndexSearchCondition(
    ExprNodeColumnDesc columnDesc,
    String comparisonOp,
    ExprNodeConstantDesc constantDesc,
    ExprNodeDesc comparisonExpr) {

    this.columnDesc = columnDesc;
    this.comparisonOp = comparisonOp;
    this.constantDesc = constantDesc;
    this.comparisonExpr = comparisonExpr;
  }

  public void setColumnDesc(ExprNodeColumnDesc columnDesc) {
    this.columnDesc = columnDesc;
  }
  
  public ExprNodeColumnDesc getColumnDesc() {
    return columnDesc;
  }

  public void setComparisonOp(String comparisonOp) {
    this.comparisonOp = comparisonOp;
  }

  public String getComparisonOp() {
    return comparisonOp;
  }

  public void setConstantDesc(ExprNodeConstantDesc constantDesc) {
    this.constantDesc = constantDesc;
  }

  public ExprNodeConstantDesc getConstantDesc() {
    return constantDesc;
  }

  public void setComparisonExpr(ExprNodeDesc comparisonExpr) {
    this.comparisonExpr = comparisonExpr;
  }

  public ExprNodeDesc getComparisonExpr() {
    return comparisonExpr;
  }
  
  @Override
  public String toString() {
    return comparisonExpr.getExprString();
  }
}
