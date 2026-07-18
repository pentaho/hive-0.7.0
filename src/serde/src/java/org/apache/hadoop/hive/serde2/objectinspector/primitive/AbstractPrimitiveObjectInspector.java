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


package org.apache.hadoop.hive.serde2.objectinspector.primitive;

import org.apache.hadoop.hive.serde2.objectinspector.PrimitiveObjectInspector;
import org.apache.hadoop.hive.serde2.objectinspector.primitive.PrimitiveObjectInspectorUtils.PrimitiveTypeEntry;

/**
 * An AbstractPrimitiveObjectInspector is based on
 * ObjectInspectorUtils.PrimitiveTypeEntry.
 */
public abstract class AbstractPrimitiveObjectInspector implements
    PrimitiveObjectInspector {

  PrimitiveTypeEntry typeEntry;

  /**
   * Construct a AbstractPrimitiveObjectInspector.
   */
  protected AbstractPrimitiveObjectInspector(PrimitiveTypeEntry typeEntry) {
    this.typeEntry = typeEntry;
  }

  /**
   * Return the associated Java primitive class for this primitive
   * ObjectInspector.
   */
  @Override
  public Class<?> getJavaPrimitiveClass() {
    return typeEntry.primitiveJavaClass;
  }

  /**
   * Return the associated primitive category for this primitive
   * ObjectInspector.
   */
  @Override
  public PrimitiveCategory getPrimitiveCategory() {
    return typeEntry.primitiveCategory;
  }

  /**
   * Return the associated primitive Writable class for this primitive
   * ObjectInspector.
   */
  @Override
  public Class<?> getPrimitiveWritableClass() {
    return typeEntry.primitiveWritableClass;
  }

  /**
   * Return the associated category this primitive ObjectInspector.
   */
  @Override
  public Category getCategory() {
    return Category.PRIMITIVE;
  }

  /**
   * Return the type name for this primitive ObjectInspector.
   */
  @Override
  public String getTypeName() {
    return typeEntry.typeName;
  }

}
