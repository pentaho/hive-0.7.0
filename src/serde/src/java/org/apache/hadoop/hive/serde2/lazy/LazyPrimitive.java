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


package org.apache.hadoop.hive.serde2.lazy;

import org.apache.hadoop.hive.serde2.objectinspector.ObjectInspector;
import org.apache.hadoop.io.Writable;

/**
 * LazyPrimitive stores a primitive Object in a LazyObject.
 */
public abstract class LazyPrimitive<OI extends ObjectInspector, T extends Writable>
    extends LazyObject<OI> {

  LazyPrimitive(OI oi) {
    super(oi);
  }

  LazyPrimitive(LazyPrimitive<OI, T> copy) {
    super(copy.oi);
    isNull = copy.isNull;
  }

  T data;
  boolean isNull = false;

  /**
   * Returns the primitive object represented by this LazyObject. This is useful
   * because it can make sure we have "null" for null objects.
   */
  @Override
  public Object getObject() {
    return isNull ? null : this;
  }

  public T getWritableObject() {
    return isNull ? null : data;
  }

  @Override
  public String toString() {
    return isNull ? null : data.toString();
  }

  @Override
  public int hashCode() {
    return isNull ? 0 : data.hashCode();
  }

}
