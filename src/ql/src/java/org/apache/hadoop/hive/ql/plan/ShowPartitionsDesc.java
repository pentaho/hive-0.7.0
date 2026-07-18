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

import org.apache.hadoop.fs.Path;

/**
 * ShowPartitionsDesc.
 *
 */
@Explain(displayName = "Show Partitions")
public class ShowPartitionsDesc extends DDLDesc implements Serializable {
  private static final long serialVersionUID = 1L;
  String tabName;
  String resFile;
  // Filter the partitions to show based on on supplied spec
  Map<String, String> partSpec;

  /**
   * table name for the result of show tables.
   */
  private static final String table = "showpartitions";
  /**
   * thrift ddl for the result of show tables.
   */
  private static final String schema = "partition#string";

  public String getTable() {
    return table;
  }

  public String getSchema() {
    return schema;
  }

  public ShowPartitionsDesc() {
  }

  /**
   * @param tabName
   *          Name of the table whose partitions need to be listed.
   * @param resFile
   *          File to store the results in
   */
  public ShowPartitionsDesc(String tabName, Path resFile,
      Map<String, String> partSpec) {
    this.tabName = tabName;
    this.resFile = resFile.toString();
    this.partSpec = partSpec;
  }

  /**
   * @return the name of the table.
   */
  @Explain(displayName = "table")
  public String getTabName() {
    return tabName;
  }

  /**
   * @param tabName
   *          the table whose partitions have to be listed
   */
  public void setTabName(String tabName) {
    this.tabName = tabName;
  }

  /**
   * @return the name of the table.
   */
  @Explain(displayName = "partSpec")
  public Map<String, String> getPartSpec() {
    return partSpec;
  }

  /**
   * @param tabName
   *          the table whose partitions have to be listed
   */
  public void setPartSpec(Map<String, String> partSpec) {
    this.partSpec = partSpec;
  }

  /**
   * @return the results file
   */
  @Explain(displayName = "result file", normalExplain = false)
  public String getResFile() {
    return resFile;
  }

  /**
   * @param resFile
   *          the results file to be used to return the results
   */
  public void setResFile(String resFile) {
    this.resFile = resFile;
  }
}
