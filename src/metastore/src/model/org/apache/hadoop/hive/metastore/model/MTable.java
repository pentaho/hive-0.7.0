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


package org.apache.hadoop.hive.metastore.model;

import java.util.List;
import java.util.Map;

public class MTable {
  
  private String tableName;
  private MDatabase database;
  private MStorageDescriptor sd;
  private String owner;
  private int createTime;
  private int lastAccessTime;
  private int retention;
  private List<MFieldSchema> partitionKeys;
  private Map<String, String> parameters;
  private String viewOriginalText;
  private String viewExpandedText;
  private String tableType;

  public MTable() {}

  /**
   * @param tableName
   * @param database
   * @param sd
   * @param owner
   * @param createTime
   * @param lastAccessTime
   * @param retention
   * @param partitionKeys
   * @param parameters
   * @param viewOriginalText
   * @param viewExpandedText
   * @param tableType
   */
  public MTable(String tableName, MDatabase database, MStorageDescriptor sd, String owner,
      int createTime, int lastAccessTime, int retention, List<MFieldSchema> partitionKeys,
      Map<String, String> parameters,
      String viewOriginalText, String viewExpandedText, String tableType) {
    this.tableName = tableName;
    this.database = database;
    this.sd = sd;
    this.owner = owner;
    this.createTime = createTime;
    this.setLastAccessTime(lastAccessTime);
    this.retention = retention;
    this.partitionKeys = partitionKeys;
    this.parameters = parameters;
    this.viewOriginalText = viewOriginalText;
    this.viewExpandedText = viewExpandedText;
    this.tableType = tableType;
  }

  /**
   * @return the tableName
   */
  public String getTableName() {
    return tableName;
  }

  /**
   * @param tableName the tableName to set
   */
  public void setTableName(String tableName) {
    this.tableName = tableName;
  }

  /**
   * @return the sd
   */
  public MStorageDescriptor getSd() {
    return sd;
  }

  /**
   * @param sd the sd to set
   */
  public void setSd(MStorageDescriptor sd) {
    this.sd = sd;
  }

  /**
   * @return the partKeys
   */
  public List<MFieldSchema> getPartitionKeys() {
    return partitionKeys;
  }

  /**
   * @param partKeys the partKeys to set
   */
  public void setPartitionKeys(List<MFieldSchema> partKeys) {
    this.partitionKeys = partKeys;
  }

  /**
   * @return the parameters
   */
  public Map<String, String> getParameters() {
    return parameters;
  }

  /**
   * @param parameters the parameters to set
   */
  public void setParameters(Map<String, String> parameters) {
    this.parameters = parameters;
  }

  /**
   * @return the original view text, or null if this table is not a view
   */
  public String getViewOriginalText() {
    return viewOriginalText;
  }

  /**
   * @param viewOriginalText the original view text to set
   */
  public void setViewOriginalText(String viewOriginalText) {
    this.viewOriginalText = viewOriginalText;
  }

  /**
   * @return the expanded view text, or null if this table is not a view
   */
  public String getViewExpandedText() {
    return viewExpandedText;
  }

  /**
   * @param viewExpandedText the expanded view text to set
   */
  public void setViewExpandedText(String viewExpandedText) {
    this.viewExpandedText = viewExpandedText;
  }

  /**
   * @return the owner
   */
  public String getOwner() {
    return owner;
  }

  /**
   * @param owner the owner to set
   */
  public void setOwner(String owner) {
    this.owner = owner;
  }

  /**
   * @return the createTime
   */
  public int getCreateTime() {
    return createTime;
  }

  /**
   * @param createTime the createTime to set
   */
  public void setCreateTime(int createTime) {
    this.createTime = createTime;
  }

  /**
   * @return the database
   */
  public MDatabase getDatabase() {
    return database;
  }

  /**
   * @param database the database to set
   */
  public void setDatabase(MDatabase database) {
    this.database = database;
  }

  /**
   * @return the retention
   */
  public int getRetention() {
    return retention;
  }

  /**
   * @param retention the retention to set
   */
  public void setRetention(int retention) {
    this.retention = retention;
  }

  /**
   * @param lastAccessTime the lastAccessTime to set
   */
  public void setLastAccessTime(int lastAccessTime) {
    this.lastAccessTime = lastAccessTime;
  }

  /**
   * @return the lastAccessTime
   */
  public int getLastAccessTime() {
    return lastAccessTime;
  }

  /**
   * @param tableType the tableType to set
   */
  public void setTableType(String tableType) {
    this.tableType = tableType;
  }

  /**
   * @return the tableType
   */
  public String getTableType() {
    return tableType;
  }
}
