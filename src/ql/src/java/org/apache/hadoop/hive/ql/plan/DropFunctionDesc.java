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
 * DropFunctionDesc.
 *
 */
@Explain(displayName = "Drop Function")
public class DropFunctionDesc implements Serializable {
  private static final long serialVersionUID = 1L;

  private String functionName;

  /**
   * For serialization only.
   */
  public DropFunctionDesc() {
  }
  
  public DropFunctionDesc(String functionName) {
    this.functionName = functionName;
  }

  @Explain(displayName = "name")
  public String getFunctionName() {
    return functionName;
  }

  public void setFunctionName(String functionName) {
    this.functionName = functionName;
  }

}
