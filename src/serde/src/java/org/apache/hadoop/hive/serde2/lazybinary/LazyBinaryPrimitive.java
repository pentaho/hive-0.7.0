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


package org.apache.hadoop.hive.serde2.lazybinary;

import org.apache.hadoop.hive.serde2.objectinspector.ObjectInspector;
import org.apache.hadoop.io.Writable;

/**
 * Defines a LazyBinaryPrimitive.
 * 
 * data will be initialized to corresponding types in different LazyBinary
 * primitive classes. For example, data will be a BooleanWritable in the
 * LazyBinaryBoolean class.
 * 
 * There is no null flag any more,
 * 
 */
public abstract class LazyBinaryPrimitive<OI extends ObjectInspector, T extends Writable>
    extends LazyBinaryObject<OI> {

  LazyBinaryPrimitive(OI oi) {
    super(oi);
  }

  LazyBinaryPrimitive(LazyBinaryPrimitive<OI, T> copy) {
    super(copy.oi);
  }

  T data;

  /**
   * Returns the primitive object represented by this LazyBinaryObject. This is
   * useful because it can make sure we have "null" for null objects.
   */
  @Override
  public Object getObject() {
    return data;
  }

  public T getWritableObject() {
    return data;
  }

  @Override
  public String toString() {
    return data.toString();
  }

  @Override
  public int hashCode() {
    return data == null ? 0 : data.hashCode();
  }
}
