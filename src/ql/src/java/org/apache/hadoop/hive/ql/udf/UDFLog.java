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
 * UDFLog.
 *
 */
@Description(name = "log",
    value = "_FUNC_([b], x) - Returns the logarithm of x with base b",
    extended = "Example:\n"
    + "  > SELECT _FUNC_(13, 13) FROM src LIMIT 1;\n" + "  1")
public class UDFLog extends UDF {
  private DoubleWritable result = new DoubleWritable();

  public UDFLog() {
  }

  /**
   * Returns the natural logarithm of "a".
   */
  public DoubleWritable evaluate(DoubleWritable a) {
    if (a == null || a.get() <= 0.0) {
      return null;
    } else {
      result.set(Math.log(a.get()));
      return result;
    }
  }

  /**
   * Returns the logarithm of "a" with base "base".
   */
  public DoubleWritable evaluate(DoubleWritable base, DoubleWritable a) {
    if (a == null || a.get() <= 0.0 || base == null || base.get() <= 1.0) {
      return null;
    } else {
      result.set(Math.log(a.get()) / Math.log(base.get()));
      return result;
    }
  }

}
