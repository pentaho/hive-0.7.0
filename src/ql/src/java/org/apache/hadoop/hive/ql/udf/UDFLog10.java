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
 * UDFLog10.
 *
 */
@Description(name = "log10",
    value = "_FUNC_(x) - Returns the logarithm of x with base 10",
    extended = "Example:\n"
    + "  > SELECT _FUNC_(10) FROM src LIMIT 1;\n" + "  1")
public class UDFLog10 extends UDF {
  private static double log10 = Math.log(10.0);

  private DoubleWritable result = new DoubleWritable();

  public UDFLog10() {
  }

  /**
   * Returns the logarithm of "a" with base 10.
   */
  public DoubleWritable evaluate(DoubleWritable a) {
    if (a == null || a.get() <= 0.0) {
      return null;
    } else {
      result.set(Math.log(a.get()) / log10);
      return result;
    }
  }

}
