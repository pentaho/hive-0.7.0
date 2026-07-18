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


package org.apache.hadoop.hive.serde2.lazy;

import org.apache.hadoop.hive.serde2.lazy.objectinspector.primitive.LazyBooleanObjectInspector;
import org.apache.hadoop.io.BooleanWritable;

/**
 * LazyObject for storing a value of boolean.
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
public class LazyBoolean extends
    LazyPrimitive<LazyBooleanObjectInspector, BooleanWritable> {

  public LazyBoolean(LazyBooleanObjectInspector oi) {
    super(oi);
    data = new BooleanWritable();
  }

  public LazyBoolean(LazyBoolean copy) {
    super(copy);
    data = new BooleanWritable(copy.data.get());
  }

  @Override
  public void init(ByteArrayRef bytes, int start, int length) {
    if (length == 4 && Character.toUpperCase(bytes.getData()[start]) == 'T'
        && Character.toUpperCase(bytes.getData()[start + 1]) == 'R'
        && Character.toUpperCase(bytes.getData()[start + 2]) == 'U'
        && Character.toUpperCase(bytes.getData()[start + 3]) == 'E') {
      data.set(true);
      isNull = false;
    } else if (length == 5
        && Character.toUpperCase(bytes.getData()[start]) == 'F'
        && Character.toUpperCase(bytes.getData()[start + 1]) == 'A'
        && Character.toUpperCase(bytes.getData()[start + 2]) == 'L'
        && Character.toUpperCase(bytes.getData()[start + 3]) == 'S'
        && Character.toUpperCase(bytes.getData()[start + 4]) == 'E') {
      data.set(false);
      isNull = false;
    } else {
      isNull = true;
    }
  }

}
