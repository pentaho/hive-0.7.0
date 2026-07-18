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

import java.util.Arrays;

import org.apache.hadoop.hive.ql.exec.Description;
import org.apache.hadoop.hive.ql.exec.UDF;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;

/**
 * UDFSpace.
 *
 */
@Description(name = "space", value = "_FUNC_(n) - returns n spaces", extended = "Example:\n "
    + "  > SELECT _FUNC_(2) FROM src LIMIT 1;\n" + "  '  '")
public class UDFSpace extends UDF {
  private final Text result = new Text();

  public Text evaluate(IntWritable n) {
    if (n == null) {
      return null;
    }

    int len = n.get();
    if (len < 0) {
      len = 0;
    }

    if (result.getBytes().length >= len) {
      result.set(result.getBytes(), 0, len);
    } else {
      byte[] spaces = new byte[len];
      Arrays.fill(spaces, (byte) ' ');
      result.set(spaces);
    }

    return result;
  }
}
