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

public class MPartitionPrivilege {
  
  private String principalName;
  
  private String principalType;

  private MPartition partition;
  
  private String privilege;
  
  private int createTime;
  
  private String grantor;
  
  private String grantorType;

  private boolean grantOption;
  
  public MPartitionPrivilege() {
  }

  public MPartitionPrivilege(String principalName, String principalType,
      MPartition partition, String privilege, int createTime,
      String grantor, String grantorType, boolean grantOption) {
    super();
    this.principalName = principalName;
    this.principalType = principalType;
    this.partition = partition;
    this.privilege = privilege;
    this.createTime = createTime;
    this.grantor = grantor;
    this.grantorType = grantorType;
    this.grantOption = grantOption;
  }

  public String getPrincipalName() {
    return principalName;
  }

  public void setPrincipalName(String principalName) {
    this.principalName = principalName;
  }


  /**
   * @return a set of privileges this user/role/group has
   */
  public String getPrivilege() {
    return privilege;
  }

  /**
   * @param dbPrivilege a set of privileges this user/role/group has
   */
  public void setPrivilege(String dbPrivilege) {
    this.privilege = dbPrivilege;
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

  /**
   * @return
   */
  public String getGrantor() {
    return grantor;
  }

  /**
   * @param grantor
   */
  public void setGrantor(String grantor) {
    this.grantor = grantor;
  }

  public String getPrincipalType() {
    return principalType;
  }

  public void setPrincipalType(String principalType) {
    this.principalType = principalType;
  }

  public MPartition getPartition() {
    return partition;
  }

  public void setPartition(MPartition partition) {
    this.partition = partition;
  }
  
  public boolean getGrantOption() {
    return grantOption;
  }

  public void setGrantOption(boolean grantOption) {
    this.grantOption = grantOption;
  }
  
  public String getGrantorType() {
    return grantorType;
  }

  public void setGrantorType(String grantorType) {
    this.grantorType = grantorType;
  }

}
