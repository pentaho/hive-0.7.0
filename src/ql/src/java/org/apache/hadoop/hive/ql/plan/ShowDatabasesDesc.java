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
 * ShowDatabasesDesc.
 *
 */
@Explain(displayName = "Show Databases")
public class ShowDatabasesDesc extends DDLDesc implements Serializable {
  private static final long serialVersionUID = 1L;
  String pattern;
  String resFile;

  /**
   * table name for the result of show databases.
   */
  private static final String table = "show_databases";

  /**
   * thrift ddl for the result of show databases.
   */
  private static final String schema = "database_name#string";

  public String getTable() {
    return table;
  }

  public String getSchema() {
    return schema;
  }

  public ShowDatabasesDesc() {
  }

  /**
   * @param resFile
   */
  public ShowDatabasesDesc(Path resFile) {
    this.resFile = resFile.toString();
    pattern = null;
  }

  /**
   * @param pattern
   *          names of databases to show
   */
  public ShowDatabasesDesc(Path resFile, String pattern) {
    this.resFile = resFile.toString();
    this.pattern = pattern;
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
