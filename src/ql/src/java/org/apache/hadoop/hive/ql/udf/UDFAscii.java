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
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;

/**
 * UDFAscii.
 *
 */
@Description(name = "ascii",
    value = "_FUNC_(str) - returns the numeric value of the first character"
    + " of str",
    extended = "Returns 0 if str is empty or NULL if str is NULL\n"
    + "Example:\n"
    + "  > SELECT _FUNC_('222') FROM src LIMIT 1;"
    + "  50\n"
    + "  > SELECT _FUNC_(2) FROM src LIMIT 1;\n" + "  50")
public class UDFAscii extends UDF {
  private final IntWritable result = new IntWritable();

  public IntWritable evaluate(Text s) {
    if (s == null) {
      return null;
    }

    if (s.getLength() > 0) {
      result.set(s.getBytes()[0]);
    } else {
      result.set(0);
    }

    return result;
  }
}
