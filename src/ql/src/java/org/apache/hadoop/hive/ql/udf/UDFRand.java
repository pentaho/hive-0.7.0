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

import java.util.Random;

import org.apache.hadoop.hive.ql.exec.Description;
import org.apache.hadoop.hive.ql.exec.UDF;
import org.apache.hadoop.hive.serde2.io.DoubleWritable;
import org.apache.hadoop.io.LongWritable;

/**
 * UDFRand.
 *
 */
@Description(name = "rand",
    value = "_FUNC_([seed]) - Returns a pseudorandom number between 0 and 1")
@UDFType(deterministic = false)
public class UDFRand extends UDF {
  private Random random;

  private DoubleWritable result = new DoubleWritable();

  public UDFRand() {
  }

  public DoubleWritable evaluate() {
    if (random == null) {
      random = new Random();
    }
    result.set(random.nextDouble());
    return result;
  }

  public DoubleWritable evaluate(LongWritable seed) {
    if (random == null) {
      random = new Random(seed.get());
    }
    result.set(random.nextDouble());
    return result;
  }

}
