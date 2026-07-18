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

import org.apache.hadoop.hive.ql.parse.BaseSemanticAnalyzer.tableSpec;

/**
 * ConditionalStats.
 *
 */
@Explain(displayName = "Stats-Aggr Operator")
public class StatsWork implements Serializable {
  private static final long serialVersionUID = 1L;

  private tableSpec tableSpecs;        // source table spec -- for TableScanOperator
  private LoadTableDesc loadTableDesc; // same as MoveWork.loadTableDesc -- for FileSinkOperator
  private String aggKey;               // aggregation key prefix

  public StatsWork() {
  }

  public StatsWork(tableSpec tableSpecs) {
    this.tableSpecs = tableSpecs;
  }

  public StatsWork(LoadTableDesc loadTableDesc) {
    this.loadTableDesc = loadTableDesc;
  }

  public tableSpec getTableSpecs() {
    return tableSpecs;
  }

  public LoadTableDesc getLoadTableDesc() {
    return loadTableDesc;
  }

  public void setAggKey(String aggK) {
    aggKey = aggK;
  }

  @Explain(displayName = "Stats Aggregation Key Prefix", normalExplain = false)
  public String getAggKey() {
    return aggKey;
  }

}
