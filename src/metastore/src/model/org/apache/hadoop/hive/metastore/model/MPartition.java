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

public class MPartition {

  private String partitionName; // partitionname ==>  (key=value/)*(key=value)
  private MTable table; 
  private List<String> values;
  private int createTime;
  private int lastAccessTime;
  private MStorageDescriptor sd;
  private Map<String, String> parameters;
  
  
  public MPartition() {}
  
  /**
   * @param partitionName
   * @param table
   * @param values
   * @param createTime
   * @param lastAccessTime
   * @param sd
   * @param parameters
   */
  public MPartition(String partitionName, MTable table, List<String> values, int createTime,
      int lastAccessTime, MStorageDescriptor sd, Map<String, String> parameters) {
    this.partitionName = partitionName;
    this.table = table;
    this.values = values;
    this.createTime = createTime;
    this.lastAccessTime = lastAccessTime;
    this.sd = sd;
    this.parameters = parameters;
  }

  /**
   * @return the lastAccessTime
   */
  public int getLastAccessTime() {
    return lastAccessTime;
  }

  /**
   * @param lastAccessTime the lastAccessTime to set
   */
  public void setLastAccessTime(int lastAccessTime) {
    this.lastAccessTime = lastAccessTime;
  }

  /**
   * @return the values
   */
  public List<String> getValues() {
    return values;
  }

  /**
   * @param values the values to set
   */
  public void setValues(List<String> values) {
    this.values = values;
  }

  /**
   * @return the table
   */
  public MTable getTable() {
    return table;
  }

  /**
   * @param table the table to set
   */
  public void setTable(MTable table) {
    this.table = table;
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
   * @return the partitionName
   */
  public String getPartitionName() {
    return partitionName;
  }

  /**
   * @param partitionName the partitionName to set
   */
  public void setPartitionName(String partitionName) {
    this.partitionName = partitionName;
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

}
