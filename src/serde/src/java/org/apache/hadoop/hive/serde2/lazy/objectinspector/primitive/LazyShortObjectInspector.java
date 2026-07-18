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

import org.apache.hadoop.hive.serde2.io.ShortWritable;
import org.apache.hadoop.hive.serde2.lazy.LazyShort;
import org.apache.hadoop.hive.serde2.objectinspector.primitive.PrimitiveObjectInspectorUtils;
import org.apache.hadoop.hive.serde2.objectinspector.primitive.ShortObjectInspector;

/**
 * A WritableShortObjectInspector inspects a ShortWritable Object.
 */
public class LazyShortObjectInspector extends
    AbstractPrimitiveLazyObjectInspector<ShortWritable> implements
    ShortObjectInspector {

  LazyShortObjectInspector() {
    super(PrimitiveObjectInspectorUtils.shortTypeEntry);
  }

  @Override
  public short get(Object o) {
    return getPrimitiveWritableObject(o).get();
  }

  @Override
  public Object copyObject(Object o) {
    return o == null ? null : new LazyShort((LazyShort) o);
  }

  @Override
  public Object getPrimitiveJavaObject(Object o) {
    return o == null ? null : Short.valueOf(get(o));
  }
}
