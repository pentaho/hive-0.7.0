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


package org.apache.hadoop.hive.metastore.hooks;

import org.apache.hadoop.conf.Configuration;

/**
 * JDOConnectURLHook is used to get the URL that JDO uses to connect to the
 * database that stores the metastore data. Classes implementing this must be
 * thread-safe (for Thrift server).
 */
public interface JDOConnectionURLHook {

  /**
   * Gets the connection URL to supply to JDO. In addition to initialization,
   * this method will be called after a connection failure for each reconnect
   * attempt.
   *
   * @param conf The configuration used to initialize this instance of the HMS
   * @return
   * @throws Exception
   */
  public String getJdoConnectionUrl(Configuration conf)
  throws Exception;

  /**
   * Alerts this that the connection URL was bad. Can be used to collect stats,
   * etc.
   *
   * @param url
   */
  public void notifyBadConnectionUrl(String url);
}
