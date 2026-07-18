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

import org.apache.hadoop.hive.ql.exec.NumericOpMethodResolver;
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
public abstract class UDFBaseNumericOp extends UDF {

  /**
   * Constructor. This constructor sets the resolver to be used for comparison
   * operators. See {@link org.apache.hadoop.hive.ql.exec.UDFMethodResolver}
   */
  public UDFBaseNumericOp() {
    super(null);
    setResolver(new NumericOpMethodResolver(this.getClass()));
  }

  protected ByteWritable byteWritable = new ByteWritable();
  protected ShortWritable shortWritable = new ShortWritable();
  protected IntWritable intWritable = new IntWritable();
  protected LongWritable longWritable = new LongWritable();
  protected FloatWritable floatWritable = new FloatWritable();
  protected DoubleWritable doubleWritable = new DoubleWritable();

  public abstract ByteWritable evaluate(ByteWritable a, ByteWritable b);

  public abstract ShortWritable evaluate(ShortWritable a, ShortWritable b);

  public abstract IntWritable evaluate(IntWritable a, IntWritable b);

  public abstract LongWritable evaluate(LongWritable a, LongWritable b);

  public abstract FloatWritable evaluate(FloatWritable a, FloatWritable b);

  public abstract DoubleWritable evaluate(DoubleWritable a, DoubleWritable b);

}
