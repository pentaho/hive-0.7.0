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

import org.apache.hadoop.hive.serde2.lazy.LazyLong;
import org.apache.hadoop.hive.serde2.objectinspector.primitive.LongObjectInspector;
import org.apache.hadoop.hive.serde2.objectinspector.primitive.PrimitiveObjectInspectorUtils;
import org.apache.hadoop.io.LongWritable;

/**
 * A WritableLongObjectInspector inspects a LongWritable Object.
 */
public class LazyLongObjectInspector extends
    AbstractPrimitiveLazyObjectInspector<LongWritable> implements
    LongObjectInspector {

  LazyLongObjectInspector() {
    super(PrimitiveObjectInspectorUtils.longTypeEntry);
  }

  @Override
  public long get(Object o) {
    return getPrimitiveWritableObject(o).get();
  }

  @Override
  public Object copyObject(Object o) {
    return o == null ? null : new LazyLong((LazyLong) o);
  }

  @Override
  public Object getPrimitiveJavaObject(Object o) {
    return o == null ? null : Long.valueOf(get(o));
  }
}
