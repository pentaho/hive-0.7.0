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
 * UDFToFloat.
 *
 */
public class UDFToFloat extends UDF {
  private FloatWritable floatWritable = new FloatWritable();

  public UDFToFloat() {
  }

  /**
   * Convert from void to a float. This is called for CAST(... AS FLOAT)
   * 
   * @param i
   *          The void value to convert
   * @return FloatWritable
   */
  public FloatWritable evaluate(NullWritable i) {
    return null;
  }

  /**
   * Convert from boolean to a float. This is called for CAST(... AS FLOAT)
   * 
   * @param i
   *          The boolean value to convert
   * @return FloatWritable
   */
  public FloatWritable evaluate(BooleanWritable i) {
    if (i == null) {
      return null;
    } else {
      floatWritable.set(i.get() ? (float) 1.0 : (float) 0.0);
      return floatWritable;
    }
  }

  /**
   * Convert from byte to a float. This is called for CAST(... AS FLOAT)
   * 
   * @param i
   *          The byte value to convert
   * @return FloatWritable
   */
  public FloatWritable evaluate(ByteWritable i) {
    if (i == null) {
      return null;
    } else {
      floatWritable.set(i.get());
      return floatWritable;
    }
  }

  /**
   * Convert from short to a float. This is called for CAST(... AS FLOAT)
   * 
   * @param i
   *          The short value to convert
   * @return FloatWritable
   */
  public FloatWritable evaluate(ShortWritable i) {
    if (i == null) {
      return null;
    } else {
      floatWritable.set(i.get());
      return floatWritable;
    }
  }

  /**
   * Convert from integer to a float. This is called for CAST(... AS FLOAT)
   * 
   * @param i
   *          The integer value to convert
   * @return FloatWritable
   */
  public FloatWritable evaluate(IntWritable i) {
    if (i == null) {
      return null;
    } else {
      floatWritable.set(i.get());
      return floatWritable;
    }
  }

  /**
   * Convert from long to a float. This is called for CAST(... AS FLOAT)
   * 
   * @param i
   *          The long value to convert
   * @return FloatWritable
   */
  public FloatWritable evaluate(LongWritable i) {
    if (i == null) {
      return null;
    } else {
      floatWritable.set(i.get());
      return floatWritable;
    }
  }

  /**
   * Convert from double to a float. This is called for CAST(... AS FLOAT)
   * 
   * @param i
   *          The double value to convert
   * @return FloatWritable
   */
  public FloatWritable evaluate(DoubleWritable i) {
    if (i == null) {
      return null;
    } else {
      floatWritable.set((float) i.get());
      return floatWritable;
    }
  }

  /**
   * Convert from string to a float. This is called for CAST(... AS FLOAT)
   * 
   * @param i
   *          The string value to convert
   * @return FloatWritable
   */
  public FloatWritable evaluate(Text i) {
    if (i == null) {
      return null;
    } else {
      try {
        floatWritable.set(Float.valueOf(i.toString()));
        return floatWritable;
      } catch (NumberFormatException e) {
        // MySQL returns 0 if the string is not a well-formed numeric value.
        // But we decided to return NULL instead, which is more conservative.
        return null;
      }
    }
  }

}
