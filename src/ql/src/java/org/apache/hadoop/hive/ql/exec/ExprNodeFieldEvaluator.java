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

import java.util.ArrayList;
import java.util.List;

import org.apache.hadoop.hive.ql.metadata.HiveException;
import org.apache.hadoop.hive.ql.plan.ExprNodeFieldDesc;
import org.apache.hadoop.hive.serde2.objectinspector.ListObjectInspector;
import org.apache.hadoop.hive.serde2.objectinspector.ObjectInspector;
import org.apache.hadoop.hive.serde2.objectinspector.ObjectInspectorFactory;
import org.apache.hadoop.hive.serde2.objectinspector.StructField;
import org.apache.hadoop.hive.serde2.objectinspector.StructObjectInspector;

/**
 * This Evaluator can evaluate s.f for s as both struct and list of struct. If s
 * is struct, then s.f is the field. If s is list of struct, then s.f is the
 * list of struct field.
 */
public class ExprNodeFieldEvaluator extends ExprNodeEvaluator {

  protected ExprNodeFieldDesc desc;
  transient ExprNodeEvaluator leftEvaluator;
  transient ObjectInspector leftInspector;
  transient StructObjectInspector structObjectInspector;
  transient StructField field;
  transient ObjectInspector structFieldObjectInspector;
  transient ObjectInspector resultObjectInspector;

  public ExprNodeFieldEvaluator(ExprNodeFieldDesc desc) {
    this.desc = desc;
    leftEvaluator = ExprNodeEvaluatorFactory.get(desc.getDesc());
  }

  @Override
  public ObjectInspector initialize(ObjectInspector rowInspector) throws HiveException {

    leftInspector = leftEvaluator.initialize(rowInspector);
    if (desc.getIsList()) {
      structObjectInspector = (StructObjectInspector) ((ListObjectInspector) leftInspector)
          .getListElementObjectInspector();
    } else {
      structObjectInspector = (StructObjectInspector) leftInspector;
    }
    field = structObjectInspector.getStructFieldRef(desc.getFieldName());
    structFieldObjectInspector = field.getFieldObjectInspector();

    if (desc.getIsList()) {
      resultObjectInspector = ObjectInspectorFactory
          .getStandardListObjectInspector(structFieldObjectInspector);
    } else {
      resultObjectInspector = structFieldObjectInspector;
    }
    return resultObjectInspector;
  }

  private List<Object> cachedList = new ArrayList<Object>();

  @Override
  public Object evaluate(Object row) throws HiveException {

    // Get the result in leftInspectableObject
    Object left = leftEvaluator.evaluate(row);

    if (desc.getIsList()) {
      List<?> list = ((ListObjectInspector) leftInspector).getList(left);
      if (list == null) {
        return null;
      } else {
        cachedList.clear();
        for (int i = 0; i < list.size(); i++) {
          cachedList.add(structObjectInspector.getStructFieldData(list.get(i),
              field));
        }
        return cachedList;
      }
    } else {
      return structObjectInspector.getStructFieldData(left, field);
    }
  }

}
