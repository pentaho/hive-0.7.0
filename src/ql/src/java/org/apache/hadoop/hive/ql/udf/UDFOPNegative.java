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
 * UDFOPNegative.
 *
 */
@Description(name = "-", value = "_FUNC_ a - Returns -a")
public class UDFOPNegative extends UDFBaseNumericUnaryOp {

  public UDFOPNegative() {
  }

  @Override
  public ByteWritable evaluate(ByteWritable a) {
    if (a == null) {
      return null;
    }
    byteWritable.set((byte) -a.get());
    return byteWritable;
  }

  @Override
  public ShortWritable evaluate(ShortWritable a) {
    if (a == null) {
      return null;
    }
    shortWritable.set((short) -a.get());
    return shortWritable;
  }

  @Override
  public IntWritable evaluate(IntWritable a) {
    if (a == null) {
      return null;
    }
    intWritable.set(-a.get());
    return intWritable;
  }

  @Override
  public LongWritable evaluate(LongWritable a) {
    if (a == null) {
      return null;
    }
    longWritable.set(-a.get());
    return longWritable;
  }

  @Override
  public FloatWritable evaluate(FloatWritable a) {
    if (a == null) {
      return null;
    }
    floatWritable.set(-a.get());
    return floatWritable;
  }

  @Override
  public DoubleWritable evaluate(DoubleWritable a) {
    if (a == null) {
      return null;
    }
    doubleWritable.set(-a.get());
    return doubleWritable;
  }

}
