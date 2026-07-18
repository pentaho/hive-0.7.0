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
import java.util.HashMap;

import org.apache.hadoop.fs.Path;

/**
 * ShowTableStatusDesc.
 *
 */
@Explain(displayName = "Show Table Status")
public class ShowTableStatusDesc extends DDLDesc implements Serializable {
  private static final long serialVersionUID = 1L;
  String pattern;
  String resFile;
  String dbName;
  HashMap<String, String> partSpec;

  /**
   * table name for the result of show tables.
   */
  private static final String table = "show_tablestatus";
  /**
   * thrift ddl for the result of show tables.
   */
  private static final String schema = "tab_name#string";

  public String getTable() {
    return table;
  }

  public String getSchema() {
    return schema;
  }

  /**
   * For serializatino use only.
   */
  public ShowTableStatusDesc() {
  }
  
  /**
   * @param pattern
   *          names of tables to show
   */
  public ShowTableStatusDesc(String resFile, String dbName, String pattern) {
    this.dbName = dbName;
    this.resFile = resFile;
    this.pattern = pattern;
  }

  /**
   * @param resFile
   * @param dbName
   *          data base name
   * @param pattern
   *          names of tables to show
   * @param part
   *          partition specification
   */
  public ShowTableStatusDesc(String resFile, String dbName, String pattern,
      HashMap<String, String> partSpec) {
    this.dbName = dbName;
    this.resFile = resFile;
    this.pattern = pattern;
    this.partSpec = partSpec;
  }

  /**
   * @return the pattern
   */
  @Explain(displayName = "pattern")
  public String getPattern() {
    return pattern;
  }

  /**
   * @param pattern
   *          the pattern to set
   */
  public void setPattern(String pattern) {
    this.pattern = pattern;
  }

  /**
   * @return the resFile
   */
  public String getResFile() {
    return resFile;
  }

  @Explain(displayName = "result file", normalExplain = false)
  public String getResFileString() {
    return getResFile();
  }

  /**
   * @param resFile
   *          the resFile to set
   */
  public void setResFile(String resFile) {
    this.resFile = resFile;
  }

  /**
   * @return the database name
   */
  @Explain(displayName = "database")
  public String getDbName() {
    return dbName;
  }

  /**
   * @param dbName
   *          the database name
   */
  public void setDbName(String dbName) {
    this.dbName = dbName;
  }

  /**
   * @return the partSpec
   */
  @Explain(displayName = "partition")
  public HashMap<String, String> getPartSpec() {
    return partSpec;
  }

  /**
   * @param partSpec
   *          the partSpec to set
   */
  public void setPartSpec(HashMap<String, String> partSpec) {
    this.partSpec = partSpec;
  }
}
