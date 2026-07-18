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


package org.apache.hadoop.hive.ql.optimizer.ppr;

import org.apache.hadoop.hive.ql.lib.NodeProcessorCtx;

/**
 * The processor context for partition pruner. This contains the table alias
 * that is being currently processed.
 */
public class ExprProcCtx implements NodeProcessorCtx {

  /**
   * The table alias that is being currently processed.
   */
  String tabAlias;

  /**
   * Flag to hold whether there are any non partition columns accessed in the
   * expression.
   */
  boolean hasNonPartCols;

  public ExprProcCtx(String tabAlias) {
    this.tabAlias = tabAlias;
    hasNonPartCols = false;
  }

  public String getTabAlias() {
    return tabAlias;
  }

  public void setTabAlias(String tabAlias) {
    this.tabAlias = tabAlias;
  }

  public boolean getHasNonPartCols() {
    return hasNonPartCols;
  }

  public void setHasNonPartCols(boolean val) {
    hasNonPartCols = val;
  }
}
