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

import org.apache.hadoop.io.BooleanWritable;

/**
 * A WritableBooleanObjectInspector inspects a BooleanWritable Object.
 */
public class WritableBooleanObjectInspector extends
    AbstractPrimitiveWritableObjectInspector implements
    SettableBooleanObjectInspector {

  WritableBooleanObjectInspector() {
    super(PrimitiveObjectInspectorUtils.booleanTypeEntry);
  }

  @Override
  public boolean get(Object o) {
    return ((BooleanWritable) o).get();
  }

  @Override
  public Object copyObject(Object o) {
    return o == null ? null : new BooleanWritable(((BooleanWritable) o).get());
  }

  @Override
  public Object getPrimitiveJavaObject(Object o) {
    return o == null ? null : Boolean.valueOf(((BooleanWritable) o).get());
  }

  @Override
  public Object create(boolean value) {
    return new BooleanWritable(value);
  }

  @Override
  public Object set(Object o, boolean value) {
    ((BooleanWritable) o).set(value);
    return o;
  }
}
