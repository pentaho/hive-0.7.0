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

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.hadoop.hive.ql.exec.Description;
import org.apache.hadoop.hive.ql.exec.UDF;
import org.apache.hadoop.io.Text;

/**
 * UDFRegExpReplace.
 *
 */
@Description(name = "regexp_replace",
    value = "_FUNC_(str, regexp, rep) - replace all substrings of str that "
    + "match regexp with rep", extended = "Example:\n"
    + "  > SELECT _FUNC_('100-200', '(\\d+)', 'num') FROM src LIMIT 1;\n"
    + "  'num-num'")
public class UDFRegExpReplace extends UDF {

  private final Text lastRegex = new Text();
  private Pattern p = null;

  private final Text lastReplacement = new Text();
  private String replacementString = "";

  private Text result = new Text();

  public UDFRegExpReplace() {
  }

  public Text evaluate(Text s, Text regex, Text replacement) {
    if (s == null || regex == null || replacement == null) {
      return null;
    }
    // If the regex is changed, make sure we compile the regex again.
    if (!regex.equals(lastRegex) || p == null) {
      lastRegex.set(regex);
      p = Pattern.compile(regex.toString());
    }
    Matcher m = p.matcher(s.toString());
    // If the replacement is changed, make sure we redo toString again.
    if (!replacement.equals(lastReplacement)) {
      lastReplacement.set(replacement);
      replacementString = replacement.toString();
    }

    StringBuffer sb = new StringBuffer();
    while (m.find()) {
      m.appendReplacement(sb, replacementString);
    }
    m.appendTail(sb);

    result.set(sb.toString());
    return result;
  }

}
