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

public class MRole {

  private String roleName;

  private int createTime;

  private String ownerName;

  public MRole() {
  }

  public MRole(String roleName, int createTime, String ownerName) {
    super();
    this.roleName = roleName;
    this.createTime = createTime;
    this.ownerName = ownerName;
  }

  /**
   * @return role name
   */
  public String getRoleName() {
    return roleName;
  }

  /**
   * @param roleName
   */
  public void setRoleName(String roleName) {
    this.roleName = roleName;
  }

  /**
   * @return create time
   */
  public int getCreateTime() {
    return createTime;
  }

  /**
   * @param createTime
   *          role create time
   */
  public void setCreateTime(int createTime) {
    this.createTime = createTime;
  }

  /**
   * @return the principal name who created this role
   */
  public String getOwnerName() {
    return ownerName;
  }

  public void setOwnerName(String ownerName) {
    this.ownerName = ownerName;
  }

}

