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


package org.apache.hadoop.hive.ql.exec;

/**
 * Base class of numeric UDAFs like sum and avg which need a
 * NumericUDAFEvaluatorResolver.
 */
public class NumericUDAF extends UDAF {

  /**
   * Constructor.
   */
  public NumericUDAF() {
    setResolver(new NumericUDAFEvaluatorResolver(this.getClass()));
  }
}
