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
 * UDFDayOfMonth.
 *
 */
@Description(name = "day,dayofmonth",
    value = "_FUNC_(date) - Returns the date of the month of date",
    extended = "date is a string in the format of 'yyyy-MM-dd HH:mm:ss' or "
    + "'yyyy-MM-dd'.\n"
    + "Example:\n "
    + "  > SELECT _FUNC_('2009-30-07', 1) FROM src LIMIT 1;\n" + "  30")
public class UDFDayOfMonth extends UDF {
  private final SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
  private final Calendar calendar = Calendar.getInstance();

  private IntWritable result = new IntWritable();

  public UDFDayOfMonth() {
  }

  /**
   * Get the day of month from a date string.
   * 
   * @param dateString
   *          the dateString in the format of "yyyy-MM-dd HH:mm:ss" or
   *          "yyyy-MM-dd".
   * @return an int from 1 to 31. null if the dateString is not a valid date
   *         string.
   */
  public IntWritable evaluate(Text dateString) {

    if (dateString == null) {
      return null;
    }

    try {
      Date date = formatter.parse(dateString.toString());
      calendar.setTime(date);
      result.set(calendar.get(Calendar.DAY_OF_MONTH));
      return result;
    } catch (ParseException e) {
      return null;
    }
  }

}
