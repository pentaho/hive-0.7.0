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

/**
 * A WritableVoidObjectInspector inspects a NullWritable Object.
 */
public class WritableVoidObjectInspector extends
    AbstractPrimitiveWritableObjectInspector implements VoidObjectInspector {

  WritableVoidObjectInspector() {
    super(PrimitiveObjectInspectorUtils.voidTypeEntry);
  }

  @Override
  public Object copyObject(Object o) {
    return o;
  }

  @Override
  public Object getPrimitiveJavaObject(Object o) {
    throw new RuntimeException("Internal error: cannot create Void object.");
  }
}
