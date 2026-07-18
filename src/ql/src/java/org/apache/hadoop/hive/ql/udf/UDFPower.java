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
 * UDFPower.
 *
 */
@Description(name = "power,pow",
    value = "_FUNC_(x1, x2) - raise x1 to the power of x2",
    extended = "Example:\n"
    + "  > SELECT _FUNC_(2, 3) FROM src LIMIT 1;\n" + "  8")
public class UDFPower extends UDF {
  private DoubleWritable result = new DoubleWritable();

  public UDFPower() {
  }

  /**
   * Raise a to the power of b.
   */
  public DoubleWritable evaluate(DoubleWritable a, DoubleWritable b) {
    if (a == null || b == null) {
      return null;
    } else {
      result.set(Math.pow(a.get(), b.get()));
      return result;
    }
  }

}
