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
import org.apache.hadoop.hive.serde2.lazy.LazyShort;
import org.apache.hadoop.io.BooleanWritable;
import org.apache.hadoop.io.FloatWritable;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;

/**
 * UDFToShort.
 *
 */
public class UDFToShort extends UDF {
  ShortWritable shortWritable = new ShortWritable();

  public UDFToShort() {
  }

  /**
   * Convert from void to a short. This is called for CAST(... AS SMALLINT)
   * 
   * @param i
   *          The void value to convert
   * @return ShortWritable
   */
  public ShortWritable evaluate(NullWritable i) {
    return null;
  }

  /**
   * Convert from boolean to a short. This is called for CAST(... AS SMALLINT)
   * 
   * @param i
   *          The boolean value to convert
   * @return ShortWritable
   */
  public ShortWritable evaluate(BooleanWritable i) {
    if (i == null) {
      return null;
    } else {
      shortWritable.set(i.get() ? (short) 1 : (short) 0);
      return shortWritable;
    }
  }

  /**
   * Convert from byte to a short. This is called for CAST(... AS SMALLINT)
   * 
   * @param i
   *          The byte value to convert
   * @return ShortWritable
   */
  public ShortWritable evaluate(ByteWritable i) {
    if (i == null) {
      return null;
    } else {
      shortWritable.set(i.get());
      return shortWritable;
    }
  }

  /**
   * Convert from integer to a short. This is called for CAST(... AS SMALLINT)
   * 
   * @param i
   *          The integer value to convert
   * @return ShortWritable
   */
  public ShortWritable evaluate(IntWritable i) {
    if (i == null) {
      return null;
    } else {
      shortWritable.set((short) i.get());
      return shortWritable;
    }
  }

  /**
   * Convert from long to a short. This is called for CAST(... AS SMALLINT)
   * 
   * @param i
   *          The long value to convert
   * @return ShortWritable
   */
  public ShortWritable evaluate(LongWritable i) {
    if (i == null) {
      return null;
    } else {
      shortWritable.set((short) i.get());
      return shortWritable;
    }
  }

  /**
   * Convert from float to a short. This is called for CAST(... AS SMALLINT)
   * 
   * @param i
   *          The float value to convert
   * @return ShortWritable
   */
  public ShortWritable evaluate(FloatWritable i) {
    if (i == null) {
      return null;
    } else {
      shortWritable.set((short) i.get());
      return shortWritable;
    }
  }

  /**
   * Convert from double to a short. This is called for CAST(... AS SMALLINT)
   * 
   * @param i
   *          The double value to convert
   * @return ShortWritable
   */
  public ShortWritable evaluate(DoubleWritable i) {
    if (i == null) {
      return null;
    } else {
      shortWritable.set((short) i.get());
      return shortWritable;
    }
  }

  /**
   * Convert from string to a short. This is called for CAST(... AS SMALLINT)
   * 
   * @param i
   *          The string value to convert
   * @return ShortWritable
   */
  public ShortWritable evaluate(Text i) {
    if (i == null) {
      return null;
    } else {
      try {
        shortWritable.set(LazyShort.parseShort(i.getBytes(), 0, i.getLength(),
            10));
        return shortWritable;
      } catch (NumberFormatException e) {
        // MySQL returns 0 if the string is not a well-formed numeric value.
        // return Byte.valueOf(0);
        // But we decided to return NULL instead, which is more conservative.
        return null;
      }
    }
  }
}
