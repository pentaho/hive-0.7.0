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
import org.apache.hadoop.hive.ql.metadata.DummyPartition;
import org.apache.hadoop.hive.ql.metadata.Table;

public class HiveLockObject {
  String [] pathNames = null;

  public static class HiveLockObjectData {

    private String queryId;  // queryId of the command
    private String lockTime; // time at which lock was acquired
    // mode of the lock: EXPLICIT(lock command)/IMPLICIT(query)
    private String lockMode;

    public HiveLockObjectData(String queryId,
                              String lockTime,
                              String lockMode) {
      this.queryId  = queryId;
      this.lockTime = lockTime;
      this.lockMode = lockMode;
    }


    public HiveLockObjectData(String data) {
      if (data == null) {
        return;
      }

      String[] elem = data.split(":");
      queryId  = elem[0];
      lockTime = elem[1];
      lockMode = elem[2];
    }

    public String getQueryId() {
      return queryId;
    }

    public String getLockTime() {
      return lockTime;
    }

    public String getLockMode() {
      return lockMode;
    }

    public String toString() {
      return queryId + ":" + lockTime + ":" + lockMode;
    }
  }

  /* user supplied data for that object */
  private HiveLockObjectData data;

  public HiveLockObject() {
    this.data = null;
  }

  public HiveLockObject(String path, HiveLockObjectData lockData) {
    this.pathNames = new String[1];
    this.pathNames[0] = path;
    this.data = lockData;
  }

  public HiveLockObject(String[] paths, HiveLockObjectData lockData) {
    this.pathNames = paths;
    this.data = lockData;
  }

  public HiveLockObject(Table tbl, HiveLockObjectData lockData) {
    this(new String[] {tbl.getDbName(), tbl.getTableName()}, lockData);
  }

  public HiveLockObject(Partition par, HiveLockObjectData lockData) {
    this(new String[] { par.getTable().getDbName(),
        par.getTable().getTableName(), par.getName() }, lockData);
  }

  public HiveLockObject(DummyPartition par, HiveLockObjectData lockData) {
    this(new String[] { par.getName() }, lockData);
  }

  public String getName() {
    if (this.pathNames == null) {
      return null;
    }
    String ret = "";
    boolean first = true;
    for (int i = 0; i < pathNames.length; i++) {
      if (!first) {
        ret = ret + "/";
      } else {
        first = false;
      }
      ret = ret + pathNames[i];
    }
    return ret;
  }

  public String getDisplayName() {
    if (this.pathNames == null) {
      return null;
    }
    if (pathNames.length == 1) {
      return pathNames[0];
    }
    else if (pathNames.length == 2) {
      return pathNames[0] + "@" + pathNames[1];
    }

    String ret = pathNames[0] + "@" + pathNames[1] + "@";
    boolean first = true;
    for (int i = 2; i < pathNames.length; i++) {
      if (!first) {
        ret = ret + "/";
      } else {
        first = false;
      }
      ret = ret + pathNames[i];
    }
    return ret;
  }

  public HiveLockObjectData getData() {
    return data;
  }

  public void setData(HiveLockObjectData data) {
    this.data = data;
  }

}
