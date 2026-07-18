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
 * A JavaDoubleObjectInspector inspects a Java Double Object.
 */
public class JavaDoubleObjectInspector extends
    AbstractPrimitiveJavaObjectInspector implements
    SettableDoubleObjectInspector {

  JavaDoubleObjectInspector() {
    super(PrimitiveObjectInspectorUtils.doubleTypeEntry);
  }

  @Override
  public Object getPrimitiveWritableObject(Object o) {
    return o == null ? null : new DoubleWritable(((Double) o).doubleValue());
  }

  @Override
  public double get(Object o) {
    return ((Double) o).doubleValue();
  }

  @Override
  public Object create(double value) {
    return Double.valueOf(value);
  }

  @Override
  public Object set(Object o, double value) {
    return Double.valueOf(value);
  }
}
