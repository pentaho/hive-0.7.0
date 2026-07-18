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
import java.util.Calendar;
import java.util.Date;

import org.apache.hadoop.hive.ql.exec.Description;
import org.apache.hadoop.hive.ql.exec.UDF;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;

/**
 * UDFHour.
 *
 */
@Description(name = "hour",
    value = "_FUNC_(date) - Returns the hour of date",
    extended = "date is a string in the format of 'yyyy-MM-dd HH:mm:ss' or "
    + "'HH:mm:ss'.\n"
    + "Example:\n "
    + "  > SELECT _FUNC_('2009-07-30 12:58:59') FROM src LIMIT 1;\n"
    + "  12\n"
    + "  > SELECT _FUNC_('12:58:59') FROM src LIMIT 1;\n" + "  12")
public class UDFHour extends UDF {
  private final SimpleDateFormat formatter1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
  private final SimpleDateFormat formatter2 = new SimpleDateFormat("HH:mm:ss");
  private final Calendar calendar = Calendar.getInstance();

  private IntWritable result = new IntWritable();

  public UDFHour() {
  }

  /**
   * Get the hour from a date string.
   * 
   * @param dateString
   *          the dateString in the format of "yyyy-MM-dd HH:mm:ss" or
   *          "yyyy-MM-dd".
   * @return an int from 0 to 23. null if the dateString is not a valid date
   *         string.
   */
  public IntWritable evaluate(Text dateString) {

    if (dateString == null) {
      return null;
    }

    try {
      Date date = null;
      try {
        date = formatter1.parse(dateString.toString());
      } catch (ParseException e) {
        date = formatter2.parse(dateString.toString());
      }
      calendar.setTime(date);
      result.set(calendar.get(Calendar.HOUR_OF_DAY));
      return result;
    } catch (ParseException e) {
      return null;
    }
  }

}
