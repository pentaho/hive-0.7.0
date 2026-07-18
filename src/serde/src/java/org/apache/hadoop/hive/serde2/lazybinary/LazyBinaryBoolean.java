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
import org.apache.hadoop.hive.serde2.objectinspector.primitive.WritableBooleanObjectInspector;
import org.apache.hadoop.io.BooleanWritable;

/**
 * LazyBinaryObject for boolean which takes one byte
 * 
 * <p>
 * Part of the code is adapted from Apache Harmony Project.
 * 
 * As with the specification, this implementation relied on code laid out in <a
 * href="http://www.hackersdelight.org/">Henry S. Warren, Jr.'s Hacker's
 * Delight, (Addison Wesley, 2002)</a> as well as <a
 * href="http://aggregate.org/MAGIC/">The Aggregate's Magic Algorithms</a>.
 * </p>
 * 
 */
public class LazyBinaryBoolean extends
    LazyBinaryPrimitive<WritableBooleanObjectInspector, BooleanWritable> {

  public LazyBinaryBoolean(WritableBooleanObjectInspector oi) {
    super(oi);
    data = new BooleanWritable();
  }

  public LazyBinaryBoolean(LazyBinaryBoolean copy) {
    super(copy);
    data = new BooleanWritable(copy.data.get());
  }

  @Override
  public void init(ByteArrayRef bytes, int start, int length) {
    assert (1 == length);
    byte val = bytes.getData()[start];
    if (val == 0) {
      data.set(false);
    } else if (val == 1) {
      data.set(true);
    }
  }
}
