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


package org.apache.hadoop.hive.serde2.typeinfo;

import java.io.Serializable;

import org.apache.hadoop.hive.serde2.objectinspector.ObjectInspector.Category;

/**
 * A List Type has homogeneous elements. All elements of the List has the same
 * TypeInfo which is returned by getListElementTypeInfo.
 * 
 * Always use the TypeInfoFactory to create new TypeInfo objects, instead of
 * directly creating an instance of this class.
 */
public final class ListTypeInfo extends TypeInfo implements Serializable {

  private static final long serialVersionUID = 1L;
  private TypeInfo listElementTypeInfo;

  /**
   * For java serialization use only.
   */
  public ListTypeInfo() {
  }

  @Override
  public String getTypeName() {
    return org.apache.hadoop.hive.serde.Constants.LIST_TYPE_NAME + "<"
        + listElementTypeInfo.getTypeName() + ">";
  }

  /**
   * For java serialization use only.
   */
  public void setListElementTypeInfo(TypeInfo listElementTypeInfo) {
    this.listElementTypeInfo = listElementTypeInfo;
  }

  /**
   * For TypeInfoFactory use only.
   */
  ListTypeInfo(TypeInfo elementTypeInfo) {
    listElementTypeInfo = elementTypeInfo;
  }

  @Override
  public Category getCategory() {
    return Category.LIST;
  }

  public TypeInfo getListElementTypeInfo() {
    return listElementTypeInfo;
  }

  @Override
  public boolean equals(Object other) {
    if (this == other) {
      return true;
    }
    if (!(other instanceof ListTypeInfo)) {
      return false;
    }
    return getListElementTypeInfo().equals(
        ((ListTypeInfo) other).getListElementTypeInfo());
  }

  @Override
  public int hashCode() {
    return listElementTypeInfo.hashCode();
  }

}
