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
 * UDFAcos.
 *
 */
@Description(name = "acos", value = "_FUNC_(x) - returns the arc cosine of x if -1<=x<=1 or "
    + "NULL otherwise", extended = "Example:\n"
    + "  > SELECT _FUNC_(1) FROM src LIMIT 1;\n" + "  0\n"
    + "  > SELECT _FUNC_(2) FROM src LIMIT 1;\n" + "  NULL")
public class UDFAcos extends UDF {
  private DoubleWritable result = new DoubleWritable();

  public UDFAcos() {
  }

  /**
   * Take Arc Cosine of a in radians.
   */
  public DoubleWritable evaluate(DoubleWritable a) {
    if (a == null) {
      return null;
    } else {
      result.set(Math.acos(a.get()));
      return result;
    }
  }

}
