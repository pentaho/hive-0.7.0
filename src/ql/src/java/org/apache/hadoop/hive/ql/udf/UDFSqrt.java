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
 * Implementation of the SQRT UDF found in many databases.
 */
@Description(name = "sqrt",
    value = "_FUNC_(x) - returns the square root of x",
    extended = "Example:\n "
    + "  > SELECT _FUNC_(4) FROM src LIMIT 1;\n" + "  2")
public class UDFSqrt extends UDF {
  private DoubleWritable result = new DoubleWritable();

  public UDFSqrt() {
  }

  /**
   * Return NULL for NULL or negative inputs; otherwise, return the square root.
   */
  public DoubleWritable evaluate(DoubleWritable i) {
    if (i == null) {
      return null;
    } else if (i.get() < 0) {
      return null;
    } else {
      result.set(Math.sqrt(i.get()));
      return result;
    }
  }

}
