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
import java.util.List;

/**
 * LoadMultiFilesDesc.
 *
 */
public class LoadMultiFilesDesc implements Serializable {
  private static final long serialVersionUID = 1L;
  private List<String> targetDirs;
  private boolean isDfsDir;
  // list of columns, comma separated
  private String columns;
  private String columnTypes;
  private List<String> srcDirs;

  public LoadMultiFilesDesc() {
  }

  public LoadMultiFilesDesc(final List<String> sourceDirs, final List<String> targetDir,
      final boolean isDfsDir, final String columns, final String columnTypes) {

    this.srcDirs = sourceDirs;
    this.targetDirs = targetDir;
    this.isDfsDir = isDfsDir;
    this.columns = columns;
    this.columnTypes = columnTypes;
  }

  @Explain(displayName = "destinations")
  public List<String> getTargetDirs() {
    return targetDirs;
  }

  @Explain(displayName = "sources")
  public List<String> getSourceDirs() {
    return srcDirs;
  }

  public void setSourceDirs(List<String> srcs) {
    this.srcDirs = srcs;
  }

  public void setTargetDirs(final List<String> targetDir) {
    this.targetDirs = targetDir;
  }

  @Explain(displayName = "hdfs directory")
  public boolean getIsDfsDir() {
    return isDfsDir;
  }

  public void setIsDfsDir(final boolean isDfsDir) {
    this.isDfsDir = isDfsDir;
  }

  /**
   * @return the columns
   */
  public String getColumns() {
    return columns;
  }

  /**
   * @param columns
   *          the columns to set
   */
  public void setColumns(String columns) {
    this.columns = columns;
  }

  /**
   * @return the columnTypes
   */
  public String getColumnTypes() {
    return columnTypes;
  }

  /**
   * @param columnTypes
   *          the columnTypes to set
   */
  public void setColumnTypes(String columnTypes) {
    this.columnTypes = columnTypes;
  }
}
