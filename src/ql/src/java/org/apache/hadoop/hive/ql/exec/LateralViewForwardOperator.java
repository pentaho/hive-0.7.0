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


package org.apache.hadoop.hive.ql.exec;

import org.apache.hadoop.hive.ql.metadata.HiveException;
import org.apache.hadoop.hive.ql.plan.LateralViewForwardDesc;
import org.apache.hadoop.hive.ql.plan.api.OperatorType;

/**
 * LateralViewForwardOperator. This operator sits at the head of the operator
 * DAG for a lateral view. This does nothing, but it aids the predicate push
 * down during traversal to identify when a lateral view occurs.
 *
 */
public class LateralViewForwardOperator extends Operator<LateralViewForwardDesc> {

  private static final long serialVersionUID = 1L;

  @Override
  public void processOp(Object row, int tag) throws HiveException {
    forward(row, inputObjInspectors[tag]);
  }

  @Override
  public String getName() {
    return "LVF";
  }

  @Override
  public OperatorType getType() {
    return OperatorType.LATERALVIEWFORWARD;
  }
}
