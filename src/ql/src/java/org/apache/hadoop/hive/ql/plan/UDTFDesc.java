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

import org.apache.hadoop.hive.ql.udf.generic.GenericUDTF;

/**
 * All member variables should have a setters and getters of the form get<member
 * name> and set<member name> or else they won't be recreated properly at run
 * time.
 * 
 */
@Explain(displayName = "UDTF Operator")
public class UDTFDesc implements Serializable {
  private static final long serialVersionUID = 1L;

  private GenericUDTF genericUDTF;

  public UDTFDesc() {
  }

  public UDTFDesc(final GenericUDTF genericUDTF) {
    this.genericUDTF = genericUDTF;
  }

  public GenericUDTF getGenericUDTF() {
    return genericUDTF;
  }

  public void setGenericUDTF(final GenericUDTF genericUDTF) {
    this.genericUDTF = genericUDTF;
  }

  @Explain(displayName = "function name")
  public String getUDTFName() {
    return genericUDTF.toString();
  }
}
