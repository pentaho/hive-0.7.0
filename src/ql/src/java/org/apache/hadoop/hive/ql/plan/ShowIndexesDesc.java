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
 * ShowIndexesDesc.
 * Returns table index information per SQL syntax.
 */
@Explain(displayName = "Show Indexes")
public class ShowIndexesDesc extends DDLDesc implements Serializable {
  private static final long serialVersionUID = 1L;
  String tableName;
  String resFile;
  boolean isFormatted;

  /**
   * thrift ddl for the result of show indexes.
   */
  private static final String schema = "idx_name,tab_name,col_names,idx_tab_name,idx_type,comment"
                                        + "#string:string:string:string:string:string";

  public static String getSchema() {
    return schema;
  }

  public String getTableName() {
    return tableName;
  }

  public String getResFile() {
    return resFile;
  }

  public boolean isFormatted() {
    return isFormatted;
  }

  public void setFormatted(boolean isFormatted) {
    this.isFormatted = isFormatted;
  }

  /**
   *
   * @param tableName
   *          Name of the table whose indexes need to be listed.
   * @param resFile
   *          File to store the results in.
   */
  public ShowIndexesDesc(String tableName, Path resFile) {
    this.tableName = tableName;
    this.resFile = resFile.toString();
  }
}
