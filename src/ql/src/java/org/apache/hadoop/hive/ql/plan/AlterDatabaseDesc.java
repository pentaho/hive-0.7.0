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
import java.util.Map;

/**
 * AlterDatabaseDesc.
 *
 */
@Explain(displayName = "Create Database")
public class AlterDatabaseDesc extends DDLDesc implements Serializable {

  private static final long serialVersionUID = 1L;

  String databaseName;
  String locationUri;
  String comment;
  boolean ifNotExists;
  Map<String, String> dbProperties;

  /**
   * For serialization only.
   */
  public AlterDatabaseDesc() {
  }

  public AlterDatabaseDesc(String databaseName, String comment,
      String locationUri, boolean ifNotExists) {
    super();
    this.databaseName = databaseName;
    this.comment = comment;
    this.locationUri = locationUri;
    this.ifNotExists = ifNotExists;
    this.dbProperties = null;
  }

  public AlterDatabaseDesc(String databaseName, boolean ifNotExists) {
    this(databaseName, null, null, ifNotExists);
  }



  @Explain(displayName="if not exists")
  public boolean getIfNotExists() {
    return ifNotExists;
  }

  public void setIfNotExists(boolean ifNotExists) {
    this.ifNotExists = ifNotExists;
  }

  public Map<String, String> getDatabaseProperties() {
    return dbProperties;
  }

  public void setDatabaseProperties(Map<String, String> dbProps) {
    this.dbProperties = dbProps;
  }

  @Explain(displayName="name")
  public String getDatabaseName() {
    return databaseName;
  }

  public void setDatabaseName(String databaseName) {
    this.databaseName = databaseName;
  }

  @Explain(displayName="comment")
  public String getComment() {
    return comment;
  }

  public void setComment(String comment) {
    this.comment = comment;
  }

  @Explain(displayName="locationUri")
  public String getLocationUri() {
    return locationUri;
  }

  public void setLocationUri(String locationUri) {
    this.locationUri = locationUri;
  }
}
