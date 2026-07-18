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
 * DescFunctionDesc.
 *
 */
@Explain(displayName = "Describe Function")
public class DescFunctionDesc extends DDLDesc implements Serializable {
  private static final long serialVersionUID = 1L;
  String name;
  String resFile;
  boolean isExtended;

  public boolean isExtended() {
    return isExtended;
  }

  public void setExtended(boolean isExtended) {
    this.isExtended = isExtended;
  }

  /**
   * table name for the result of show tables.
   */
  private static final String table = "show";
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

  public DescFunctionDesc() {
  }

  /**
   * @param resFile
   */
  public DescFunctionDesc(Path resFile) {
    this.resFile = resFile.toString();
    name = null;
  }

  /**
   * @param name
   *          of the function to describe
   */
  public DescFunctionDesc(Path resFile, String name, boolean isExtended) {
    this.isExtended = isExtended;
    this.resFile = resFile.toString();
    this.name = name;
  }

  /**
   * @return the name
   */
  @Explain(displayName = "name")
  public String getName() {
    return name;
  }

  /**
   * @param name
   *          is the function name
   */
  public void setName(String name) {
    this.name = name;
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
