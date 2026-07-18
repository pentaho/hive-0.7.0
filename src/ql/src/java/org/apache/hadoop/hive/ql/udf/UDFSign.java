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
    name = "sign",
    value = "_FUNC_(x) - returns the sign of x )",
    extended = "Example:\n " +
    		"  > SELECT _FUNC_(40) FROM src LIMIT 1;\n" +
    		"  1"
    )
public class UDFSign extends UDF {

  @SuppressWarnings("unused")
  private static Log LOG = LogFactory.getLog(UDFSign.class.getName());
  DoubleWritable result = new DoubleWritable();

  public UDFSign() {
  }

  /**
   * Take sign of a
   */
  public DoubleWritable evaluate(DoubleWritable a)  {
    if (a == null) {
      return null;
    }
    if (a.get()==0) {
      result.set(0);
    }
    else if (a.get()>0) {
      result.set(1);
    }
    else {
      result.set(-1);
    }
    return result;
  }

}
