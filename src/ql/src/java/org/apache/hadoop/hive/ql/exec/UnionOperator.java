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
import java.util.ArrayList;
import java.util.List;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hive.ql.metadata.HiveException;
import org.apache.hadoop.hive.ql.plan.UnionDesc;
import org.apache.hadoop.hive.ql.plan.api.OperatorType;
import org.apache.hadoop.hive.ql.udf.generic.GenericUDFUtils.ReturnObjectInspectorResolver;
import org.apache.hadoop.hive.serde2.objectinspector.ObjectInspector;
import org.apache.hadoop.hive.serde2.objectinspector.ObjectInspectorFactory;
import org.apache.hadoop.hive.serde2.objectinspector.StructField;
import org.apache.hadoop.hive.serde2.objectinspector.StructObjectInspector;

/**
 * Union Operator Just forwards. Doesn't do anything itself.
 **/
public class UnionOperator extends Operator<UnionDesc> implements Serializable {
  private static final long serialVersionUID = 1L;

  StructObjectInspector[] parentObjInspectors;
  List<? extends StructField>[] parentFields;

  ReturnObjectInspectorResolver[] columnTypeResolvers;
  boolean[] needsTransform;

  ArrayList<Object> outputRow;

  /**
   * UnionOperator will transform the input rows if the inputObjInspectors from
   * different parents are different. If one parent has exactly the same
   * ObjectInspector as the output ObjectInspector, then we don't need to do
   * transformation for that parent. This information is recorded in
   * needsTransform[].
   */
  @Override
  protected void initializeOp(Configuration hconf) throws HiveException {

    int parents = parentOperators.size();
    parentObjInspectors = new StructObjectInspector[parents];
    parentFields = new List[parents];
    for (int p = 0; p < parents; p++) {
      parentObjInspectors[p] = (StructObjectInspector) inputObjInspectors[p];
      parentFields[p] = parentObjInspectors[p].getAllStructFieldRefs();
    }

    // Get columnNames from the first parent
    int columns = parentFields[0].size();
    ArrayList<String> columnNames = new ArrayList<String>(columns);
    for (int c = 0; c < columns; c++) {
      columnNames.add(parentFields[0].get(c).getFieldName());
    }

    // Get outputFieldOIs
    columnTypeResolvers = new ReturnObjectInspectorResolver[columns];
    for (int c = 0; c < columns; c++) {
      columnTypeResolvers[c] = new ReturnObjectInspectorResolver();
    }

    for (int p = 0; p < parents; p++) {
      assert (parentFields[p].size() == columns);
      for (int c = 0; c < columns; c++) {
        columnTypeResolvers[c].update(parentFields[p].get(c)
            .getFieldObjectInspector());
      }
    }

    ArrayList<ObjectInspector> outputFieldOIs = new ArrayList<ObjectInspector>(
        columns);
    for (int c = 0; c < columns; c++) {
      outputFieldOIs.add(columnTypeResolvers[c].get());
    }

    // create output row ObjectInspector
    outputObjInspector = ObjectInspectorFactory
        .getStandardStructObjectInspector(columnNames, outputFieldOIs);
    outputRow = new ArrayList<Object>(columns);
    for (int c = 0; c < columns; c++) {
      outputRow.add(null);
    }

    // whether we need to do transformation for each parent
    needsTransform = new boolean[parents];
    for (int p = 0; p < parents; p++) {
      // Testing using != is good enough, because we use ObjectInspectorFactory
      // to
      // create ObjectInspectors.
      needsTransform[p] = (inputObjInspectors[p] != outputObjInspector);
      if (needsTransform[p]) {
        LOG.info("Union Operator needs to transform row from parent[" + p
            + "] from " + inputObjInspectors[p] + " to " + outputObjInspector);
      }
    }
    initializeChildren(hconf);
  }

  @Override
  public synchronized void processOp(Object row, int tag) throws HiveException {

    StructObjectInspector soi = parentObjInspectors[tag];
    List<? extends StructField> fields = parentFields[tag];

    if (needsTransform[tag]) {
      for (int c = 0; c < fields.size(); c++) {
        outputRow.set(c, columnTypeResolvers[c].convertIfNecessary(soi
            .getStructFieldData(row, fields.get(c)), fields.get(c)
            .getFieldObjectInspector()));
      }
      forward(outputRow, outputObjInspector);
    } else {
      forward(row, inputObjInspectors[tag]);
    }
  }

  /**
   * @return the name of the operator
   */
  @Override
  public String getName() {
    return new String("UNION");
  }

  @Override
  public OperatorType getType() {
    return OperatorType.UNION;
  }
}
