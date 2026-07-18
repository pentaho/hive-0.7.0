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

import org.apache.hadoop.hive.serde2.objectinspector.primitive.PrimitiveObjectInspectorUtils.PrimitiveTypeEntry;

/**
 * An AbstractJavaPrimitiveObjectInspector for a Java object.
 */
public abstract class AbstractPrimitiveJavaObjectInspector extends
    AbstractPrimitiveObjectInspector {

  protected AbstractPrimitiveJavaObjectInspector(PrimitiveTypeEntry typeEntry) {
    super(typeEntry);
  }

  @Override
  public Object getPrimitiveJavaObject(Object o) {
    return o;
  }

  @Override
  public Object copyObject(Object o) {
    return o;
  }

  @Override
  public boolean preferWritable() {
    return false;
  }
}
