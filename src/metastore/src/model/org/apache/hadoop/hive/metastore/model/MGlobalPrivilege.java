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

/**
 * User global level privileges  
 */
public class MGlobalPrivilege {

  //principal name, can be a user, group, or role
  private String principalName;
  
  private String principalType;
  
  private String privilege;
  
  private int createTime;
  
  private String grantor;
  
  private String grantorType;
  
  private boolean grantOption;
  
  public MGlobalPrivilege() {
    super();
  }

  public MGlobalPrivilege(String userName, String principalType,
      String dbPrivilege, int createTime, String grantor, String grantorType,
      boolean grantOption) {
    super();
    this.principalName = userName;
    this.principalType = principalType;
    this.privilege = dbPrivilege;
    this.createTime = createTime;
    this.grantor = grantor;
    this.grantorType = grantorType;
    this.grantOption = grantOption;
  }

  /**
   * @return a set of global privileges granted to this user
   */
  public String getPrivilege() {
    return privilege;
  }

  /**
   * @param dbPrivileges set of global privileges to user
   */
  public void setPrivilege(String dbPrivilege) {
    this.privilege = dbPrivilege;
  }
  
  public String getPrincipalName() {
    return principalName;
  }

  public void setPrincipalName(String principalName) {
    this.principalName = principalName;
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

  public String getGrantorType() {
    return grantorType;
  }

  public void setGrantorType(String grantorType) {
    this.grantorType = grantorType;
  }

}
