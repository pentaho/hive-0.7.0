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

import org.apache.hadoop.hive.serde.Constants;
import org.apache.hadoop.hive.serde2.typeinfo.TypeInfo;
import org.apache.hadoop.hive.serde2.typeinfo.TypeInfoFactory;

/**
 * A constant expression.
 */
public class ExprNodeConstantDesc extends ExprNodeDesc implements Serializable {
  private static final long serialVersionUID = 1L;
  private Object value;

  public ExprNodeConstantDesc() {
  }

  public ExprNodeConstantDesc(TypeInfo typeInfo, Object value) {
    super(typeInfo);
    this.value = value;
  }

  public ExprNodeConstantDesc(Object value) {
    this(TypeInfoFactory
        .getPrimitiveTypeInfoFromJavaPrimitive(value.getClass()), value);
  }

  public void setValue(Object value) {
    this.value = value;
  }

  public Object getValue() {
    return value;
  }

  @Override
  public String toString() {
    return "Const " + typeInfo.toString() + " " + value;
  }

  @Explain(displayName = "expr")
  @Override
  public String getExprString() {
    if (value == null) {
      return "null";
    }

    if (typeInfo.getTypeName().equals(Constants.STRING_TYPE_NAME)) {
      return "'" + value.toString() + "'";
    } else {
      return value.toString();
    }
  }

  @Override
  public ExprNodeDesc clone() {
    return new ExprNodeConstantDesc(typeInfo, value);
  }

  @Override
  public boolean isSame(Object o) {
    if (!(o instanceof ExprNodeConstantDesc)) {
      return false;
    }
    ExprNodeConstantDesc dest = (ExprNodeConstantDesc) o;
    if (!typeInfo.equals(dest.getTypeInfo())) {
      return false;
    }
    if (!value.equals(dest.getValue())) {
      return false;
    }

    return true;
  }
}
