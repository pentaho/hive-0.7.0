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

import org.apache.hadoop.io.LongWritable;

/**
 * A WritableLongObjectInspector inspects a LongWritable Object.
 */
public class WritableLongObjectInspector extends
    AbstractPrimitiveWritableObjectInspector implements
    SettableLongObjectInspector {

  WritableLongObjectInspector() {
    super(PrimitiveObjectInspectorUtils.longTypeEntry);
  }

  @Override
  public long get(Object o) {
    return ((LongWritable) o).get();
  }

  @Override
  public Object copyObject(Object o) {
    return o == null ? null : new LongWritable(((LongWritable) o).get());
  }

  @Override
  public Object getPrimitiveJavaObject(Object o) {
    return o == null ? null : Long.valueOf(((LongWritable) o).get());
  }

  @Override
  public Object create(long value) {
    return new LongWritable(value);
  }

  @Override
  public Object set(Object o, long value) {
    ((LongWritable) o).set(value);
    return o;
  }

}
