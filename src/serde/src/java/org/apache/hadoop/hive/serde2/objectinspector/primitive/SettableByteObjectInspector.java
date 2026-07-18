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

/**
 * A SettableByteObjectInspector can set a byte value to an object.
 */
public interface SettableByteObjectInspector extends ByteObjectInspector {

  /**
   * Set the object with the value. Return the object that has the new value.
   * 
   * In most cases the returned value should be the same as o, but in case o is
   * unmodifiable, this will return a new object with new value.
   */
  Object set(Object o, byte value);

  /**
   * Create an object with the value.
   */
  Object create(byte value);
}
