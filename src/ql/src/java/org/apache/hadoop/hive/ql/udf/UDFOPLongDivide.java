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
import org.apache.hadoop.io.LongWritable;

/**
 * UDFOPLongDivide.
 *
 */
@Description(name = "div",
    value = "a _FUNC_ b - Divide a by b rounded to the long integer",
    extended = "Example:\n"
    + "  > SELECT 3 _FUNC_ 2 FROM src LIMIT 1;\n" + "  1")
public class UDFOPLongDivide extends UDF {
  private LongWritable longWritable = new LongWritable();

  public LongWritable evaluate(LongWritable a, LongWritable b) {
    // LOG.info("Get input " + a.getClass() + ":" + a + " " + b.getClass() + ":"
    // + b);
    if ((a == null) || (b == null)) {
      return null;
    }

    longWritable.set(a.get() / b.get());
    return longWritable;
  }
}
