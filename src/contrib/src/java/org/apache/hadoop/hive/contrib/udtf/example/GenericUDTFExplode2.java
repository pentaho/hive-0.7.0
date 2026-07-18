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


package org.apache.hadoop.hive.contrib.udtf.example;

import java.util.ArrayList;
import java.util.List;

import org.apache.hadoop.hive.ql.exec.Description;
import org.apache.hadoop.hive.ql.exec.UDFArgumentException;
import org.apache.hadoop.hive.ql.metadata.HiveException;
import org.apache.hadoop.hive.ql.udf.generic.GenericUDTF;
import org.apache.hadoop.hive.serde2.objectinspector.ListObjectInspector;
import org.apache.hadoop.hive.serde2.objectinspector.ObjectInspector;
import org.apache.hadoop.hive.serde2.objectinspector.ObjectInspectorFactory;
import org.apache.hadoop.hive.serde2.objectinspector.StructObjectInspector;

/**
 * GenericUDTFExplode2.
 *
 */
@Description(name = "explode2",
    value = "_FUNC_(a) - like explode, but outputs two identical columns (for testing purposes)")
public class GenericUDTFExplode2 extends GenericUDTF {

  ListObjectInspector listOI = null;

  @Override
  public void close() throws HiveException {
  }

  @Override
  public StructObjectInspector initialize(ObjectInspector[] args)
      throws UDFArgumentException {

    if (args.length != 1) {
      throw new UDFArgumentException("explode() takes only one argument");
    }

    if (args[0].getCategory() != ObjectInspector.Category.LIST) {
      throw new UDFArgumentException("explode() takes an array as a parameter");
    }
    listOI = (ListObjectInspector) args[0];

    ArrayList<String> fieldNames = new ArrayList<String>();
    ArrayList<ObjectInspector> fieldOIs = new ArrayList<ObjectInspector>();
    fieldNames.add("col1");
    fieldNames.add("col2");
    fieldOIs.add(listOI.getListElementObjectInspector());
    fieldOIs.add(listOI.getListElementObjectInspector());
    return ObjectInspectorFactory.getStandardStructObjectInspector(fieldNames,
        fieldOIs);
  }

  Object forwardObj[] = new Object[2];

  @Override
  public void process(Object[] o) throws HiveException {

    List<?> list = listOI.getList(o[0]);
    for (Object r : list) {
      forwardObj[0] = r;
      forwardObj[1] = r;
      forward(forwardObj);
    }
  }

  @Override
  public String toString() {
    return "explode";
  }
}
