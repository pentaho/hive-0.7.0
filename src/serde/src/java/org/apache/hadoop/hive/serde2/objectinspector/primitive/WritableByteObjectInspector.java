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


package org.apache.hadoop.hive.serde2.objectinspector.primitive;

import org.apache.hadoop.hive.serde2.io.ByteWritable;

/**
 * A WritableByteObjectInspector inspects a ByteWritable Object.
 */
public class WritableByteObjectInspector extends
    AbstractPrimitiveWritableObjectInspector implements
    SettableByteObjectInspector {

  WritableByteObjectInspector() {
    super(PrimitiveObjectInspectorUtils.byteTypeEntry);
  }

  @Override
  public byte get(Object o) {
    return ((ByteWritable) o).get();
  }

  @Override
  public Object copyObject(Object o) {
    return o == null ? null : new ByteWritable(((ByteWritable) o).get());
  }

  @Override
  public Object getPrimitiveJavaObject(Object o) {
    return o == null ? null : Byte.valueOf(((ByteWritable) o).get());
  }

  @Override
  public Object create(byte value) {
    return new ByteWritable(value);
  }

  @Override
  public Object set(Object o, byte value) {
    ((ByteWritable) o).set(value);
    return o;
  }
}
