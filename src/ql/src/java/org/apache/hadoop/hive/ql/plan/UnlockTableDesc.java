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
 * UnlockTableDesc.
 *
 */
@Explain(displayName = "Unlock Table")
public class UnlockTableDesc extends DDLDesc implements Serializable {
  private static final long serialVersionUID = 1L;

  private String tableName;
  private Map<String, String> partSpec;

  public UnlockTableDesc() {
  }

  public UnlockTableDesc(String tableName, Map<String, String> partSpec) {
    this.tableName = tableName;
    this.partSpec  = partSpec;
  }

  public String getTableName() {
    return tableName;
  }

  public void setTableName(String tableName) {
    this.tableName = tableName;
  }

  public Map<String, String> getPartSpec() {
    return partSpec;
  }

  public void setPartSpec(Map<String, String> partSpec) {
    this.partSpec = partSpec;
  }
}
