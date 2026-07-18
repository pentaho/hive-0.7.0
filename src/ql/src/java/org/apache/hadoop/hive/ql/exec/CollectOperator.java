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

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hive.ql.metadata.HiveException;
import org.apache.hadoop.hive.ql.plan.api.OperatorType;
import org.apache.hadoop.hive.ql.plan.CollectDesc;
import org.apache.hadoop.hive.serde2.objectinspector.InspectableObject;
import org.apache.hadoop.hive.serde2.objectinspector.ObjectInspector;
import org.apache.hadoop.hive.serde2.objectinspector.ObjectInspectorUtils;

/**
 * Buffers rows emitted by other operators.
 **/
public class CollectOperator extends Operator<CollectDesc> implements
    Serializable {

  private static final long serialVersionUID = 1L;
  protected transient ArrayList<Object> rowList;
  protected transient ObjectInspector standardRowInspector;
  transient int maxSize;

  @Override
  protected void initializeOp(Configuration hconf) throws HiveException {
    super.initializeOp(hconf);
    rowList = new ArrayList<Object>();
    maxSize = conf.getBufferSize().intValue();
  }

  boolean firstRow = true;

  @Override
  public void processOp(Object row, int tag) throws HiveException {
    ObjectInspector rowInspector = inputObjInspectors[tag];
    if (firstRow) {
      firstRow = false;
      // Get the standard ObjectInspector of the row
      standardRowInspector = ObjectInspectorUtils
          .getStandardObjectInspector(rowInspector);
    }

    if (rowList.size() < maxSize) {
      // Create a standard copy of the object.
      Object o = ObjectInspectorUtils.copyToStandardObject(row, rowInspector);
      rowList.add(o);
    }
    forward(row, rowInspector);
  }

  public void retrieve(InspectableObject result) {
    assert (result != null);
    if (rowList.isEmpty()) {
      result.o = null;
      result.oi = null;
    } else {
      result.o = rowList.remove(0);
      result.oi = standardRowInspector;
    }
  }

  @Override
  public OperatorType getType() {
    return null;
  }
}
