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
import org.apache.hadoop.hive.serde2.objectinspector.primitive.WritableFloatObjectInspector;
import org.apache.hadoop.io.FloatWritable;

/**
 * LazyBinaryObject for float which takes four bytes.
 */
public class LazyBinaryFloat extends
    LazyBinaryPrimitive<WritableFloatObjectInspector, FloatWritable> {

  LazyBinaryFloat(WritableFloatObjectInspector oi) {
    super(oi);
    data = new FloatWritable();
  }

  LazyBinaryFloat(LazyBinaryFloat copy) {
    super(copy);
    data = new FloatWritable(copy.data.get());
  }

  @Override
  public void init(ByteArrayRef bytes, int start, int length) {
    assert (4 == length);
    data.set(Float.intBitsToFloat(LazyBinaryUtils.byteArrayToInt(bytes
        .getData(), start)));
  }
}
