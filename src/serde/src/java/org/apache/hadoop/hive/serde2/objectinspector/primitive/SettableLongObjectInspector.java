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
 * A SettableLongObjectInspector can set a long value to an object.
 */
public interface SettableLongObjectInspector extends LongObjectInspector {

  /**
   * Set the object with the value. Return the object that has the new value.
   * 
   * In most cases the returned value should be the same as o, but in case o is
   * unmodifiable, this will return a new object with new value.
   */
  Object set(Object o, long value);

  /**
   * Create an object with the value.
   */
  Object create(long value);
}
