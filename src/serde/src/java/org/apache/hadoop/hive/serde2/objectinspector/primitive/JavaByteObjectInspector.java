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
 * A JavaByteObjectInspector inspects a Java Byte Object.
 */
public class JavaByteObjectInspector extends
    AbstractPrimitiveJavaObjectInspector implements SettableByteObjectInspector {

  JavaByteObjectInspector() {
    super(PrimitiveObjectInspectorUtils.byteTypeEntry);
  }

  @Override
  public Object getPrimitiveWritableObject(Object o) {
    return o == null ? null : new ByteWritable(((Byte) o).byteValue());
  }

  @Override
  public byte get(Object o) {
    return ((Byte) o).byteValue();
  }

  @Override
  public Object create(byte value) {
    return Byte.valueOf(value);
  }

  @Override
  public Object set(Object o, byte value) {
    return Byte.valueOf(value);
  }
}
