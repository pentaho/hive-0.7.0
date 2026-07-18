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
 * UDFExp.
 *
 */
@Description(name = "exp",
    value = "_FUNC_(x) - Returns e to the power of x",
    extended = "Example:\n "
    + "  > SELECT _FUNC_(0) FROM src LIMIT 1;\n" + "  1")
public class UDFExp extends UDF {
  private DoubleWritable result = new DoubleWritable();

  public UDFExp() {
  }

  /**
   * Raise e (the base of natural logarithm) to the power of a.
   */
  public DoubleWritable evaluate(DoubleWritable a) {
    if (a == null) {
      return null;
    } else {
      result.set(Math.exp(a.get()));
      return result;
    }
  }

}
