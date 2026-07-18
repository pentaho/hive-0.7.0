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
 * JUnit test for UDFDateAdd.
 */
public class TestUDFDateAdd extends TestCase {

    /**
     * Verify if adding dates across a daylight savings time change
     * from daylight to standard time.  The timezone tested is west
     * coast US (PDT/PST) with a 1 hour shift back in time at 02:00 AM
     * on 2009-10-31.
     */
    public void testFallBack() throws Exception {
        // set the default time zone so that the dates cover 
        // the zone's daylight saving time adjustment (2009-10-31)
        // from daylight to standard time
        TimeZone.setDefault(TimeZone.getTimeZone("America/Los_Angeles"));
        final IntWritable nmDays = new IntWritable(7);
        final Text dtStr = new Text("2009-10-26");

        final Text result = new UDFDateAdd().evaluate(dtStr, nmDays);
        assertEquals("2009-11-02", result.toString());
    }

    /**
     * Verify if adding dates across a daylight savings time change
     * from standard to daylight time.  The timezone tested is west
     * coast US (PDT/PST) with a 1 hour shift forward in time at 02:00 AM
     * on 2010-03-14.
     */
    public void testSpringAhead() throws Exception {
        // set the default time zone so that the dates cover 
        // the zone's daylight saving time adjustment (2010-03-14)
        // from standard to daylight time
        TimeZone.setDefault(TimeZone.getTimeZone("America/Los_Angeles"));
        final IntWritable nmDays = new IntWritable(7);
        final Text dtStr = new Text("2010-03-08");

        final Text result = new UDFDateAdd().evaluate(dtStr, nmDays);
        assertEquals("2010-03-15", result.toString());
    }
}
