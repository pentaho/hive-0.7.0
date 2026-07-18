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

import org.apache.hadoop.hive.serde2.lazy.LazyString;
import org.apache.hadoop.hive.serde2.objectinspector.primitive.PrimitiveObjectInspectorUtils;
import org.apache.hadoop.hive.serde2.objectinspector.primitive.StringObjectInspector;
import org.apache.hadoop.io.Text;

/**
 * A WritableStringObjectInspector inspects a Text Object.
 */
public class LazyStringObjectInspector extends
    AbstractPrimitiveLazyObjectInspector<Text> implements StringObjectInspector {

  boolean escaped;
  byte escapeChar;

  LazyStringObjectInspector(boolean escaped, byte escapeChar) {
    super(PrimitiveObjectInspectorUtils.stringTypeEntry);
    this.escaped = escaped;
    this.escapeChar = escapeChar;
  }

  @Override
  public Object copyObject(Object o) {
    return o == null ? null : new LazyString((LazyString) o);
  }

  @Override
  public Text getPrimitiveWritableObject(Object o) {
    return o == null ? null : ((LazyString) o).getWritableObject();
  }

  @Override
  public String getPrimitiveJavaObject(Object o) {
    return o == null ? null : ((LazyString) o).getWritableObject().toString();
  }

  public boolean isEscaped() {
    return escaped;
  }

  public byte getEscapeChar() {
    return escapeChar;
  }

}
