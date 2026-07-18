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
 * A JavaFloatObjectInspector inspects a Java Float Object.
 */
public class JavaFloatObjectInspector extends
    AbstractPrimitiveJavaObjectInspector implements
    SettableFloatObjectInspector {

  JavaFloatObjectInspector() {
    super(PrimitiveObjectInspectorUtils.floatTypeEntry);
  }

  @Override
  public Object getPrimitiveWritableObject(Object o) {
    return o == null ? null : new FloatWritable(((Float) o).floatValue());
  }

  @Override
  public float get(Object o) {
    return ((Float) o).floatValue();
  }

  @Override
  public Object create(float value) {
    return Float.valueOf(value);
  }

  @Override
  public Object set(Object o, float value) {
    return Float.valueOf(value);
  }
}
