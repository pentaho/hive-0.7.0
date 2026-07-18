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
import org.apache.hadoop.io.LongWritable;

/**
 * UDFCeil.
 *
 */
@Description(name = "ceil,ceiling",
    value = "_FUNC_(x) - Find the smallest integer not smaller than x",
    extended = "Example:\n"
    + "  > SELECT _FUNC_(-0.1) FROM src LIMIT 1;\n"
    + "  0\n"
    + "  > SELECT _FUNC_(5) FROM src LIMIT 1;\n" + "  5")
public class UDFCeil extends UDF {
  private LongWritable longWritable = new LongWritable();

  public UDFCeil() {
  }

  public LongWritable evaluate(DoubleWritable i) {
    if (i == null) {
      return null;
    } else {
      longWritable.set((long) Math.ceil(i.get()));
      return longWritable;
    }
  }

}
