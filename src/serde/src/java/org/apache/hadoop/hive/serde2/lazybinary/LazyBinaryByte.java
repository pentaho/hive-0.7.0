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

import org.apache.hadoop.hive.serde2.io.ByteWritable;
import org.apache.hadoop.hive.serde2.lazy.ByteArrayRef;
import org.apache.hadoop.hive.serde2.objectinspector.primitive.WritableByteObjectInspector;

/**
 * LazyBinaryObject for byte which takes one byte.
 */
public class LazyBinaryByte extends
    LazyBinaryPrimitive<WritableByteObjectInspector, ByteWritable> {

  LazyBinaryByte(WritableByteObjectInspector oi) {
    super(oi);
    data = new ByteWritable();
  }

  LazyBinaryByte(LazyBinaryByte copy) {
    super(copy);
    data = new ByteWritable(copy.data.get());
  }

  @Override
  public void init(ByteArrayRef bytes, int start, int length) {
    assert (1 == length);
    data.set(bytes.getData()[start]);
  }
}
