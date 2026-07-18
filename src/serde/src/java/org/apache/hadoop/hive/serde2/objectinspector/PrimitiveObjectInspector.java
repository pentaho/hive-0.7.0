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


package org.apache.hadoop.hive.serde2.objectinspector;

/**
 * PrimitiveObjectInspector.
 *
 */
public interface PrimitiveObjectInspector extends ObjectInspector {

  /**
   * The primitive types supported by Hive.
   */
  public static enum PrimitiveCategory {
    VOID, BOOLEAN, BYTE, SHORT, INT, LONG, FLOAT, DOUBLE, STRING, UNKNOWN
  };

  /**
   * Get the primitive category of the PrimitiveObjectInspector.
   */
  PrimitiveCategory getPrimitiveCategory();

  /**
   * Get the Primitive Writable class which is the return type of
   * getPrimitiveWritableObject() and copyToPrimitiveWritableObject().
   */
  Class<?> getPrimitiveWritableClass();

  /**
   * Return the data in an instance of primitive writable Object. If the Object
   * is already a primitive writable Object, just return o.
   */
  Object getPrimitiveWritableObject(Object o);

  /**
   * Get the Java Primitive class which is the return type of
   * getJavaPrimitiveObject().
   */
  Class<?> getJavaPrimitiveClass();

  /**
   * Get the Java Primitive object.
   */
  Object getPrimitiveJavaObject(Object o);

  /**
   * Get a copy of the Object in the same class, so the return value can be
   * stored independently of the parameter.
   * 
   * If the Object is a Primitive Java Object, we just return the parameter
   * since Primitive Java Object is immutable.
   */
  Object copyObject(Object o);

  /**
   * Whether the ObjectInspector prefers to return a Primitive Writable Object
   * instead of a Primitive Java Object. This can be useful for determining the
   * most efficient way to getting data out of the Object.
   */
  boolean preferWritable();
}
