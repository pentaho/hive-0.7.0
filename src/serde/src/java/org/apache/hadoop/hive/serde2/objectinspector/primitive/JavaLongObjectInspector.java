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
 * A JavaLongObjectInspector inspects a Java Long Object.
 */
public class JavaLongObjectInspector extends
    AbstractPrimitiveJavaObjectInspector implements SettableLongObjectInspector {

  JavaLongObjectInspector() {
    super(PrimitiveObjectInspectorUtils.longTypeEntry);
  }

  @Override
  public Object getPrimitiveWritableObject(Object o) {
    return o == null ? null : new LongWritable(((Long) o).longValue());
  }

  @Override
  public long get(Object o) {
    return ((Long) o).longValue();
  }

  @Override
  public Object create(long value) {
    return Long.valueOf(value);
  }

  @Override
  public Object set(Object o, long value) {
    return Long.valueOf(value);
  }
}
