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
 * UDFLower.
 *
 */
@Description(name = "lower,lcase",
    value = "_FUNC_(str) - Returns str with all characters changed to lowercase",
    extended = "Example:\n"
    + "  > SELECT _FUNC_('Facebook') FROM src LIMIT 1;\n" + "  'facebook'")
public class UDFLower extends UDF {
  private Text t = new Text();

  public UDFLower() {
  }

  public Text evaluate(Text s) {
    if (s == null) {
      return null;
    }
    t.set(s.toString().toLowerCase());
    return t;
  }

}
