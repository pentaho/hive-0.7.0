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

import java.io.Serializable;

import org.apache.hadoop.hive.ql.metadata.HiveException;
import org.apache.hadoop.hive.ql.plan.ForwardDesc;
import org.apache.hadoop.hive.ql.plan.api.OperatorType;

/**
 * Forward Operator Just forwards. Doesn't do anything itself.
 **/
public class ForwardOperator extends Operator<ForwardDesc> implements
    Serializable {
  private static final long serialVersionUID = 1L;

  @Override
  public void processOp(Object row, int tag) throws HiveException {
    forward(row, inputObjInspectors[tag]);
  }

  @Override
  public OperatorType getType() {
    return OperatorType.FORWARD;
  }
}
