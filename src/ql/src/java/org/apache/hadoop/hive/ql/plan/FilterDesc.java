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


package org.apache.hadoop.hive.ql.plan;

import java.io.Serializable;
import java.util.List;

/**
 * FilterDesc.
 *
 */
@Explain(displayName = "Filter Operator")
public class FilterDesc implements Serializable {

  /**
   * sampleDesc is used to keep track of the sampling descriptor.
   */
  public static class sampleDesc {
    // The numerator of the TABLESAMPLE clause
    private int numerator;

    // The denominator of the TABLESAMPLE clause
    private int denominator;

    // Input files can be pruned
    private boolean inputPruning;

    public sampleDesc() {
    }

    public sampleDesc(int numerator, int denominator,
        List<String> tabBucketCols, boolean inputPruning) {
      this.numerator = numerator;
      this.denominator = denominator;
      this.inputPruning = inputPruning;
    }

    public int getNumerator() {
      return numerator;
    }

    public int getDenominator() {
      return denominator;
    }

    public boolean getInputPruning() {
      return inputPruning;
    }
  }

  private static final long serialVersionUID = 1L;
  private org.apache.hadoop.hive.ql.plan.ExprNodeDesc predicate;
  private boolean isSamplingPred;
  private transient sampleDesc sampleDescr;

  public FilterDesc() {
  }

  public FilterDesc(
      final org.apache.hadoop.hive.ql.plan.ExprNodeDesc predicate,
      boolean isSamplingPred) {
    this.predicate = predicate;
    this.isSamplingPred = isSamplingPred;
    sampleDescr = null;
  }

  public FilterDesc(
      final org.apache.hadoop.hive.ql.plan.ExprNodeDesc predicate,
      boolean isSamplingPred, final sampleDesc sampleDescr) {
    this.predicate = predicate;
    this.isSamplingPred = isSamplingPred;
    this.sampleDescr = sampleDescr;
  }

  @Explain(displayName = "predicate")
  public org.apache.hadoop.hive.ql.plan.ExprNodeDesc getPredicate() {
    return predicate;
  }

  public void setPredicate(
      final org.apache.hadoop.hive.ql.plan.ExprNodeDesc predicate) {
    this.predicate = predicate;
  }

  @Explain(displayName = "isSamplingPred", normalExplain = false)
  public boolean getIsSamplingPred() {
    return isSamplingPred;
  }

  public void setIsSamplingPred(final boolean isSamplingPred) {
    this.isSamplingPred = isSamplingPred;
  }

  @Explain(displayName = "sampleDesc", normalExplain = false)
  public sampleDesc getSampleDescr() {
    return sampleDescr;
  }

  public void setSampleDescr(final sampleDesc sampleDescr) {
    this.sampleDescr = sampleDescr;
  }

}
