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
import java.util.ArrayList;

/**
 * LateralViewJoinDesc.
 *
 */
@Explain(displayName = "Lateral View Join Operator")
public class LateralViewJoinDesc implements Serializable {
  private static final long serialVersionUID = 1L;

  private ArrayList<String> outputInternalColNames;

  public LateralViewJoinDesc() {
  }

  public LateralViewJoinDesc(ArrayList<String> outputInternalColNames) {
    this.outputInternalColNames = outputInternalColNames;
  }

  public void setOutputInternalColNames(ArrayList<String> outputInternalColNames) {
    this.outputInternalColNames = outputInternalColNames;
  }

  @Explain(displayName = "outputColumnNames")
  public ArrayList<String> getOutputInternalColNames() {
    return outputInternalColNames;
  }
}
