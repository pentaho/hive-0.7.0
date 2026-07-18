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
import org.apache.hadoop.hive.serde2.lazy.LazyUtils;
import org.apache.hadoop.hive.serde2.objectinspector.ObjectInspector;

/**
 * LazyBinaryNonPrimitive.
 *
 * @param <OI>
 */
public abstract class LazyBinaryNonPrimitive<OI extends ObjectInspector>
    extends LazyBinaryObject<OI> {

  protected ByteArrayRef bytes;
  protected int start;
  protected int length;

  protected LazyBinaryNonPrimitive(OI oi) {
    super(oi);
    bytes = null;
    start = 0;
    length = 0;
  }

  @Override
  public Object getObject() {
    return this;
  }

  @Override
  public void init(ByteArrayRef bytes, int start, int length) {
    if (null == bytes) {
      throw new RuntimeException("bytes cannot be null!");
    }
    if (length <= 0) {
      throw new RuntimeException("length should be positive!");
    }
    this.bytes = bytes;
    this.start = start;
    this.length = length;
  }

  @Override
  public int hashCode() {
    return LazyUtils.hashBytes(bytes.getData(), start, length);
  }
}
