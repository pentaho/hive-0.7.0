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
 * UDFWeekOfYear.
 *
 */
@Description(name = "yearweek", 
    value = "_FUNC_(date) - Returns the week of the year of the given date. A week "
    + "is considered to start on a Monday and week 1 is the first week with >3 days.", 
    extended = "Examples:\n"
    + "  > SELECT _FUNC_('2008-02-20') FROM src LIMIT 1;\n"
    + "  8\n"
    + "  > SELECT _FUNC_('1980-12-31 12:59:59') FROM src LIMIT 1;\n" + "  1")
public class UDFWeekOfYear extends UDF {
  private final SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
  private final Calendar calendar = Calendar.getInstance();

  private IntWritable result = new IntWritable();

  public UDFWeekOfYear() {
    calendar.setFirstDayOfWeek(Calendar.MONDAY);
    calendar.setMinimalDaysInFirstWeek(4);
  }

  /**
   * Get the week of the year from a date string.
   * 
   * @param dateString
   *          the dateString in the format of "yyyy-MM-dd HH:mm:ss" or
   *          "yyyy-MM-dd".
   * @return an int from 1 to 53. null if the dateString is not a valid date
   *         string.
   */
  public IntWritable evaluate(Text dateString) {
    if (dateString == null) {
      return null;
    }
    try {
      Date date = formatter.parse(dateString.toString());
      calendar.setTime(date);
      result.set(calendar.get(Calendar.WEEK_OF_YEAR));
      return result;
    } catch (ParseException e) {
      return null;
    }
  }

}
