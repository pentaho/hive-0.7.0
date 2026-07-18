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
 * UDFLpad.
 *
 */
@Description(name = "lpad",
    value = "_FUNC_(str, len, pad) - Returns str, left-padded with pad to a length of len",
    extended = "If str is longer than len, the return value is shortened to "
    + "len characters.\n"
    + "Example:\n"
    + "  > SELECT _FUNC_('hi', 5, '??') FROM src LIMIT 1;\n"
    + "  '???hi'"
    + "  > SELECT _FUNC_('hi', 1, '??') FROM src LIMIT 1;\n" + "  'h'")
public class UDFLpad extends UDF {

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

    // The length of the padding needed
    int pos = Math.max(len - s.getLength(), 0);

    // Copy the padding
    for (int i = 0; i < pos; i += pad.getLength()) {
      for (int j = 0; j < pad.getLength() && j < pos - i; j++) {
        data[i + j] = padTxt[j];
      }
    }

    // Copy the text
    for (int i = 0; pos + i < len && i < s.getLength(); i++) {
      data[pos + i] = txt[i];
    }

    result.set(data, 0, len);
    return result;
  }
}
