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

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.hadoop.hive.ql.exec.Description;
import org.apache.hadoop.hive.ql.exec.UDF;
import org.apache.hadoop.io.Text;

/**
 * UDFDate.
 *
 */
@Description(name = "to_date",
    value = "_FUNC_(expr) - Extracts the date part of the date or datetime expression expr",
    extended = "Example:\n "
    + "  > SELECT _FUNC_('2009-30-07 04:17:52') FROM src LIMIT 1;\n"
    + "  '2009-30-07'")
public class UDFDate extends UDF {
  private final SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

  private Text t = new Text();

  public UDFDate() {
  }

  /**
   * Get the date part of a date time string.
   * 
   * @param dateString
   *          the date string in the format of "yyyy-MM-dd HH:mm:ss" or
   *          "yyyy-MM-dd".
   * @return the date in the format of "yyyy-MM-dd".
   */
  public Text evaluate(Text dateString) {

    if (dateString == null) {
      return null;
    }

    try {
      Date date = formatter.parse(dateString.toString());
      t.set(formatter.format(date));
      return t;
    } catch (ParseException e) {
      return null;
    }
  }

}
