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
import org.apache.hadoop.hive.serde2.io.ByteWritable;
import org.apache.hadoop.hive.serde2.io.DoubleWritable;
import org.apache.hadoop.hive.serde2.io.ShortWritable;
import org.apache.hadoop.io.FloatWritable;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;

/**
 * UDFOPPositive.
 *
 */
@Description(name = "positive", value = "_FUNC_ a - Returns a")
public class UDFOPPositive extends UDFBaseNumericUnaryOp {

  public UDFOPPositive() {
  }

  @Override
  public ByteWritable evaluate(ByteWritable a) {
    return a;
  }

  @Override
  public ShortWritable evaluate(ShortWritable a) {
    return a;
  }

  @Override
  public IntWritable evaluate(IntWritable a) {
    return a;
  }

  @Override
  public LongWritable evaluate(LongWritable a) {
    return a;
  }

  @Override
  public FloatWritable evaluate(FloatWritable a) {
    return a;
  }

  @Override
  public DoubleWritable evaluate(DoubleWritable a) {
    return a;
  }

}
