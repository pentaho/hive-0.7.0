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

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.hadoop.hive.ql.exec.Description;
import org.apache.hadoop.hive.ql.exec.UDF;
import org.apache.hadoop.hive.serde2.io.DoubleWritable;

@Description(
    name = "tan",
    value = "_FUNC_(x) - returns the tangent of x (x is in radians)",
    extended = "Example:\n " +
    		"  > SELECT _FUNC_(0) FROM src LIMIT 1;\n" +
    		"  1"
    )
public class UDFTan extends UDF {

  @SuppressWarnings("unused")
  private static Log LOG = LogFactory.getLog(UDFTan.class.getName());
  DoubleWritable result = new DoubleWritable();

  public UDFTan() {
  }

  /**
   * Take Tangent of a
   */
  public DoubleWritable evaluate(DoubleWritable a)  {
    if (a == null) {
      return null;
    } else {
      result.set(Math.tan(a.get()));
      return result;
    }
  }

}
