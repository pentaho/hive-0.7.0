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

import org.apache.hadoop.hive.serde2.io.DoubleWritable;
import org.apache.hadoop.hive.serde2.lazy.ByteArrayRef;
import org.apache.hadoop.hive.serde2.objectinspector.primitive.WritableDoubleObjectInspector;

/**
 * LazyBinaryObject for double which takes eight bytes.
 */
public class LazyBinaryDouble extends
    LazyBinaryPrimitive<WritableDoubleObjectInspector, DoubleWritable> {

  LazyBinaryDouble(WritableDoubleObjectInspector oi) {
    super(oi);
    data = new DoubleWritable();
  }

  LazyBinaryDouble(LazyBinaryDouble copy) {
    super(copy);
    data = new DoubleWritable(copy.data.get());
  }

  @Override
  public void init(ByteArrayRef bytes, int start, int length) {
    assert (8 == length);
    data.set(Double.longBitsToDouble(LazyBinaryUtils.byteArrayToLong(bytes
        .getData(), start)));
  }
}
