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
import java.util.List;

import org.apache.hadoop.hive.serde2.typeinfo.TypeInfo;
import org.apache.hadoop.hive.serde2.typeinfo.TypeInfoFactory;

/**
 * ExprNodeColumnDesc.
 *
 */
public class ExprNodeColumnDesc extends ExprNodeDesc implements Serializable {
  private static final long serialVersionUID = 1L;

  /**
   * The column name.
   */
  private String column;

  /**
   * The alias of the table.
   */
  private String tabAlias;

  /**
   * Is the column a partitioned column.
   */
  private boolean isPartitionColOrVirtualCol;

  public ExprNodeColumnDesc() {
  }

  public ExprNodeColumnDesc(TypeInfo typeInfo, String column, String tabAlias,
      boolean isPartitionColOrVirtualCol) {
    super(typeInfo);
    this.column = column;
    this.tabAlias = tabAlias;
    this.isPartitionColOrVirtualCol = isPartitionColOrVirtualCol;
  }

  public ExprNodeColumnDesc(Class<?> c, String column, String tabAlias,
      boolean isPartitionColOrVirtualCol) {
    super(TypeInfoFactory.getPrimitiveTypeInfoFromJavaPrimitive(c));
    this.column = column;
    this.tabAlias = tabAlias;
    this.isPartitionColOrVirtualCol = isPartitionColOrVirtualCol;
  }

  public String getColumn() {
    return column;
  }

  public void setColumn(String column) {
    this.column = column;
  }

  public String getTabAlias() {
    return tabAlias;
  }

  public void setTabAlias(String tabAlias) {
    this.tabAlias = tabAlias;
  }

  public boolean getIsPartitionColOrVirtualCol() {
    return isPartitionColOrVirtualCol;
  }

  public void setIsPartitionColOrVirtualCol(boolean isPartitionCol) {
    this.isPartitionColOrVirtualCol = isPartitionCol;
  }

  @Override
  public String toString() {
    return "Column[" + column + "]";
  }

  @Explain(displayName = "expr")
  @Override
  public String getExprString() {
    return getColumn();
  }

  @Override
  public List<String> getCols() {
    List<String> lst = new ArrayList<String>();
    lst.add(column);
    return lst;
  }

  @Override
  public ExprNodeDesc clone() {
    return new ExprNodeColumnDesc(typeInfo, column, tabAlias, isPartitionColOrVirtualCol);
  }

  @Override
  public boolean isSame(Object o) {
    if (!(o instanceof ExprNodeColumnDesc)) {
      return false;
    }
    ExprNodeColumnDesc dest = (ExprNodeColumnDesc) o;
    if (!column.equals(dest.getColumn())) {
      return false;
    }
    if (!typeInfo.equals(dest.getTypeInfo())) {
      return false;
    }
    return true;
  }
}
