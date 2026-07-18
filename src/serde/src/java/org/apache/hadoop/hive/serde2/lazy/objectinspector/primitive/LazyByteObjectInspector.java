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


package org.apache.hadoop.hive.serde2.lazy.objectinspector.primitive;

import org.apache.hadoop.hive.serde2.io.ByteWritable;
import org.apache.hadoop.hive.serde2.lazy.LazyByte;
import org.apache.hadoop.hive.serde2.objectinspector.primitive.ByteObjectInspector;
import org.apache.hadoop.hive.serde2.objectinspector.primitive.PrimitiveObjectInspectorUtils;

/**
 * A WritableByteObjectInspector inspects a ByteWritable Object.
 */
public class LazyByteObjectInspector extends
    AbstractPrimitiveLazyObjectInspector<ByteWritable> implements
    ByteObjectInspector {

  LazyByteObjectInspector() {
    super(PrimitiveObjectInspectorUtils.byteTypeEntry);
  }

  @Override
  public byte get(Object o) {
    return getPrimitiveWritableObject(o).get();
  }

  @Override
  public Object copyObject(Object o) {
    return o == null ? null : new LazyByte((LazyByte) o);
  }

  @Override
  public Object getPrimitiveJavaObject(Object o) {
    return o == null ? null : Byte.valueOf(get(o));
  }
}
