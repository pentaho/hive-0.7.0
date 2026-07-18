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
import org.apache.hadoop.hive.serde2.objectinspector.PrimitiveObjectInspector.PrimitiveCategory;
import org.apache.hadoop.hive.serde2.objectinspector.primitive.PrimitiveObjectInspectorUtils;

/**
 * There are limited number of Primitive Types. All Primitive Types are defined
 * by TypeInfoFactory.isPrimitiveClass().
 * 
 * Always use the TypeInfoFactory to create new TypeInfo objects, instead of
 * directly creating an instance of this class.
 */
public final class PrimitiveTypeInfo extends TypeInfo implements Serializable {

  private static final long serialVersionUID = 1L;

  private String typeName;

  /**
   * For java serialization use only.
   */
  public PrimitiveTypeInfo() {
  }

  /**
   * For TypeInfoFactory use only.
   */
  PrimitiveTypeInfo(String typeName) {
    this.typeName = typeName;
  }

  /**
   * Returns the category of this TypeInfo.
   */
  @Override
  public Category getCategory() {
    return Category.PRIMITIVE;
  }

  public PrimitiveCategory getPrimitiveCategory() {
    return PrimitiveObjectInspectorUtils.getTypeEntryFromTypeName(typeName).primitiveCategory;
  }

  public Class<?> getPrimitiveWritableClass() {
    return PrimitiveObjectInspectorUtils.getTypeEntryFromTypeName(typeName).primitiveWritableClass;
  }

  public Class<?> getPrimitiveJavaClass() {
    return PrimitiveObjectInspectorUtils.getTypeEntryFromTypeName(typeName).primitiveJavaClass;
  }

  // The following 2 methods are for java serialization use only.
  public void setTypeName(String typeName) {
    this.typeName = typeName;
  }

  @Override
  public String getTypeName() {
    return typeName;
  }

  /**
   * Compare if 2 TypeInfos are the same. We use TypeInfoFactory to cache
   * TypeInfos, so we only need to compare the Object pointer.
   */
  @Override
  public boolean equals(Object other) {
    return this == other;
  }

  /**
   * Generate the hashCode for this TypeInfo.
   */
  @Override
  public int hashCode() {
    return typeName.hashCode();
  }

}
