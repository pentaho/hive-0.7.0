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

import org.apache.hadoop.hive.serde2.io.ShortWritable;

/**
 * A WritableShortObjectInspector inspects a ShortWritable Object.
 */
public class WritableShortObjectInspector extends
    AbstractPrimitiveWritableObjectInspector implements
    SettableShortObjectInspector {

  WritableShortObjectInspector() {
    super(PrimitiveObjectInspectorUtils.shortTypeEntry);
  }

  @Override
  public short get(Object o) {
    return ((ShortWritable) o).get();
  }

  @Override
  public Object copyObject(Object o) {
    return o == null ? null : new ShortWritable(((ShortWritable) o).get());
  }

  @Override
  public Object getPrimitiveJavaObject(Object o) {
    return o == null ? null : Short.valueOf(((ShortWritable) o).get());
  }

  @Override
  public Object create(short value) {
    return new ShortWritable(value);
  }

  @Override
  public Object set(Object o, short value) {
    ((ShortWritable) o).set(value);
    return o;
  }

}
