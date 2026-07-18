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

import java.util.HashMap;

import org.apache.hadoop.hive.ql.exec.TableScanOperator;
import org.apache.hadoop.hive.ql.lib.NodeProcessorCtx;
import org.apache.hadoop.hive.ql.plan.ExprNodeDesc;

/**
 * Context class for operator tree walker for partition pruner.
 */
public class OpWalkerCtx implements NodeProcessorCtx {

  private boolean hasNonPartCols;

  /**
   * Map from tablescan operator to partition pruning predicate that is
   * initialized from the ParseContext.
   */
  private final HashMap<TableScanOperator, ExprNodeDesc> opToPartPruner;

  /**
   * Constructor.
   */
  public OpWalkerCtx(HashMap<TableScanOperator, ExprNodeDesc> opToPartPruner) {
    this.opToPartPruner = opToPartPruner;
    hasNonPartCols = false;
  }

  public HashMap<TableScanOperator, ExprNodeDesc> getOpToPartPruner() {
    return opToPartPruner;
  }

  public void addHasNonPartCols(boolean val) {
    hasNonPartCols = (hasNonPartCols || val);
  }

  public boolean getHasNonPartCols() {
    return hasNonPartCols;
  }
}
