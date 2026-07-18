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
 * DescTableDesc.
 *
 */
@Explain(displayName = "Describe Table")
public class DescTableDesc extends DDLDesc implements Serializable {
  public void setPartSpec(HashMap<String, String> partSpec) {
    this.partSpec = partSpec;
  }

  private static final long serialVersionUID = 1L;

  String tableName;
  HashMap<String, String> partSpec;
  String resFile;
  boolean isExt;
  boolean isFormatted;
  /**
   * table name for the result of describe table.
   */
  private static final String table = "describe";
  /**
   * thrift ddl for the result of describe table.
   */
  private static final String schema = "col_name,data_type,comment#string:string:string";

  public DescTableDesc() {
  }

  /**
   * @param partSpec
   * @param resFile
   * @param tableName
   */
  public DescTableDesc(Path resFile, String tableName,
      HashMap<String, String> partSpec) {
    this.isExt = false;
    this.isFormatted = false;
    this.partSpec = partSpec;
    this.resFile = resFile.toString();
    this.tableName = tableName;
  }

  public String getTable() {
    return table;
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
   * @return the isFormatted
   */
  public boolean isFormatted() {
    return isFormatted;
  }

  /**
   * @param isFormatted
   *          the isFormat to set
   */
  public void setFormatted(boolean isFormat) {
    this.isFormatted = isFormat;
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
}
