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

import org.apache.hadoop.hive.serde2.io.DoubleWritable;

/**
 * A WritableDoubleObjectInspector inspects a DoubleWritable Object.
 */
public class WritableDoubleObjectInspector extends
    AbstractPrimitiveWritableObjectInspector implements
    SettableDoubleObjectInspector {

  WritableDoubleObjectInspector() {
    super(PrimitiveObjectInspectorUtils.doubleTypeEntry);
  }

  @Override
  public double get(Object o) {
    return ((DoubleWritable) o).get();
  }

  @Override
  public Object copyObject(Object o) {
    return o == null ? null : new DoubleWritable(((DoubleWritable) o).get());
  }

  @Override
  public Object getPrimitiveJavaObject(Object o) {
    return o == null ? null : Double.valueOf(((DoubleWritable) o).get());
  }

  @Override
  public Object create(double value) {
    return new DoubleWritable(value);
  }

  @Override
  public Object set(Object o, double value) {
    ((DoubleWritable) o).set(value);
    return o;
  }

}
