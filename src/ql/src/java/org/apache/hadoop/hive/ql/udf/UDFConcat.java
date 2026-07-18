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
import org.apache.hadoop.io.Text;

/**
 * UDFConcat.
 *
 */
@Description(name = "concat",
    value = "_FUNC_(str1, str2, ... strN) - returns the concatenation of str1, str2, ... strN",
    extended = "Returns NULL if any argument is NULL.\n"
    + "Example:\n"
    + "  > SELECT _FUNC_('abc', 'def') FROM src LIMIT 1;\n"
    + "  'abcdef'")
public class UDFConcat extends UDF {

  public UDFConcat() {
  }

  private Text text = new Text();

  public Text evaluate(Text... args) {
    text.clear();
    for (Text arg : args) {
      if (arg == null) {
        return null;
      }
      text.append(arg.getBytes(), 0, arg.getLength());
    }
    return text;
  }

}
