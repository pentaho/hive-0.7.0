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


package org.apache.hadoop.hive.ql.plan;

import java.io.Serializable;
import java.util.Map;

import org.apache.hadoop.fs.Path;

/**
 * LockTableDesc.
 *
 */
@Explain(displayName = "Lock Table")
public class LockTableDesc extends DDLDesc implements Serializable {
  private static final long serialVersionUID = 1L;

  private String tableName;
  private String mode;
  private Map<String, String> partSpec;
  private String queryId;

  public LockTableDesc() {
  }

  public LockTableDesc(String tableName, String mode, Map<String, String> partSpec, String queryId) {
    this.tableName = tableName;
    this.mode      = mode;
    this.partSpec  = partSpec;
    this.queryId   = queryId;
  }

  public String getTableName() {
    return tableName;
  }

  public void setTableName(String tableName) {
    this.tableName = tableName;
  }

  public void setMode(String mode) {
    this.mode = mode;
  }

  public String getMode() {
    return mode;
  }

  public Map<String, String> getPartSpec() {
    return partSpec;
  }

  public void setPartSpec(Map<String, String> partSpec) {
    this.partSpec = partSpec;
  }

  public String getQueryId() {
    return queryId;
  }

  public void setQueryId(String queryId) {
    this.queryId = queryId;
  }
}
