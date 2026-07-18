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


package org.apache.hadoop.hive.ql.udf.generic;

import java.util.ArrayList;
import java.util.List;

import org.apache.hadoop.hive.ql.exec.Description;
import org.apache.hadoop.hive.ql.exec.UDFArgumentException;
import org.apache.hadoop.hive.ql.metadata.HiveException;
import org.apache.hadoop.hive.serde2.objectinspector.ListObjectInspector;
import org.apache.hadoop.hive.serde2.objectinspector.ObjectInspector;
import org.apache.hadoop.hive.serde2.objectinspector.ObjectInspectorFactory;
import org.apache.hadoop.hive.serde2.objectinspector.StructObjectInspector;

/**
 * GenericUDTFExplode.
 *
 */
@Description(name = "explode",
    value = "_FUNC_(a) - separates the elements of array a into multiple rows ")
public class GenericUDTFExplode extends GenericUDTF {

  private ListObjectInspector listOI = null;

  @Override
  public void close() throws HiveException {
  }

  @Override
  public StructObjectInspector initialize(ObjectInspector[] args) throws UDFArgumentException {
    if (args.length != 1) {
      throw new UDFArgumentException("explode() takes only one argument");
    }

    if (args[0].getCategory() != ObjectInspector.Category.LIST) {
      throw new UDFArgumentException("explode() takes an array as a parameter");
    }
    listOI = (ListObjectInspector) args[0];

    ArrayList<String> fieldNames = new ArrayList<String>();
    ArrayList<ObjectInspector> fieldOIs = new ArrayList<ObjectInspector>();
    fieldNames.add("col");
    fieldOIs.add(listOI.getListElementObjectInspector());
    return ObjectInspectorFactory.getStandardStructObjectInspector(fieldNames,
        fieldOIs);
  }

  private final Object[] forwardObj = new Object[1];

  @Override
  public void process(Object[] o) throws HiveException {
    List<?> list = listOI.getList(o[0]);
    if(list == null) {
      return;
    }
    for (Object r : list) {
      forwardObj[0] = r;
      forward(forwardObj);
    }
  }

  @Override
  public String toString() {
    return "explode";
  }
}
