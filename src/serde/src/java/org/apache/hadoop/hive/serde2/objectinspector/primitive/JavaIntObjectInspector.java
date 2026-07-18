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

import org.apache.hadoop.io.IntWritable;

/**
 * A JavaIntObjectInspector inspects a Java Integer Object.
 */
public class JavaIntObjectInspector extends
    AbstractPrimitiveJavaObjectInspector implements SettableIntObjectInspector {

  JavaIntObjectInspector() {
    super(PrimitiveObjectInspectorUtils.intTypeEntry);
  }

  @Override
  public Object getPrimitiveWritableObject(Object o) {
    return o == null ? null : new IntWritable(((Integer) o).intValue());
  }

  @Override
  public int get(Object o) {
    return ((Integer) o).intValue();
  }

  @Override
  public Object create(int value) {
    return Integer.valueOf(value);
  }

  @Override
  public Object set(Object o, int value) {
    return Integer.valueOf(value);
  }
}
