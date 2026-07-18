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


package org.apache.hadoop.hive.ql.lockmgr.zookeeper;

import org.apache.hadoop.hive.ql.lockmgr.HiveLock;
import org.apache.hadoop.hive.ql.lockmgr.HiveLockMode;
import org.apache.hadoop.hive.ql.lockmgr.HiveLockObject;

public class ZooKeeperHiveLock extends HiveLock {
  private String         path;
  private HiveLockObject obj;
  private HiveLockMode   mode;

  public ZooKeeperHiveLock(String path, HiveLockObject obj, HiveLockMode mode) {
    this.path = path;
    this.obj  = obj;
    this.mode = mode;
  }

  public String getPath() {
    return path;
  }

  public void setPath(String path) {
    this.path = path;
  }

  public HiveLockObject getHiveLockObject() {
    return obj;
  }

  public void setHiveLockObject(HiveLockObject obj) {
    this.obj = obj;
  }

  public HiveLockMode getHiveLockMode() {
    return mode;
  }

  public void setHiveLockMode(HiveLockMode mode) {
    this.mode = mode;
  }
}
