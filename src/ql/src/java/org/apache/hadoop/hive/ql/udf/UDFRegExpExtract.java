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

import java.util.regex.MatchResult;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.hadoop.hive.ql.exec.Description;
import org.apache.hadoop.hive.ql.exec.UDF;

/**
 * UDF to extract a specific group identified by a java regex. Note that if a
 * regexp has a backslash ('\'), then need to specify '\\' For example,
 * regexp_extract('100-200', '(\\d+)-(\\d+)', 1) will return '100'
 */
@Description(name = "regexp_extract",
    value = "_FUNC_(str, regexp[, idx]) - extracts a group that matches regexp",
    extended = "Example:\n"
    + "  > SELECT _FUNC_('100-200', '(\\d+)-(\\d+)', 1) FROM src LIMIT 1;\n"
    + "  '100'")
public class UDFRegExpExtract extends UDF {
  private String lastRegex = null;
  private Pattern p = null;

  public UDFRegExpExtract() {
  }

  public String evaluate(String s, String regex, Integer extractIndex) {
    if (s == null || regex == null) {
      return null;
    }
    if (!regex.equals(lastRegex) || p == null) {
      lastRegex = regex;
      p = Pattern.compile(regex);
    }
    Matcher m = p.matcher(s);
    if (m.find()) {
      MatchResult mr = m.toMatchResult();
      return mr.group(extractIndex);
    }
    return "";
  }

  public String evaluate(String s, String regex) {
    return this.evaluate(s, regex, 1);
  }

}
