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
 * An AbstractWritablePrimitiveObjectInspector for a Writable object.
 */
public abstract class AbstractPrimitiveWritableObjectInspector extends
    AbstractPrimitiveObjectInspector {

  protected AbstractPrimitiveWritableObjectInspector(
      PrimitiveTypeEntry typeEntry) {
    super(typeEntry);
  }

  @Override
  public Object getPrimitiveWritableObject(Object o) {
    return o;
  }

  @Override
  public boolean preferWritable() {
    return true;
  }

}
