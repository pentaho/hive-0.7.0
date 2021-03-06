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

import org.apache.hadoop.hive.ql.exec.Description;
import org.apache.hadoop.hive.serde2.io.ByteWritable;
import org.apache.hadoop.hive.serde2.io.DoubleWritable;
import org.apache.hadoop.hive.serde2.io.ShortWritable;
import org.apache.hadoop.io.FloatWritable;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;

/**
 * UDFOPPositive.
 *
 */
@Description(name = "positive", value = "_FUNC_ a - Returns a")
public class UDFOPPositive extends UDFBaseNumericUnaryOp {

  public UDFOPPositive() {
  }

  @Override
  public ByteWritable evaluate(ByteWritable a) {
    return a;
  }

  @Override
  public ShortWritable evaluate(ShortWritable a) {
    return a;
  }

  @Override
  public IntWritable evaluate(IntWritable a) {
    return a;
  }

  @Override
  public LongWritable evaluate(LongWritable a) {
    return a;
  }

  @Override
  public FloatWritable evaluate(FloatWritable a) {
    return a;
  }

  @Override
  public DoubleWritable evaluate(DoubleWritable a) {
    return a;
  }

}
