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
import java.util.List;

import org.apache.hadoop.hive.ql.lib.Node;
import org.apache.hadoop.hive.serde2.typeinfo.TypeInfo;

/**
 * ExprNodeDesc.
 *
 */
public abstract class ExprNodeDesc implements Serializable, Node {
  private static final long serialVersionUID = 1L;
  TypeInfo typeInfo;

  public ExprNodeDesc() {
  }

  public ExprNodeDesc(TypeInfo typeInfo) {
    this.typeInfo = typeInfo;
    if (typeInfo == null) {
      throw new RuntimeException("typeInfo cannot be null!");
    }
  }

  @Override
  public abstract ExprNodeDesc clone();

  // Cant use equals because the walker depends on them being object equal
  // The default graph walker processes a node after its kids have been
  // processed. That comparison needs
  // object equality - isSame means that the objects are semantically equal.
  public abstract boolean isSame(Object o);

  public TypeInfo getTypeInfo() {
    return typeInfo;
  }

  public void setTypeInfo(TypeInfo typeInfo) {
    this.typeInfo = typeInfo;
  }

  public String getExprString() {
    assert (false);
    return null;
  }

  @Explain(displayName = "type")
  public String getTypeString() {
    return typeInfo.getTypeName();
  }

  public List<String> getCols() {
    return null;
  }

  @Override
  public List<ExprNodeDesc> getChildren() {
    return null;
  }

  @Override
  public String getName() {
    return this.getClass().getName();
  }

}
