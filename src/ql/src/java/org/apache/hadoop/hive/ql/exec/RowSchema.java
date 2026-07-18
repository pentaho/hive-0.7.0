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

import java.io.Serializable;
import java.util.ArrayList;

/**
 * RowSchema Implementation.
 */
public class RowSchema implements Serializable {

  private static final long serialVersionUID = 1L;
  private ArrayList<ColumnInfo> signature;

  public RowSchema() {
  }

  public RowSchema(ArrayList<ColumnInfo> signature) {
    this.signature = signature;
  }

  public void setSignature(ArrayList<ColumnInfo> signature) {
    this.signature = signature;
  }

  public ArrayList<ColumnInfo> getSignature() {
    return signature;
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder('(');
    for (ColumnInfo col: signature) {
      sb.append(col.toString());
    }
    sb.append(')');
    return sb.toString();
  }
}
