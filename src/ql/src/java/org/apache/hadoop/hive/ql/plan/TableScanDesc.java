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

import org.apache.hadoop.hive.ql.metadata.VirtualColumn;

/**
 * Table Scan Descriptor Currently, data is only read from a base source as part
 * of map-reduce framework. So, nothing is stored in the descriptor. But, more
 * things will be added here as table scan is invoked as part of local work.
 **/
@Explain(displayName = "TableScan")
public class TableScanDesc implements Serializable {
  private static final long serialVersionUID = 1L;

  private String alias;

  private List<VirtualColumn> virtualCols;
  private String statsAggKeyPrefix;   // stats publishing/aggregating key prefix

 /**
  * A list of the partition columns of the table.
  * Set by the semantic analyzer only in case of the analyze command.
  */
  private List<String> partColumns;

  /**
   * A boolean variable set to true by the semantic analyzer only in case of the analyze command.
   *
   */
  private boolean gatherStats;

  private ExprNodeDesc filterExpr;

  public static final String FILTER_EXPR_CONF_STR =
    "hive.io.filter.expr.serialized";

  public static final String FILTER_TEXT_CONF_STR =
    "hive.io.filter.text";

  @SuppressWarnings("nls")
  public TableScanDesc() {
  }

  public TableScanDesc(final String alias) {
    this.alias = alias;
  }

  public TableScanDesc(final String alias, List<VirtualColumn> vcs) {
    this.alias = alias;
    this.virtualCols = vcs;
  }

  @Explain(displayName = "alias")
  public String getAlias() {
    return alias;
  }

  @Explain(displayName = "filterExpr")
  public ExprNodeDesc getFilterExpr() {
    return filterExpr;
  }

  public void setFilterExpr(ExprNodeDesc filterExpr) {
    this.filterExpr = filterExpr;
  }

  public void setAlias(String alias) {
    this.alias = alias;
  }

  public void setPartColumns (List<String> partColumns) {
    this.partColumns = partColumns;
  }

  public List<String> getPartColumns () {
    return partColumns;
  }

  public void setGatherStats(boolean gatherStats) {
    this.gatherStats = gatherStats;
  }

  @Explain(displayName = "GatherStats", normalExplain = false)
  public boolean isGatherStats() {
    return gatherStats;
  }

  public List<VirtualColumn> getVirtualCols() {
    return virtualCols;
  }

  public void setVirtualCols(List<VirtualColumn> virtualCols) {
    this.virtualCols = virtualCols;
  }

  public void setStatsAggPrefix(String k) {
    statsAggKeyPrefix = k;
  }

  @Explain(displayName = "Statistics Aggregation Key Prefix", normalExplain = false)
  public String getStatsAggPrefix() {
    return statsAggKeyPrefix;
  }
}
