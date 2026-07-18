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

@Description (
  name="PI",
  value="_FUNC_() - returns pi ",
  extended = "Example:\n"+
     " > SELECT _FUNC_() FROM src LIMIT 1;\n" +
     " 3.14159..."
   )
public class UDFPI extends UDF {
  @SuppressWarnings("unused")
  private static Log LOG = LogFactory.getLog(UDFPI.class.getName() );
  DoubleWritable result = new DoubleWritable();

  public UDFPI() {
    result.set(Math.PI);
  }

  public DoubleWritable evaluate() {
    return result;
  }
}
