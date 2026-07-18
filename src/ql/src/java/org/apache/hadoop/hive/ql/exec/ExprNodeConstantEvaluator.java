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
import org.apache.hadoop.hive.ql.plan.ExprNodeConstantDesc;
import org.apache.hadoop.hive.serde2.objectinspector.ObjectInspector;
import org.apache.hadoop.hive.serde2.objectinspector.PrimitiveObjectInspector.PrimitiveCategory;
import org.apache.hadoop.hive.serde2.objectinspector.primitive.PrimitiveObjectInspectorFactory;
import org.apache.hadoop.hive.serde2.typeinfo.PrimitiveTypeInfo;

/**
 * ExprNodeConstantEvaluator.
 *
 */
public class ExprNodeConstantEvaluator extends ExprNodeEvaluator {

  protected ExprNodeConstantDesc expr;
  transient ObjectInspector writableObjectInspector;
  transient Object writableValue;

  public ExprNodeConstantEvaluator(ExprNodeConstantDesc expr) {
    this.expr = expr;
    PrimitiveCategory pc = ((PrimitiveTypeInfo) expr.getTypeInfo())
        .getPrimitiveCategory();
    writableObjectInspector = PrimitiveObjectInspectorFactory
        .getPrimitiveWritableObjectInspector(pc);
    // Convert from Java to Writable
    writableValue = PrimitiveObjectInspectorFactory
        .getPrimitiveJavaObjectInspector(pc).getPrimitiveWritableObject(
        expr.getValue());
  }

  @Override
  public ObjectInspector initialize(ObjectInspector rowInspector) throws HiveException {
    return writableObjectInspector;
  }

  @Override
  public Object evaluate(Object row) throws HiveException {
    return writableValue;
  }

}
