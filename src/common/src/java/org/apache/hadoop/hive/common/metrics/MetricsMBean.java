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


package org.apache.hadoop.hive.common.metrics;

import java.io.IOException;

import javax.management.DynamicMBean;

/**
 * MBean definition for metrics tracking from jmx
 */

public interface MetricsMBean extends DynamicMBean {

    /**
     * Check if we're tracking a certain named key/metric
     */
    public abstract boolean hasKey(String name);

    /**
     * Add a key/metric and its value to track
     * @param name Name of the key/metric
     * @param value value associated with the key
     * @throws Exception
     */
    public abstract void put(String name, Object value) throws IOException;

    /**
     *
     * @param name
     * @return
     * @throws Exception
     */
    public abstract Object get(String name) throws IOException;

}
