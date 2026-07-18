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

import org.apache.hadoop.hive.serde2.io.ShortWritable;
import org.apache.hadoop.hive.serde2.lazy.ByteArrayRef;
import org.apache.hadoop.hive.serde2.objectinspector.primitive.WritableShortObjectInspector;

/**
 * LazyBinaryObject for short which takes two bytes.
 */
public class LazyBinaryShort extends
    LazyBinaryPrimitive<WritableShortObjectInspector, ShortWritable> {

  LazyBinaryShort(WritableShortObjectInspector oi) {
    super(oi);
    data = new ShortWritable();
  }

  LazyBinaryShort(LazyBinaryShort copy) {
    super(copy);
    data = new ShortWritable(copy.data.get());
  }

  @Override
  public void init(ByteArrayRef bytes, int start, int length) {
    assert (2 == length);
    data.set(LazyBinaryUtils.byteArrayToShort(bytes.getData(), start));
  }
}
