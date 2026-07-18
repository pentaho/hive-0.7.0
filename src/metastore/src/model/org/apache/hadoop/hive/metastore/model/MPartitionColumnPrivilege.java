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


public class MPartitionColumnPrivilege {

  private String principalName; 

  private String principalType;

  private MPartition partition;

  private String columnName;

  private String privilege;

  private int createTime;
  
  private String grantor;
  
  private String grantorType;
  
  private boolean grantOption;

  public MPartitionColumnPrivilege() {
  }
  
  /**
   * @param principalName
   * @param isRole
   * @param isGroup
   * @param partition
   * @param columnName
   * @param privileges
   * @param createTime
   * @param grantor
   */
  public MPartitionColumnPrivilege(String principalName, String principalType,
      MPartition partition, String columnName, String privileges, int createTime,
      String grantor, String grantorType, boolean grantOption) {
    super();
    this.principalName = principalName;
    this.principalType = principalType;
    this.partition = partition;
    this.columnName = columnName;
    this.privilege = privileges;
    this.createTime = createTime;
    this.grantor = grantor;
    this.grantorType = grantorType;
    this.grantOption = grantOption;
  }
  
  /**
   * @return column name
   */
  public String getColumnName() {
    return columnName;
  }

  /**
   * @param columnName column name
   */
  public void setColumnName(String columnName) {
    this.columnName = columnName;
  }

  /**
   * @return a set of privileges this user/role/group has
   */
  public String getPrivilege() {
    return privilege;
  }

  /**
   * @param dbPrivileges a set of privileges this user/role/group has
   */
  public void setPrivilege(String dbPrivileges) {
    this.privilege = dbPrivileges;
  }

  /**
   * @return create time
   */
  public int getCreateTime() {
    return createTime;
  }

  /**
   * @param createTime create time
   */
  public void setCreateTime(int createTime) {
    this.createTime = createTime;
  }

  public String getPrincipalName() {
    return principalName;
  }

  public void setPrincipalName(String principalName) {
    this.principalName = principalName;
  }

  public MPartition getPartition() {
    return partition;
  }

  public void setPartition(MPartition partition) {
    this.partition = partition;
  }

  public String getGrantor() {
    return grantor;
  }

  public void setGrantor(String grantor) {
    this.grantor = grantor;
  }
  
  public String getGrantorType() {
    return grantorType;
  }

  public void setGrantorType(String grantorType) {
    this.grantorType = grantorType;
  }
  
  public boolean getGrantOption() {
    return grantOption;
  }

  public void setGrantOption(boolean grantOption) {
    this.grantOption = grantOption;
  }
  
  public String getPrincipalType() {
    return principalType;
  }

  public void setPrincipalType(String principalType) {
    this.principalType = principalType;
  }

}
