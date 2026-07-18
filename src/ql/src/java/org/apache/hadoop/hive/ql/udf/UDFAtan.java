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
    name = "atan",
    value = "_FUNC_(x) - returns the atan (arctan) of x (x is in radians)",
    extended = "Example:\n " +
        "  > SELECT _FUNC_(0) FROM src LIMIT 1;\n" +
        "  0"
    )
public class UDFAtan extends UDF {

  @SuppressWarnings("unused")
  private static Log LOG = LogFactory.getLog(UDFAtan.class.getName());

  DoubleWritable result = new DoubleWritable();

  public UDFAtan() {
  }

  public DoubleWritable evaluate(DoubleWritable x)  {
    if (x == null) {
      return null;
    } else {
      result.set(Math.atan(x.get()));
      return result;
    }
  }

}
