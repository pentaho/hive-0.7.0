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

public class MDBPrivilege {
  
  private String principalName;
  
  private String principalType;
  
  private MDatabase database;
  
  private int createTime;
  
  private String privilege;
  
  private String grantor;
  
  private String grantorType;
  
  private boolean grantOption;

  public MDBPrivilege() {
  }

  public MDBPrivilege(String principalName, String principalType,
      MDatabase database, String dbPrivileges, int createTime, String grantor,
      String grantorType, boolean grantOption) {
    super();
    this.principalName = principalName;
    this.principalType = principalType;
    this.database = database;
    this.privilege = dbPrivileges;
    this.createTime = createTime;
    this.grantorType = grantorType;
    this.grantOption = grantOption;
    this.grantor = grantor;
  }

  /**
   * @return user name, role name, or group name
   */
  public String getPrincipalName() {
    return principalName;
  }

  /**
   * @param userName user/role/group name
   */
  public void setPrincipalName(String userName) {
    this.principalName = userName;
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
  public void setPrivilege(String dbPrivilege) {
    this.privilege = dbPrivilege;
  }
  
  public MDatabase getDatabase() {
    return database;
  }

  public void setDatabase(MDatabase database) {
    this.database = database;
  }

  public int getCreateTime() {
    return createTime;
  }

  public void setCreateTime(int createTime) {
    this.createTime = createTime;
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
