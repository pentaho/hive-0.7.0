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

/**
 * LazyObject stores an object in a range of bytes in a byte[].
 * 
 * A LazyObject can represent any primitive object or hierarchical object like
 * array, map or struct.
 */
public abstract class LazyObject<OI extends ObjectInspector> {

  OI oi;

  /**
   * Create a LazyObject.
   * 
   * @param oi
   *          Derived classes can access meta information about this Lazy Object
   *          (e.g, separator, nullSequence, escaper) from it.
   */
  protected LazyObject(OI oi) {
    this.oi = oi;
  }

  /**
   * Set the data for this LazyObject. We take ByteArrayRef instead of byte[] so
   * that we will be able to drop the reference to byte[] by a single
   * assignment. The ByteArrayRef object can be reused across multiple rows.
   * 
   * @param bytes
   *          The wrapper of the byte[].
   * @param start
   *          The start position inside the bytes.
   * @param length
   *          The length of the data, starting from "start"
   * @see ByteArrayRef
   */
  public abstract void init(ByteArrayRef bytes, int start, int length);

  /**
   * If the LazyObject is a primitive Object, then deserialize it and return the
   * actual primitive Object. Otherwise (array, map, struct), return this.
   */
  public abstract Object getObject();

  @Override
  public abstract int hashCode();

  protected OI getInspector() {
    return oi;
  }

  protected void setInspector(OI oi) {
    this.oi = oi;
  }
}
