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


package org.apache.hadoop.hive.ql.lockmgr;

import org.apache.hadoop.hive.conf.HiveConf;

public class HiveLockManagerCtx {
  HiveConf conf;

  public HiveLockManagerCtx() {
  }

  public HiveLockManagerCtx(HiveConf conf) {
    this.conf = conf;
  }

  public HiveConf getConf() {
    return conf;
  }

  public void setConf(HiveConf conf) {
    this.conf = conf;
  }
}
