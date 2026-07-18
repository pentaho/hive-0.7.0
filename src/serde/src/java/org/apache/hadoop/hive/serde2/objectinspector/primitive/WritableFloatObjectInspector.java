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

import org.apache.hadoop.io.FloatWritable;

/**
 * A FloatObjectInspector inspects a FloatWritable Object.
 */
public class WritableFloatObjectInspector extends
    AbstractPrimitiveWritableObjectInspector implements
    SettableFloatObjectInspector {

  WritableFloatObjectInspector() {
    super(PrimitiveObjectInspectorUtils.floatTypeEntry);
  }

  @Override
  public float get(Object o) {
    return ((FloatWritable) o).get();
  }

  @Override
  public Object copyObject(Object o) {
    return o == null ? null : new FloatWritable(((FloatWritable) o).get());
  }

  @Override
  public Object getPrimitiveJavaObject(Object o) {
    return o == null ? null : Float.valueOf(((FloatWritable) o).get());
  }

  @Override
  public Object create(float value) {
    return new FloatWritable(value);
  }

  @Override
  public Object set(Object o, float value) {
    ((FloatWritable) o).set(value);
    return o;
  }
}
