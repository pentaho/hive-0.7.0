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

import org.apache.hadoop.hive.serde2.lazy.LazyBoolean;
import org.apache.hadoop.hive.serde2.objectinspector.primitive.BooleanObjectInspector;
import org.apache.hadoop.hive.serde2.objectinspector.primitive.PrimitiveObjectInspectorUtils;
import org.apache.hadoop.io.BooleanWritable;

/**
 * A WritableBooleanObjectInspector inspects a BooleanWritable Object.
 */
public class LazyBooleanObjectInspector extends
    AbstractPrimitiveLazyObjectInspector<BooleanWritable> implements
    BooleanObjectInspector {

  LazyBooleanObjectInspector() {
    super(PrimitiveObjectInspectorUtils.booleanTypeEntry);
  }

  @Override
  public boolean get(Object o) {
    return getPrimitiveWritableObject(o).get();
  }

  @Override
  public Object copyObject(Object o) {
    return o == null ? null : new LazyBoolean((LazyBoolean) o);
  }

  @Override
  public Object getPrimitiveJavaObject(Object o) {
    return o == null ? null : Boolean.valueOf(get(o));
  }
}
