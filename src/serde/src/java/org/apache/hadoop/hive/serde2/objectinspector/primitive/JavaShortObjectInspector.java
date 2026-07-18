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

import org.apache.hadoop.hive.serde2.io.ShortWritable;

/**
 * A JavaShortObjectInspector inspects a Java Short Object.
 */
public class JavaShortObjectInspector extends
    AbstractPrimitiveJavaObjectInspector implements
    SettableShortObjectInspector {

  JavaShortObjectInspector() {
    super(PrimitiveObjectInspectorUtils.shortTypeEntry);
  }

  @Override
  public Object getPrimitiveWritableObject(Object o) {
    return o == null ? null : new ShortWritable(((Short) o).shortValue());
  }

  @Override
  public short get(Object o) {
    return ((Short) o).shortValue();
  }

  @Override
  public Object create(short value) {
    return Short.valueOf(value);
  }

  @Override
  public Object set(Object o, short value) {
    return Short.valueOf(value);
  }
}
