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
 * LazyPrimitive stores a primitive Object in a LazyObject.
 */
public abstract class LazyNonPrimitive<OI extends ObjectInspector> extends
    LazyObject<OI> {

  protected ByteArrayRef bytes;
  protected int start;
  protected int length;

  /**
   * Create a LazyNonPrimitive object with the specified ObjectInspector.
   * 
   * @param oi
   *          The ObjectInspector would have to have a hierarchy of
   *          LazyObjectInspectors with the leaf nodes being
   *          WritableObjectInspectors. It's used both for accessing the type
   *          hierarchy of the complex object, as well as getting meta
   *          information (separator, nullSequence, etc) when parsing the lazy
   *          object.
   */
  protected LazyNonPrimitive(OI oi) {
    super(oi);
    bytes = null;
    start = 0;
    length = 0;
  }

  @Override
  public void init(ByteArrayRef bytes, int start, int length) {
    if (bytes == null) {
      throw new RuntimeException("bytes cannot be null!");
    }
    this.bytes = bytes;
    this.start = start;
    this.length = length;
    assert start >= 0;
    assert start + length <= bytes.getData().length;
  }

  @Override
  public Object getObject() {
    return this;
  }

  @Override
  public int hashCode() {
    return LazyUtils.hashBytes(bytes.getData(), start, length);
  }
}
