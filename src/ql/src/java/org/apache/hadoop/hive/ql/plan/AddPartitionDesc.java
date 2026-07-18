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
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Contains the information needed to add a partition.
 */
public class AddPartitionDesc extends DDLDesc implements Serializable {

  private static final long serialVersionUID = 1L;
  
  String tableName;
  String dbName;
  String location;
  boolean ifNotExists;
  LinkedHashMap<String, String> partSpec;

  /**
   * For serialization only.
   */
  public AddPartitionDesc() {
  }

  /**
   * @param dbName
   *          database to add to.
   * @param tableName
   *          table to add to.
   * @param partSpec
   *          partition specification.
   * @param location
   *          partition location, relative to table location.
   * @param ifNotExists 
   *          if true, the partition is only added if it doesn't exist
   */
  public AddPartitionDesc(String dbName, String tableName,
      Map<String, String> partSpec, String location, boolean ifNotExists) {
    super();
    this.dbName = dbName;
    this.tableName = tableName;
    this.partSpec = new LinkedHashMap<String,String>(partSpec);
    this.location = location;
    this.ifNotExists = ifNotExists;
  }

  /**
   * @return database name
   */
  public String getDbName() {
    return dbName;
  }

  /**
   * @param dbName
   *          database name
   */
  public void setDbName(String dbName) {
    this.dbName = dbName;
  }

  /**
   * @return the table we're going to add the partitions to.
   */
  public String getTableName() {
    return tableName;
  }

  /**
   * @param tableName
   *          the table we're going to add the partitions to.
   */
  public void setTableName(String tableName) {
    this.tableName = tableName;
  }

  /**
   * @return location of partition in relation to table
   */
  public String getLocation() {
    return location;
  }

  /**
   * @param location
   *          location of partition in relation to table
   */
  public void setLocation(String location) {
    this.location = location;
  }

  /**
   * @return partition specification.
   */
  public LinkedHashMap<String, String> getPartSpec() {
    return partSpec;
  }

  /**
   * @param partSpec
   *          partition specification
   */
  public void setPartSpec(LinkedHashMap<String, String> partSpec) {
    this.partSpec = partSpec;
  }

  /**
   * @return if the partition should only be added if it doesn't exist already
   */
  public boolean getIfNotExists() {
    return this.ifNotExists;
  }

  /**
   * @param ifNotExists 
   *          if the part should be added only if it doesn't exist
   */
  public void setIfNotExists(boolean ifNotExists) {
    this.ifNotExists = ifNotExists;
  }
}
