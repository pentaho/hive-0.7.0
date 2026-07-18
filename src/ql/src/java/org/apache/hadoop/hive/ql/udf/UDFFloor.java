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


package org.apache.hadoop.hive.ql.udf;

import org.apache.hadoop.hive.ql.exec.Description;
import org.apache.hadoop.hive.ql.exec.UDF;
import org.apache.hadoop.hive.serde2.io.DoubleWritable;
import org.apache.hadoop.io.LongWritable;

/**
 * UDFFloor.
 *
 */
@Description(name = "floor",
    value = "_FUNC_(x) - Find the largest integer not greater than x",
    extended = "Example:\n"
    + "  > SELECT _FUNC_(-0.1) FROM src LIMIT 1;\n"
    + "  -1\n"
    + "  > SELECT _FUNC_(5) FROM src LIMIT 1;\n" + "  5")
public class UDFFloor extends UDF {
  private LongWritable result = new LongWritable();

  public UDFFloor() {
  }

  public LongWritable evaluate(DoubleWritable i) {
    if (i == null) {
      return null;
    } else {
      result.set((long) Math.floor(i.get()));
      return result;
    }
  }

}
