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

import org.apache.hadoop.io.NullWritable;

/**
 * A JavaVoidObjectInspector inspects a Java Void Object.
 */
public class JavaVoidObjectInspector extends
    AbstractPrimitiveJavaObjectInspector implements VoidObjectInspector {

  JavaVoidObjectInspector() {
    super(PrimitiveObjectInspectorUtils.voidTypeEntry);
  }

  @Override
  public Object getPrimitiveWritableObject(Object o) {
    return NullWritable.get();
  }

}
