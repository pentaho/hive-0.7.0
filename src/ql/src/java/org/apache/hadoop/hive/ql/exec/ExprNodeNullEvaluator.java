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
import org.apache.hadoop.hive.ql.plan.ExprNodeNullDesc;
import org.apache.hadoop.hive.serde2.objectinspector.ObjectInspector;
import org.apache.hadoop.hive.serde2.objectinspector.primitive.PrimitiveObjectInspectorFactory;

// This function will not be used currently, since the function expressions
// change the void to the first matching argument
/**
 * ExprNodeNullEvaluator.
 *
 */
public class ExprNodeNullEvaluator extends ExprNodeEvaluator {

  protected ExprNodeNullDesc expr;

  public ExprNodeNullEvaluator(ExprNodeNullDesc expr) {
    this.expr = expr;
  }

  @Override
  public ObjectInspector initialize(ObjectInspector rowInspector) throws HiveException {
    return PrimitiveObjectInspectorFactory.writableVoidObjectInspector;
  }

  @Override
  public Object evaluate(Object row) throws HiveException {
    return null;
  }

}
