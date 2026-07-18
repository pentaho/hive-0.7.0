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


package org.apache.hadoop.hive.serde2.lazy.objectinspector.primitive;

import org.apache.hadoop.hive.serde2.lazy.LazyInteger;
import org.apache.hadoop.hive.serde2.objectinspector.primitive.IntObjectInspector;
import org.apache.hadoop.hive.serde2.objectinspector.primitive.PrimitiveObjectInspectorUtils;
import org.apache.hadoop.io.IntWritable;

/**
 * A WritableIntObjectInspector inspects a IntWritable Object.
 */
public class LazyIntObjectInspector extends
    AbstractPrimitiveLazyObjectInspector<IntWritable> implements
    IntObjectInspector {

  LazyIntObjectInspector() {
    super(PrimitiveObjectInspectorUtils.intTypeEntry);
  }

  @Override
  public int get(Object o) {
    return getPrimitiveWritableObject(o).get();
  }

  @Override
  public Object copyObject(Object o) {
    return o == null ? null : new LazyInteger((LazyInteger) o);
  }

  @Override
  public Object getPrimitiveJavaObject(Object o) {
    return o == null ? null : Integer.valueOf(get(o));
  }
}
