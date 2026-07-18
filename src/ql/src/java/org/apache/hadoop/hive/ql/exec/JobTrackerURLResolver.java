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


package org.apache.hadoop.hive.ql.exec;

import java.io.IOException;
import java.net.InetSocketAddress;

import org.apache.hadoop.mapred.JobConf;
import org.apache.hadoop.mapred.JobTracker;
import org.apache.hadoop.net.NetUtils;

/**
 * JobTrackerURLResolver.
 *
 */
public final class JobTrackerURLResolver {
  public static String getURL(JobConf conf) throws IOException {
    String infoAddr = conf.get("mapred.job.tracker.http.address");
    if (infoAddr == null) {
      throw new IOException("Unable to find job tracker info port.");
    }
    InetSocketAddress infoSocAddr = NetUtils.createSocketAddr(infoAddr);
    int infoPort = infoSocAddr.getPort();

    String tracker = "http://" + JobTracker.getAddress(conf).getHostName()
        + ":" + infoPort;

    return tracker;
  }

  private JobTrackerURLResolver() {
    // prevent instantiation
  }
}
