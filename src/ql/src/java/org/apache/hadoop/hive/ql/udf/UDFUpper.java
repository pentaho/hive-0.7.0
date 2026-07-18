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
 * UDFUpper.
 *
 */
@Description(name = "upper,ucase",
    value = "_FUNC_(str) - Returns str with all characters changed to uppercase",
    extended = "Example:\n"
    + "  > SELECT _FUNC_('Facebook') FROM src LIMIT 1;\n" + "  'FACEBOOK'")
public class UDFUpper extends UDF {

  Text t = new Text();

  public UDFUpper() {
  }

  public Text evaluate(Text s) {
    if (s == null) {
      return null;
    }
    t.set(s.toString().toUpperCase());
    return t;
  }

}
