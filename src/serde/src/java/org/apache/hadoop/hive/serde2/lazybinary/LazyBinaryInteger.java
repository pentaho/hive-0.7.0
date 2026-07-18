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
import org.apache.hadoop.hive.serde2.lazybinary.LazyBinaryUtils.VInt;
import org.apache.hadoop.hive.serde2.objectinspector.primitive.WritableIntObjectInspector;
import org.apache.hadoop.io.IntWritable;

/**
 * LazyBinaryObject for integer which is serialized as VInt.
 * 
 * @see LazyBinaryUtils#readVInt(byte[], int, VInt)
 */
public class LazyBinaryInteger extends
    LazyBinaryPrimitive<WritableIntObjectInspector, IntWritable> {

  LazyBinaryInteger(WritableIntObjectInspector oi) {
    super(oi);
    data = new IntWritable();
  }

  LazyBinaryInteger(LazyBinaryInteger copy) {
    super(copy);
    data = new IntWritable(copy.data.get());
  }

  /**
   * The reusable vInt for decoding the integer.
   */
  VInt vInt = new LazyBinaryUtils.VInt();

  @Override
  public void init(ByteArrayRef bytes, int start, int length) {
    LazyBinaryUtils.readVInt(bytes.getData(), start, vInt);
    assert (length == vInt.length);
    data.set(vInt.value);
  }
}
