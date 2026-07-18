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
 * UDFLn.
 *
 */
@Description(name = "ln",
    value = "_FUNC_(x) - Returns the natural logarithm of x",
    extended = "Example:\n"
    + "  > SELECT _FUNC_(1) FROM src LIMIT 1;\n" + "  0")
public class UDFLn extends UDF {
  private DoubleWritable result = new DoubleWritable();

  public UDFLn() {
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

}
