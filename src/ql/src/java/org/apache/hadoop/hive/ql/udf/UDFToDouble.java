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
import org.apache.hadoop.io.BooleanWritable;
import org.apache.hadoop.io.FloatWritable;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;

/**
 * UDFToDouble.
 *
 */
public class UDFToDouble extends UDF {
  private DoubleWritable doubleWritable = new DoubleWritable();

  public UDFToDouble() {
  }

  /**
   * Convert from void to a double. This is called for CAST(... AS DOUBLE)
   * 
   * @param i
   *          The void value to convert
   * @return DoubleWritable
   */
  public DoubleWritable evaluate(NullWritable i) {
    return null;
  }

  /**
   * Convert from boolean to a double. This is called for CAST(... AS DOUBLE)
   * 
   * @param i
   *          The boolean value to convert
   * @return DoubleWritable
   */
  public DoubleWritable evaluate(BooleanWritable i) {
    if (i == null) {
      return null;
    } else {
      doubleWritable.set(i.get() ? 1.0 : 0.0);
      return doubleWritable;
    }
  }

  /**
   * Convert from boolean to a double. This is called for CAST(... AS DOUBLE)
   * 
   * @param i
   *          The byte value to convert
   * @return DoubleWritable
   */
  public DoubleWritable evaluate(ByteWritable i) {
    if (i == null) {
      return null;
    } else {
      doubleWritable.set(i.get());
      return doubleWritable;
    }
  }

  /**
   * Convert from short to a double. This is called for CAST(... AS DOUBLE)
   * 
   * @param i
   *          The short value to convert
   * @return DoubleWritable
   */
  public DoubleWritable evaluate(ShortWritable i) {
    if (i == null) {
      return null;
    } else {
      doubleWritable.set(i.get());
      return doubleWritable;
    }
  }

  /**
   * Convert from integer to a double. This is called for CAST(... AS DOUBLE)
   * 
   * @param i
   *          The integer value to convert
   * @return DoubleWritable
   */
  public DoubleWritable evaluate(IntWritable i) {
    if (i == null) {
      return null;
    } else {
      doubleWritable.set(i.get());
      return doubleWritable;
    }
  }

  /**
   * Convert from long to a double. This is called for CAST(... AS DOUBLE)
   * 
   * @param i
   *          The long value to convert
   * @return DoubleWritable
   */
  public DoubleWritable evaluate(LongWritable i) {
    if (i == null) {
      return null;
    } else {
      doubleWritable.set(i.get());
      return doubleWritable;
    }
  }

  /**
   * Convert from float to a double. This is called for CAST(... AS DOUBLE)
   * 
   * @param i
   *          The float value to convert
   * @return DoubleWritable
   */
  public DoubleWritable evaluate(FloatWritable i) {
    if (i == null) {
      return null;
    } else {
      doubleWritable.set(i.get());
      return doubleWritable;
    }
  }

  /**
   * Convert from string to a double. This is called for CAST(... AS DOUBLE)
   * 
   * @param i
   *          The string value to convert
   * @return DoubleWritable
   */
  public DoubleWritable evaluate(Text i) {
    if (i == null) {
      return null;
    } else {
      try {
        doubleWritable.set(Double.valueOf(i.toString()));
        return doubleWritable;
      } catch (NumberFormatException e) {
        // MySQL returns 0 if the string is not a well-formed double value.
        // But we decided to return NULL instead, which is more conservative.
        return null;
      }
    }
  }

}
