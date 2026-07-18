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

import org.apache.hadoop.hive.ql.exec.UDF;
import org.apache.hadoop.hive.serde2.io.ByteWritable;
import org.apache.hadoop.hive.serde2.io.DoubleWritable;
import org.apache.hadoop.hive.serde2.io.ShortWritable;
import org.apache.hadoop.io.FloatWritable;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;

/**
 * Base class for numeric operators like +, -, / etc. All these operators share
 * a common method resolver (NumericOpMethodResolver).
 */
public abstract class UDFBaseNumericUnaryOp extends UDF {

  /**
   * Constructor.
   */
  public UDFBaseNumericUnaryOp() {
    super();
  }

  protected ByteWritable byteWritable = new ByteWritable();
  protected ShortWritable shortWritable = new ShortWritable();
  protected IntWritable intWritable = new IntWritable();
  protected LongWritable longWritable = new LongWritable();
  protected FloatWritable floatWritable = new FloatWritable();
  protected DoubleWritable doubleWritable = new DoubleWritable();

  public abstract ByteWritable evaluate(ByteWritable a);

  public abstract ShortWritable evaluate(ShortWritable a);

  public abstract IntWritable evaluate(IntWritable a);

  public abstract LongWritable evaluate(LongWritable a);

  public abstract FloatWritable evaluate(FloatWritable a);

  public abstract DoubleWritable evaluate(DoubleWritable a);

}
