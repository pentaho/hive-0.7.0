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
 * FunctionWork.
 *
 */
public class FunctionWork implements Serializable {
  private static final long serialVersionUID = 1L;
  private CreateFunctionDesc createFunctionDesc;
  private DropFunctionDesc dropFunctionDesc;

  /**
   * For serialization only.
   */
  public FunctionWork() {
  }
  
  public FunctionWork(CreateFunctionDesc createFunctionDesc) {
    this.createFunctionDesc = createFunctionDesc;
  }

  public FunctionWork(DropFunctionDesc dropFunctionDesc) {
    this.dropFunctionDesc = dropFunctionDesc;
  }

  public CreateFunctionDesc getCreateFunctionDesc() {
    return createFunctionDesc;
  }

  public void setCreateFunctionDesc(CreateFunctionDesc createFunctionDesc) {
    this.createFunctionDesc = createFunctionDesc;
  }

  public DropFunctionDesc getDropFunctionDesc() {
    return dropFunctionDesc;
  }

  public void setDropFunctionDesc(DropFunctionDesc dropFunctionDesc) {
    this.dropFunctionDesc = dropFunctionDesc;
  }

}
