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

/**
 * CreateTableLikeDesc.
 *
 */
@Explain(displayName = "Create Table")
public class CreateTableLikeDesc extends DDLDesc implements Serializable {
  private static final long serialVersionUID = 1L;
  String tableName;
  boolean isExternal;
  String location;
  boolean ifNotExists;
  String likeTableName;

  public CreateTableLikeDesc() {
  }

  public CreateTableLikeDesc(String tableName, boolean isExternal,
      String location, boolean ifNotExists, String likeTableName) {
    this.tableName = tableName;
    this.isExternal = isExternal;
    this.location = location;
    this.ifNotExists = ifNotExists;
    this.likeTableName = likeTableName;
  }

  @Explain(displayName = "if not exists")
  public boolean getIfNotExists() {
    return ifNotExists;
  }

  public void setIfNotExists(boolean ifNotExists) {
    this.ifNotExists = ifNotExists;
  }

  @Explain(displayName = "name")
  public String getTableName() {
    return tableName;
  }

  public void setTableName(String tableName) {
    this.tableName = tableName;
  }

  @Explain(displayName = "location")
  public String getLocation() {
    return location;
  }

  public void setLocation(String location) {
    this.location = location;
  }

  @Explain(displayName = "isExternal")
  public boolean isExternal() {
    return isExternal;
  }

  public void setExternal(boolean isExternal) {
    this.isExternal = isExternal;
  }

  @Explain(displayName = "like")
  public String getLikeTableName() {
    return likeTableName;
  }

  public void setLikeTableName(String likeTableName) {
    this.likeTableName = likeTableName;
  }

}
