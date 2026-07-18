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
 * ShowLocksDesc.
 *
 */
@Explain(displayName = "Show Locks")
public class ShowLocksDesc extends DDLDesc implements Serializable {
  private static final long serialVersionUID = 1L;
  String resFile;
  String tableName;
  HashMap<String, String> partSpec;
  boolean isExt;

  /**
   * table name for the result of show locks.
   */
  private static final String table = "showlocks";
  /**
   * thrift ddl for the result of show locks.
   */
  private static final String schema = "tab_name,mode#string:string";

  public String getTable() {
    return table;
  }

  public String getSchema() {
    return schema;
  }

  public ShowLocksDesc() {
  }

  /**
   * @param resFile
   */
  public ShowLocksDesc(Path resFile, String tableName,
                       HashMap<String, String> partSpec, boolean isExt) {
    this.resFile   = resFile.toString();
    this.partSpec  = partSpec;
    this.tableName = tableName;
    this.isExt     = isExt;
  }

  /**
   * @return the tableName
   */
  @Explain(displayName = "table")
  public String getTableName() {
    return tableName;
  }

  /**
   * @param tableName
   *          the tableName to set
   */
  public void setTableName(String tableName) {
    this.tableName = tableName;
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
  public void setPartSpecs(HashMap<String, String> partSpec) {
    this.partSpec = partSpec;
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
}
