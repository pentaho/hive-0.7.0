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
import org.apache.hadoop.hive.ql.udf.generic.GenericUDFUtils;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;

/**
 * UDFLength.
 *
 */
@Description(name = "length",
    value = "_FUNC_(str) - Returns the length of str ",
    extended = "Example:\n"
    + "  > SELECT _FUNC_('Facebook') FROM src LIMIT 1;\n" + "  8")
public class UDFLength extends UDF {
  private final IntWritable result = new IntWritable();

  public IntWritable evaluate(Text s) {
    if (s == null) {
      return null;
    }

    byte[] data = s.getBytes();
    int len = 0;
    for (int i = 0; i < s.getLength(); i++) {
      if (GenericUDFUtils.isUtfStartByte(data[i])) {
        len++;
      }
    }

    result.set(len);
    return result;
  }
}
