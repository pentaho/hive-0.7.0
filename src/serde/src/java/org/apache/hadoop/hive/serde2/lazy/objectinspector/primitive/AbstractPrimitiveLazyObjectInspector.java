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

import org.apache.hadoop.hive.serde2.lazy.LazyPrimitive;
import org.apache.hadoop.hive.serde2.objectinspector.primitive.AbstractPrimitiveObjectInspector;
import org.apache.hadoop.hive.serde2.objectinspector.primitive.PrimitiveObjectInspectorUtils.PrimitiveTypeEntry;
import org.apache.hadoop.io.Writable;

/**
 * An AbstractPrimitiveLazyObjectInspector for a LazyPrimitive object.
 */
public abstract class AbstractPrimitiveLazyObjectInspector<T extends Writable>
    extends AbstractPrimitiveObjectInspector {

  protected AbstractPrimitiveLazyObjectInspector(PrimitiveTypeEntry typeEntry) {
    super(typeEntry);
  }

  @Override
  public T getPrimitiveWritableObject(Object o) {
    return o == null ? null : ((LazyPrimitive<?, T>) o).getWritableObject();
  }

  @Override
  public boolean preferWritable() {
    return true;
  }

}
