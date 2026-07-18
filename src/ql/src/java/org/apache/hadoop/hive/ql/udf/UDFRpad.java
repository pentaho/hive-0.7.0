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
 * UDFRpad.
 *
 */
@Description(name = "rpad",
    value = "_FUNC_(str, len, pad) - Returns str, right-padded with pad to a length of len",
    extended = "If str is longer than len, the return value is shortened to "
    + "len characters.\n"
    + "Example:\n"
    + "  > SELECT _FUNC_('hi', 5, '??') FROM src LIMIT 1;\n"
    + "  'hi???'"
    + "  > SELECT _FUNC_('hi', 1, '??') FROM src LIMIT 1;\n" + "  'h'")
public class UDFRpad extends UDF {
  private final Text result = new Text();

  public Text evaluate(Text s, IntWritable n, Text pad) {
    if (s == null || n == null || pad == null) {
      return null;
    }

    int len = n.get();

    byte[] data = result.getBytes();
    if (data.length < len) {
      data = new byte[len];
    }

    byte[] txt = s.getBytes();
    byte[] padTxt = pad.getBytes();

    int pos;
    // Copy the text
    for (pos = 0; pos < s.getLength() && pos < len; pos++) {
      data[pos] = txt[pos];
    }

    // Copy the padding
    while (pos < len) {
      for (int i = 0; i < pad.getLength() && i < len - pos; i++) {
        data[pos + i] = padTxt[i];
      }
      pos += pad.getLength();
    }

    result.set(data, 0, len);
    return result;
  }
}
