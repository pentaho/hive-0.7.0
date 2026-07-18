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

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;

import junit.framework.TestCase;

import java.util.TimeZone;

/**
 * JUnit test for UDFDateDiff.
 */
public class TestUDFDateDiff extends TestCase {

    /**
     * Verify differences of dates crossing a daylight savings time change
     * are correct.  The timezone tested is west coast US (PDT/PST) with a 
     * 1 hour shift back in time at 02:00 AM on 2009-10-31 and a
     * 1 hour shift forward in time at 02:00 AM on 2010-03-14.
     */
    public void testDaylightChange() throws Exception {
        TimeZone.setDefault(TimeZone.getTimeZone("America/Los_Angeles"));

        // time moves ahead an hour at 02:00 on 2009-10-31
        // which results in a 23 hour long day
        Text date1 = new Text("2009-11-01");
        Text date2 = new Text("2009-10-25");

        IntWritable result = new UDFDateDiff().evaluate(date1, date2);
        assertEquals(7, result.get());

        result = new UDFDateDiff().evaluate(date2, date1);
        assertEquals(-7, result.get());


        // time moves back an hour at 02:00 on 2010-03-14
        // which results in a 25 hour long day
        date1 = new Text("2010-03-15");
        date2 = new Text("2010-03-08");

        result = new UDFDateDiff().evaluate(date1, date2);
        assertEquals(7, result.get());

        result = new UDFDateDiff().evaluate(date2, date1);
        assertEquals(-7, result.get());

    }
}
