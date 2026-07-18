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

import java.util.LinkedHashMap;
import java.util.Map;

import org.apache.hadoop.hive.ql.plan.AlterTableDesc.AlterTableTypes;

/**
 * Contains information needed to modify a partition or a table
 */
public class AlterTableSimpleDesc extends DDLDesc {
  private String tableName;
  private String dbName;
  private LinkedHashMap<String, String> partSpec;

  AlterTableTypes type;

  public AlterTableSimpleDesc() {
  }

  /**
   * @param dbName
   *          database that contains the table / partition
   * @param tableName
   *          table containing the partition
   * @param partSpec
   *          partition specification. Null if touching a table.
   */
  public AlterTableSimpleDesc(String dbName, String tableName,
      Map<String, String> partSpec, AlterTableDesc.AlterTableTypes type) {
    super();
    this.dbName = dbName;
    this.tableName = tableName;
    if(partSpec == null) {
      this.partSpec = null;
    } else {
      this.partSpec = new LinkedHashMap<String,String>(partSpec);
    }
    this.type = type;
  }

  public String getTableName() {
    return tableName;
  }

  public void setTableName(String tableName) {
    this.tableName = tableName;
  }

  public String getDbName() {
    return dbName;
  }

  public void setDbName(String dbName) {
    this.dbName = dbName;
  }

  public AlterTableDesc.AlterTableTypes getType() {
    return type;
  }

  public void setType(AlterTableDesc.AlterTableTypes type) {
    this.type = type;
  }

  public LinkedHashMap<String, String> getPartSpec() {
    return partSpec;
  }

  public void setPartSpec(LinkedHashMap<String, String> partSpec) {
    this.partSpec = partSpec;
  }

}
