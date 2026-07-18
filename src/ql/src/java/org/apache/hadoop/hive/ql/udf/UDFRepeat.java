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
 * UDFRepeat.
 *
 */
@Description(name = "repeat",
    value = "_FUNC_(str, n) - repeat str n times ",
    extended = "Example:\n"
    + "  > SELECT _FUNC_('123', 2) FROM src LIMIT 1;\n" + "  '123123'")
public class UDFRepeat extends UDF {
  private final Text result = new Text();

  public Text evaluate(Text s, IntWritable n) {
    if (n == null || s == null) {
      return null;
    }

    int len = n.get() * s.getLength();
    if (len < 0) {
      len = 0;
    }

    byte[] data = result.getBytes();

    if (data.length < len) {
      data = new byte[len];
    }

    for (int i = 0; i < len; i += s.getLength()) {
      for (int j = 0; j < s.getLength(); j++) {
        data[i + j] = s.getBytes()[j];
      }
    }

    result.set(data, 0, len);
    return result;
  }
}
