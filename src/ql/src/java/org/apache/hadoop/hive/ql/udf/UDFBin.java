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
import org.apache.hadoop.io.Text;

/**
 * UDFBin.
 *
 */
@Description(name = "bin",
    value = "_FUNC_(n) - returns n in binary",
    extended = "n is a BIGINT. Returns NULL if n is NULL.\n"
    + "Example:\n" + "  > SELECT _FUNC_(13) FROM src LIMIT 1\n" + "  '1101'")
public class UDFBin extends UDF {
  private final Text result = new Text();
  private byte[] value = new byte[64];

  public Text evaluate(LongWritable n) {
    if (n == null) {
      return null;
    }

    long num = n.get();
    // Extract the bits of num into value[] from right to left
    int len = 0;
    do {
      len++;
      value[value.length - len] = (byte) ('0' + (num & 1));
      num >>>= 1;
    } while (num != 0);

    result.set(value, value.length - len, len);
    return result;
  }
}
