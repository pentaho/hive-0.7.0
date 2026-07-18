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

import org.apache.commons.lang.StringUtils;
import org.apache.hadoop.hive.ql.exec.Description;
import org.apache.hadoop.hive.ql.exec.UDF;
import org.apache.hadoop.io.Text;

/**
 * UDFTrim.
 *
 */
@Description(name = "trim", 
    value = "_FUNC_(str) - Removes the leading and trailing space characters from str ",
    extended = "Example:\n"
    + "  > SELECT _FUNC_('   facebook  ') FROM src LIMIT 1;\n" + "  'facebook'")
public class UDFTrim extends UDF {

  Text result = new Text();

  public UDFTrim() {
  }

  public Text evaluate(Text s) {
    if (s == null) {
      return null;
    }
    result.set(StringUtils.strip(s.toString(), " "));
    return result;
  }

}
