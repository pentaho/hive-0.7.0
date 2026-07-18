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


package org.apache.hadoop.hive.serde2.lazybinary.objectinspector;

import java.util.List;

import org.apache.hadoop.hive.serde2.lazybinary.LazyBinaryStruct;
import org.apache.hadoop.hive.serde2.objectinspector.ObjectInspector;
import org.apache.hadoop.hive.serde2.objectinspector.StandardStructObjectInspector;
import org.apache.hadoop.hive.serde2.objectinspector.StructField;

/**
 * ObjectInspector for LazyBinaryStruct.
 * 
 * @see LazyBinaryStruct
 */
public class LazyBinaryStructObjectInspector extends
    StandardStructObjectInspector {

  protected LazyBinaryStructObjectInspector(List<String> structFieldNames,
      List<ObjectInspector> structFieldObjectInspectors) {
    super(structFieldNames, structFieldObjectInspectors);
  }

  protected LazyBinaryStructObjectInspector(List<StructField> fields) {
    super(fields);
  }

  @Override
  public Object getStructFieldData(Object data, StructField fieldRef) {
    if (data == null) {
      return null;
    }
    LazyBinaryStruct struct = (LazyBinaryStruct) data;
    MyField f = (MyField) fieldRef;

    int fieldID = f.getFieldID();
    assert (fieldID >= 0 && fieldID < fields.size());

    return struct.getField(fieldID);
  }

  @Override
  public List<Object> getStructFieldsDataAsList(Object data) {
    if (data == null) {
      return null;
    }
    LazyBinaryStruct struct = (LazyBinaryStruct) data;
    return struct.getFieldsAsList();
  }
}
