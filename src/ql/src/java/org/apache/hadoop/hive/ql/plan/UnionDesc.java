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

/**
 * unionDesc is a empty class currently. However, union has more than one input
 * (as compared with forward), and therefore, we need a separate class.
 **/
@Explain(displayName = "Union")
public class UnionDesc implements Serializable {
  private static final long serialVersionUID = 1L;

  private transient int numInputs;

  @SuppressWarnings("nls")
  public UnionDesc() {
    numInputs = 2;
  }

  /**
   * @return the numInputs
   */
  public int getNumInputs() {
    return numInputs;
  }

  /**
   * @param numInputs
   *          the numInputs to set
   */
  public void setNumInputs(int numInputs) {
    this.numInputs = numInputs;
  }
}
