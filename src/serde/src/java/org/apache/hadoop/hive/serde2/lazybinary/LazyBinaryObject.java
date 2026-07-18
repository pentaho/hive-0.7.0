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

import org.apache.hadoop.hive.serde2.lazy.ByteArrayRef;
import org.apache.hadoop.hive.serde2.objectinspector.ObjectInspector;

/**
 * LazyBinaryObject stores an object in a binary format in a byte[]. For
 * example, a double takes four bytes.
 * 
 * A LazyBinaryObject can represent any primitive object or hierarchical object
 * like string, list, map or struct.
 */
public abstract class LazyBinaryObject<OI extends ObjectInspector> {

  OI oi;

  /**
   * Create a LazyBinaryObject.
   * 
   * @param oi
   *          Derived classes can access meta information about this Lazy Binary
   *          Object (e.g, length, null-bits) from it.
   */
  protected LazyBinaryObject(OI oi) {
    this.oi = oi;
  }

  /**
   * Set the data for this LazyBinaryObject. We take ByteArrayRef instead of
   * byte[] so that we will be able to drop the reference to byte[] by a single
   * assignment. The ByteArrayRef object can be reused across multiple rows.
   * 
   * Never call this function if the object represent a null!!!
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
   * If the LazyBinaryObject is a primitive Object, then deserialize it and
   * return the actual primitive Object. Otherwise (string, list, map, struct),
   * return this.
   */
  public abstract Object getObject();

  @Override
  public abstract int hashCode();
}
