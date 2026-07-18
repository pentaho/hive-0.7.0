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

import org.apache.hadoop.hive.ql.metadata.Partition;
import org.apache.hadoop.hive.ql.metadata.Table;

public class HiveLockObj {
  HiveLockObject obj;
  HiveLockMode   mode;

  public HiveLockObj(HiveLockObject obj, HiveLockMode mode) {
    this.obj  = obj;
    this.mode = mode;
  }

  public HiveLockObject getObj() {
    return obj;
  }

  public void setObj(HiveLockObject obj) {
    this.obj = obj;
  }

  public HiveLockMode getMode() {
    return mode;
  }

  public void setMode(HiveLockMode mode) {
    this.mode = mode;
  }

  public String getName() {
    return obj.getName();
  }
}
