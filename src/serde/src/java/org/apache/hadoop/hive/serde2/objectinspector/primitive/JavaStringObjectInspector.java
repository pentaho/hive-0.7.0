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

import org.apache.hadoop.io.Text;

/**
 * A JavaStringObjectInspector inspects a Java String Object.
 */
public class JavaStringObjectInspector extends
    AbstractPrimitiveJavaObjectInspector implements
    SettableStringObjectInspector {

  JavaStringObjectInspector() {
    super(PrimitiveObjectInspectorUtils.stringTypeEntry);
  }

  @Override
  public Text getPrimitiveWritableObject(Object o) {
    return o == null ? null : new Text(((String) o));
  }

  @Override
  public String getPrimitiveJavaObject(Object o) {
    return (String) o;
  }

  @Override
  public Object create(Text value) {
    return value == null ? null : value.toString();
  }

  @Override
  public Object set(Object o, Text value) {
    return value == null ? null : value.toString();
  }

  @Override
  public Object create(String value) {
    return value;
  }

  @Override
  public Object set(Object o, String value) {
    return value;
  }
}
