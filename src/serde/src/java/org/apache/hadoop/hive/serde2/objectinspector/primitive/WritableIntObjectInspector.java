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

import org.apache.hadoop.io.IntWritable;

/**
 * A WritableIntObjectInspector inspects a IntWritable Object.
 */
public class WritableIntObjectInspector extends
    AbstractPrimitiveWritableObjectInspector implements
    SettableIntObjectInspector {

  WritableIntObjectInspector() {
    super(PrimitiveObjectInspectorUtils.intTypeEntry);
  }

  @Override
  public int get(Object o) {
    return ((IntWritable) o).get();
  }

  @Override
  public Object copyObject(Object o) {
    return o == null ? null : new IntWritable(((IntWritable) o).get());
  }

  @Override
  public Object getPrimitiveJavaObject(Object o) {
    return o == null ? null : Integer.valueOf(((IntWritable) o).get());
  }

  @Override
  public Object create(int value) {
    return new IntWritable(value);
  }

  @Override
  public Object set(Object o, int value) {
    ((IntWritable) o).set(value);
    return o;
  }
}
