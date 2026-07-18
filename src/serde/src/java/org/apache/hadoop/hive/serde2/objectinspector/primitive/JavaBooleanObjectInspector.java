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
 * A JavaBooleanObjectInspector inspects a Java Boolean Object.
 */
public class JavaBooleanObjectInspector extends
    AbstractPrimitiveJavaObjectInspector implements
    SettableBooleanObjectInspector {

  JavaBooleanObjectInspector() {
    super(PrimitiveObjectInspectorUtils.booleanTypeEntry);
  }

  @Override
  public Object getPrimitiveWritableObject(Object o) {
    return o == null ? null : new BooleanWritable(((Boolean) o).booleanValue());
  }

  @Override
  public boolean get(Object o) {
    return ((Boolean) o).booleanValue();
  }

  @Override
  public Object create(boolean value) {
    return value ? Boolean.TRUE : Boolean.FALSE;
  }

  @Override
  public Object set(Object o, boolean value) {
    return value ? Boolean.TRUE : Boolean.FALSE;
  }
}
