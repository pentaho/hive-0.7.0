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
 * CreateFunctionDesc.
 *
 */
@Explain(displayName = "Create Function")
public class CreateFunctionDesc implements Serializable {
  private static final long serialVersionUID = 1L;

  private String functionName;
  private String className;

  /**
   * For serialization only.
   */
  public CreateFunctionDesc() {
  }
  
  public CreateFunctionDesc(String functionName, String className) {
    this.functionName = functionName;
    this.className = className;
  }

  @Explain(displayName = "name")
  public String getFunctionName() {
    return functionName;
  }

  public void setFunctionName(String functionName) {
    this.functionName = functionName;
  }

  @Explain(displayName = "class")
  public String getClassName() {
    return className;
  }

  public void setClassName(String className) {
    this.className = className;
  }

}
