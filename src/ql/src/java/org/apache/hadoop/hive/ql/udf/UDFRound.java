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

import java.math.BigDecimal;
import java.math.RoundingMode;

import org.apache.hadoop.hive.ql.exec.Description;
import org.apache.hadoop.hive.ql.exec.UDF;
import org.apache.hadoop.hive.serde2.io.DoubleWritable;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;

/**
 * UDFRound.
 *
 */
@Description(name = "round",
    value = "_FUNC_(x[, d]) - round x to d decimal places",
    extended = "Example:\n"
    + "  > SELECT _FUNC_(12.3456, 1) FROM src LIMIT 1;\n" + "  12.3'")
public class UDFRound extends UDF {
  private DoubleWritable doubleWritable = new DoubleWritable();
  private LongWritable longWritable = new LongWritable();

  public UDFRound() {
  }

  public LongWritable evaluate(DoubleWritable n) {
    if (n == null) {
      return null;
    }
    longWritable.set(BigDecimal.valueOf(n.get()).setScale(0,
        RoundingMode.HALF_UP).longValue());
    return longWritable;
  }

  public DoubleWritable evaluate(DoubleWritable n, IntWritable i) {
    if ((n == null) || (i == null)) {
      return null;
    }
    double d = n.get();
    if (Double.isNaN(d) || Double.isInfinite(d)) {
      doubleWritable.set(d);
    } else {
      doubleWritable.set(BigDecimal.valueOf(d).setScale(i.get(),
          RoundingMode.HALF_UP).doubleValue());
    }
    return doubleWritable;
  }

}
