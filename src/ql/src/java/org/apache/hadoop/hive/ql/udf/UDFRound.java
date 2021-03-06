/*!
* Copyright 2010 - 2013 Pentaho Corporation.  All rights reserved.
*
* Licensed under the Apache License, Version 2.0 (the "License");
* you may not use this file except in compliance with the License.
* You may obtain a copy of the License at
*
* http://www.apache.org/licenses/LICENSE-2.0
*
* Unless required by applicable law or agreed to in writing, software
* distributed under the License is distributed on an "AS IS" BASIS,
* WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
* See the License for the specific language governing permissions and
* limitations under the License.
*
*/

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
