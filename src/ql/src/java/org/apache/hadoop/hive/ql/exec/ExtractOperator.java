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

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hive.ql.metadata.HiveException;
import org.apache.hadoop.hive.ql.plan.ExtractDesc;
import org.apache.hadoop.hive.ql.plan.api.OperatorType;

/**
 * Extract operator implementation Extracts a subobject and passes that on.
 **/
public class ExtractOperator extends Operator<ExtractDesc> implements
    Serializable {
  private static final long serialVersionUID = 1L;
  protected transient ExprNodeEvaluator eval;

  @Override
  protected void initializeOp(Configuration hconf) throws HiveException {
    eval = ExprNodeEvaluatorFactory.get(conf.getCol());
    outputObjInspector = eval.initialize(inputObjInspectors[0]);
    initializeChildren(hconf);
  }

  @Override
  public void processOp(Object row, int tag) throws HiveException {
    forward(eval.evaluate(row), outputObjInspector);
  }

  @Override
  public OperatorType getType() {
    return OperatorType.EXTRACT;
  }
}
