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

import org.apache.hadoop.hive.serde2.typeinfo.TypeInfoFactory;
import org.apache.hadoop.io.NullWritable;

/**
 * ExprNodeNullDesc.
 *
 */
public class ExprNodeNullDesc extends ExprNodeDesc implements Serializable {

  private static final long serialVersionUID = 1L;

  public ExprNodeNullDesc() {
    super(TypeInfoFactory
        .getPrimitiveTypeInfoFromPrimitiveWritable(NullWritable.class));
  }

  public Object getValue() {
    return null;
  }

  @Explain(displayName = "expr")
  @Override
  public String getExprString() {
    return "null";
  }

  @Override
  public ExprNodeDesc clone() {
    return new ExprNodeNullDesc();
  }

  @Override
  public boolean isSame(Object o) {
    if (!(o instanceof ExprNodeNullDesc)) {
      return false;
    }
    if (!typeInfo.equals(((ExprNodeNullDesc) o).getTypeInfo())) {
      return false;
    }

    return true;
  }
}
