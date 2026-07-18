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

public class MRoleMap {
  
  private String principalName;
  
  private String principalType;
  
  private MRole role;
  
  private int addTime;
  
  private String grantor;
  
  private String grantorType;
  
  private boolean grantOption;

  public MRoleMap() {
  }
  
  public MRoleMap(String principalName, String principalType, MRole role,
      int addTime, String grantor, String grantorType, boolean grantOption) {
    super();
    this.principalName = principalName;
    this.principalType = principalType;
    this.role = role;
    this.addTime = addTime;
    this.grantor = grantor;
    this.grantorType = grantorType;
    this.grantOption = grantOption;
  }

  /**
   * @return principal name
   */
  public String getPrincipalName() {
    return principalName;
  }

  /**
   * @param userName principal name
   */
  public void setPrincipalName(String userName) {
    this.principalName = userName;
  }

  public String getPrincipalType() {
    return principalType;
  }

  public void setPrincipalType(String principalType) {
    this.principalType = principalType;
  }

  /**
   * @return add time
   */
  public int getAddTime() {
    return addTime;
  }

  /**
   * @param addTime
   */
  public void setAddTime(int addTime) {
    this.addTime = addTime;
  }

  public MRole getRole() {
    return role;
  }

  public void setRole(MRole role) {
    this.role = role;
  }
  
  public boolean getGrantOption() {
    return grantOption;
  }

  public void setGrantOption(boolean grantOption) {
    this.grantOption = grantOption;
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

}
