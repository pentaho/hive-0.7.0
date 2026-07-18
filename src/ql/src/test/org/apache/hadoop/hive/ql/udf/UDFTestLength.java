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

import org.apache.hadoop.hive.ql.exec.UDF;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;

/**
 * A UDF for testing, which evaluates the length of a string.
 */
public class UDFTestLength extends UDF {

  IntWritable result = new IntWritable();

  public IntWritable evaluate(Text s) {
    if (s == null) {
      return null;
    }
    result.set(s.toString().length());
    return result;
  }
}
