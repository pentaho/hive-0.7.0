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


package org.apache.hadoop.hive.serde2.objectinspector;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.hadoop.hive.serde2.ColumnSet;
import org.apache.hadoop.hive.serde2.objectinspector.PrimitiveObjectInspector.PrimitiveCategory;
import org.apache.hadoop.hive.serde2.objectinspector.primitive.PrimitiveObjectInspectorFactory;

/**
 * StructObjectInspector works on struct data that is stored as a Java List or
 * Java Array object. Basically, the fields are stored sequentially in the List
 * object.
 * 
 * The names of the struct fields and the internal structure of the struct
 * fields are specified in the ctor of the StructObjectInspector.
 * 
 */
public class MetadataListStructObjectInspector extends
    StandardStructObjectInspector {

  static HashMap<List<String>, MetadataListStructObjectInspector> cached = new HashMap<List<String>, MetadataListStructObjectInspector>();

  // public static MetadataListStructObjectInspector getInstance(int fields) {
  // return getInstance(ObjectInspectorUtils.getIntegerArray(fields));
  // }
  public static MetadataListStructObjectInspector getInstance(
      List<String> columnNames) {
    MetadataListStructObjectInspector result = cached.get(columnNames);
    if (result == null) {
      result = new MetadataListStructObjectInspector(columnNames);
      cached.put(columnNames, result);
    }
    return result;
  }

  static ArrayList<ObjectInspector> getFieldObjectInspectors(int fields) {
    ArrayList<ObjectInspector> r = new ArrayList<ObjectInspector>(fields);
    for (int i = 0; i < fields; i++) {
      r.add(PrimitiveObjectInspectorFactory
          .getPrimitiveJavaObjectInspector(PrimitiveCategory.STRING));
    }
    return r;
  }

  MetadataListStructObjectInspector(List<String> columnNames) {
    super(columnNames, getFieldObjectInspectors(columnNames.size()));
  }

  // Get col object out
  @Override
  public Object getStructFieldData(Object data, StructField fieldRef) {
    if (data instanceof ColumnSet) {
      data = ((ColumnSet) data).col;
    }
    return super.getStructFieldData(data, fieldRef);
  }

  // Get col object out
  @Override
  public List<Object> getStructFieldsDataAsList(Object data) {
    if (data instanceof ColumnSet) {
      data = ((ColumnSet) data).col;
    }
    return super.getStructFieldsDataAsList(data);
  }

}
