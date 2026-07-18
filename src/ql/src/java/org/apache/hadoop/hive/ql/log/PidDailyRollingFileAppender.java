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


package org.apache.hadoop.hive.ql.log;

import java.lang.management.ManagementFactory;
import java.lang.management.RuntimeMXBean;

import org.apache.log4j.DailyRollingFileAppender;

public class PidDailyRollingFileAppender extends DailyRollingFileAppender {

  @Override
  public void setFile(String file) {
    RuntimeMXBean rt = ManagementFactory.getRuntimeMXBean();
    super.setFile(file + '.' + rt.getName());
  }
}
