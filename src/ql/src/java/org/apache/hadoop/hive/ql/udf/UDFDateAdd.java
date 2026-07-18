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
 * UDFDateAdd.
 *
 */
@Description(name = "date_add",
    value = "_FUNC_(start_date, num_days) - Returns the date that is num_days after start_date.",
    extended = "start_date is a string in the format 'yyyy-MM-dd HH:mm:ss' or"
    + " 'yyyy-MM-dd'. num_days is a number. The time part of start_date is "
    + "ignored.\n"
    + "Example:\n "
    + "  > SELECT _FUNC_('2009-30-07', 1) FROM src LIMIT 1;\n"
    + "  '2009-31-07'")
public class UDFDateAdd extends UDF {
  private final SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
  private final Calendar calendar = Calendar.getInstance();

  private Text result = new Text();

  public UDFDateAdd() {
  }

  /**
   * Add a number of days to the date. The time part of the string will be
   * ignored.
   * 
   * NOTE: This is a subset of what MySQL offers as:
   * http://dev.mysql.com/doc/refman
   * /5.1/en/date-and-time-functions.html#function_date-add
   * 
   * @param dateString1
   *          the date string in the format of "yyyy-MM-dd HH:mm:ss" or
   *          "yyyy-MM-dd".
   * @param days
   *          The number of days to add.
   * @return the date in the format of "yyyy-MM-dd".
   */
  public Text evaluate(Text dateString1, IntWritable days) {

    if (dateString1 == null || days == null) {
      return null;
    }

    try {
      calendar.setTime(formatter.parse(dateString1.toString()));
      calendar.add(Calendar.DAY_OF_MONTH, days.get());
      Date newDate = calendar.getTime();
      result.set(formatter.format(newDate));
      return result;
    } catch (ParseException e) {
      return null;
    }
  }

}
