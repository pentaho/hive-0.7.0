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
 * UDFOPMinus.
 *
 */
@Description(name = "-", value = "a _FUNC_ b - Returns the difference a-b")
public class UDFOPMinus extends UDFBaseNumericOp {

  public UDFOPMinus() {
  }

  @Override
  public ByteWritable evaluate(ByteWritable a, ByteWritable b) {
    // LOG.info("Get input " + a.getClass() + ":" + a + " " + b.getClass() + ":"
    // + b);
    if ((a == null) || (b == null)) {
      return null;
    }

    byteWritable.set((byte) (a.get() - b.get()));
    return byteWritable;
  }

  @Override
  public ShortWritable evaluate(ShortWritable a, ShortWritable b) {
    // LOG.info("Get input " + a.getClass() + ":" + a + " " + b.getClass() + ":"
    // + b);
    if ((a == null) || (b == null)) {
      return null;
    }

    shortWritable.set((short) (a.get() - b.get()));
    return shortWritable;
  }

  @Override
  public IntWritable evaluate(IntWritable a, IntWritable b) {
    // LOG.info("Get input " + a.getClass() + ":" + a + " " + b.getClass() + ":"
    // + b);
    if ((a == null) || (b == null)) {
      return null;
    }

    intWritable.set((a.get() - b.get()));
    return intWritable;
  }

  @Override
  public LongWritable evaluate(LongWritable a, LongWritable b) {
    // LOG.info("Get input " + a.getClass() + ":" + a + " " + b.getClass() + ":"
    // + b);
    if ((a == null) || (b == null)) {
      return null;
    }

    longWritable.set(a.get() - b.get());
    return longWritable;
  }

  @Override
  public FloatWritable evaluate(FloatWritable a, FloatWritable b) {
    // LOG.info("Get input " + a.getClass() + ":" + a + " " + b.getClass() + ":"
    // + b);
    if ((a == null) || (b == null)) {
      return null;
    }

    floatWritable.set(a.get() - b.get());
    return floatWritable;
  }

  @Override
  public DoubleWritable evaluate(DoubleWritable a, DoubleWritable b) {
    // LOG.info("Get input " + a.getClass() + ":" + a + " " + b.getClass() + ":"
    // + b);
    if ((a == null) || (b == null)) {
      return null;
    }

    doubleWritable.set(a.get() - b.get());
    return doubleWritable;
  }
}
