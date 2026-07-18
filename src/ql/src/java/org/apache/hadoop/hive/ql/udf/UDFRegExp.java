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

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.hadoop.hive.ql.exec.Description;
import org.apache.hadoop.hive.ql.exec.UDF;
import org.apache.hadoop.io.BooleanWritable;
import org.apache.hadoop.io.Text;

/**
 * UDFRegExp.
 *
 */
@Description(name = "rlike,regexp",
    value = "str _FUNC_ regexp - Returns true if str matches regexp and "
    + "false otherwise", extended = "Example:\n"
    + "  > SELECT 'fb' _FUNC_ '.*' FROM src LIMIT 1;\n" + "  true")
public class UDFRegExp extends UDF {
  static final Log LOG = LogFactory.getLog(UDFRegExp.class.getName());

  private final Text lastRegex = new Text();
  private Pattern p = null;
  private boolean warned = false;

  private BooleanWritable result = new BooleanWritable();

  public UDFRegExp() {
  }

  public BooleanWritable evaluate(Text s, Text regex) {
    if (s == null || regex == null) {
      return null;
    }
    if (regex.getLength() == 0) {
      if (!warned) {
        warned = true;
        LOG.warn(getClass().getSimpleName() + " regex is empty. Additional "
            + "warnings for an empty regex will be suppressed.");
      }
      result.set(false);
      return result;
    }
    if (!regex.equals(lastRegex) || p == null) {
      lastRegex.set(regex);
      p = Pattern.compile(regex.toString());
    }
    Matcher m = p.matcher(s.toString());
    result.set(m.find(0));
    return result;
  }

}
