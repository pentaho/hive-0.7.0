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

/**
 * UDFLog2.
 *
 */
@Description(name = "log2",
    value = "_FUNC_(x) - Returns the logarithm of x with base 2",
    extended = "Example:\n"
    + "  > SELECT _FUNC_(2) FROM src LIMIT 1;\n" + "  1")
public class UDFLog2 extends UDF {
  private static double log2 = Math.log(2.0);

  private DoubleWritable result = new DoubleWritable();

  public UDFLog2() {
  }

  /**
   * Returns the logarithm of "a" with base 2.
   */
  public DoubleWritable evaluate(DoubleWritable a) {
    if (a == null || a.get() <= 0.0) {
      return null;
    } else {
      result.set(Math.log(a.get()) / log2);
      return result;
    }
  }

}
