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


package org.apache.hadoop.hive.ql.optimizer.pcr;

import java.util.List;

import org.apache.hadoop.hive.ql.lib.NodeProcessorCtx;
import org.apache.hadoop.hive.ql.metadata.Partition;

/**
 * The processor context for partition condition remover. This contains
 * partition pruned for the table scan and table alias.
 */
public class PcrExprProcCtx implements NodeProcessorCtx {

  public PcrExprProcCtx(String tabAlias, List<Partition> partList) {
    super();
    this.tabAlias = tabAlias;
    this.partList = partList;
  }

  /**
   * The table alias that is being currently processed.
   */
  String tabAlias;
  List<Partition> partList;

  public String getTabAlias() {
    return tabAlias;
  }

  public void setTabAlias(String tabAlias) {
    this.tabAlias = tabAlias;
  }

  public List<Partition> getPartList() {
    return partList;
  }

  public void setPartList(List<Partition> partList) {
    this.partList = partList;
  }
}
