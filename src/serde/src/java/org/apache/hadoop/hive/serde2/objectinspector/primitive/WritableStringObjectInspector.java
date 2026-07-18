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
 * A WritableStringObjectInspector inspects a Text Object.
 */
public class WritableStringObjectInspector extends
    AbstractPrimitiveWritableObjectInspector implements
    SettableStringObjectInspector {

  WritableStringObjectInspector() {
    super(PrimitiveObjectInspectorUtils.stringTypeEntry);
  }

  @Override
  public Object copyObject(Object o) {
    return o == null ? null : new Text((Text) o);
  }

  @Override
  public Text getPrimitiveWritableObject(Object o) {
    return o == null ? null : (Text) o;
  }

  @Override
  public String getPrimitiveJavaObject(Object o) {
    return o == null ? null : ((Text) o).toString();
  }

  @Override
  public Object create(Text value) {
    Text r = new Text();
    if (value != null) {
      r.set(value);
    }
    return r;
  }

  @Override
  public Object create(String value) {
    Text r = new Text();
    if (value != null) {
      r.set(value);
    }
    return r;
  }

  @Override
  public Object set(Object o, Text value) {
    Text r = (Text) o;
    if (value != null) {
      r.set(value);
    }
    return o;
  }

  @Override
  public Object set(Object o, String value) {
    Text r = (Text) o;
    if (value != null) {
      r.set(value);
    }
    return o;
  }

}
