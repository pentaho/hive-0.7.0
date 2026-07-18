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


package org.apache.hadoop.hive.serde2.lazybinary;

import org.apache.hadoop.hive.serde2.lazy.ByteArrayRef;
import org.apache.hadoop.hive.serde2.objectinspector.primitive.WritableVoidObjectInspector;
import org.apache.hadoop.io.Writable;

/**
 * LazyBinaryVoid for NULL which is a placeholder class and it does not need to store any data.
 */
public class LazyBinaryVoid extends
    LazyBinaryPrimitive<WritableVoidObjectInspector, Writable> {

  LazyBinaryVoid(WritableVoidObjectInspector oi) {
    super(oi);
  }

  LazyBinaryVoid(LazyBinaryVoid copy) {
    super(copy);
  }

  @Override
  public void init(ByteArrayRef bytes, int start, int length) {
  }
}
