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

import org.apache.hadoop.fs.Path;

/**
 * DescDatabaseDesc.
 *
 */
@Explain(displayName = "Describe Database")
public class DescDatabaseDesc extends DDLDesc implements Serializable {

  private static final long serialVersionUID = 1L;

  String dbName;
  String resFile;
  boolean isExt;

  /**
   * thrift ddl for the result of describe database.
   */
  private static final String schema = "db_name,comment,location,parameters#string:string:string:string";

  public DescDatabaseDesc() {
  }

  /**
   * @param partSpec
   * @param resFile
   * @param tableName
   */
  public DescDatabaseDesc(Path resFile, String dbName, boolean isExt) {
    this.isExt = isExt;
    this.resFile = resFile.toString();
    this.dbName = dbName;
  }

  public static String getSchema() {
    return schema;
  }

  /**
   * @return the isExt
   */
  public boolean isExt() {
    return isExt;
  }

  /**
   * @param isExt
   *          the isExt to set
   */
  public void setExt(boolean isExt) {
    this.isExt = isExt;
  }

  /**
   * @return the tableName
   */
  @Explain(displayName = "database")
  public String getDatabaseName() {
    return dbName;
  }

  /**
   * @param tableName
   *          the tableName to set
   */
  public void setDatabaseName(String db) {
    this.dbName = db;
  }

  /**
   * @return the resFile
   */
  @Explain(displayName = "result file", normalExplain = false)
  public String getResFile() {
    return resFile;
  }

  /**
   * @param resFile
   *          the resFile to set
   */
  public void setResFile(String resFile) {
    this.resFile = resFile;
  }
}
