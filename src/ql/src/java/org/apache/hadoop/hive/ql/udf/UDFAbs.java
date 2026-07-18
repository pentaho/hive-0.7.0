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
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;

/**
 * UDFAbs.
 *
 */
@Description(name = "abs",
    value = "_FUNC_(x) - returns the absolute value of x",
    extended = "Example:\n"
    + "  > SELECT _FUNC_(0) FROM src LIMIT 1;\n"
    + "  0\n"
    + "  > SELECT _FUNC_(-5) FROM src LIMIT 1;\n" + "  5")
public class UDFAbs extends UDF {
  private final DoubleWritable resultDouble = new DoubleWritable();
  private final LongWritable resultLong = new LongWritable();
  private final IntWritable resultInt = new IntWritable();

  public DoubleWritable evaluate(DoubleWritable n) {
    if (n == null) {
      return null;
    }

    resultDouble.set(Math.abs(n.get()));

    return resultDouble;
  }

  public LongWritable evaluate(LongWritable n) {
    if (n == null) {
      return null;
    }

    resultLong.set(Math.abs(n.get()));

    return resultLong;
  }

  public IntWritable evaluate(IntWritable n) {
    if (n == null) {
      return null;
    }

    resultInt.set(Math.abs(n.get()));

    return resultInt;
  }
}
