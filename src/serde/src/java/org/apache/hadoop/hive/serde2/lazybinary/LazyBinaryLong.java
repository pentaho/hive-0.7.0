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
import org.apache.hadoop.hive.serde2.lazybinary.LazyBinaryUtils.VLong;
import org.apache.hadoop.hive.serde2.objectinspector.primitive.WritableLongObjectInspector;
import org.apache.hadoop.io.LongWritable;

/**
 * LazyBinaryObject for long which stores as VLong.
 * 
 * @see LazyBinaryUtils#readVLong(byte[], int, VLong)
 */
public class LazyBinaryLong extends
    LazyBinaryPrimitive<WritableLongObjectInspector, LongWritable> {

  LazyBinaryLong(WritableLongObjectInspector oi) {
    super(oi);
    data = new LongWritable();
  }

  LazyBinaryLong(LazyBinaryLong copy) {
    super(copy);
    data = new LongWritable(copy.data.get());
  }

  /**
   * The reusable vLong for decoding the long.
   */
  VLong vLong = new LazyBinaryUtils.VLong();

  @Override
  public void init(ByteArrayRef bytes, int start, int length) {
    LazyBinaryUtils.readVLong(bytes.getData(), start, vLong);
    assert (length == vLong.length);
    data.set(vLong.value);
  }
}
