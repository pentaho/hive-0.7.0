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
import java.util.List;

/**
 * UnionStructObjectInspector unions several struct data into a single struct.
 * Basically, the fields of these structs are put together sequentially into a
 * single struct.
 * 
 * The object that can be acceptable by this ObjectInspector is a List of
 * objects, each of which can be inspected by the ObjectInspector provided in
 * the ctor of UnionStructObjectInspector.
 * 
 * Always use the ObjectInspectorFactory to create new ObjectInspector objects,
 * instead of directly creating an instance of this class.
 */
public class UnionStructObjectInspector extends StructObjectInspector {

  /**
   * MyField.
   *
   */
  public static class MyField implements StructField {
    public int structID;
    StructField structField;

    public MyField(int structID, StructField structField) {
      this.structID = structID;
      this.structField = structField;
    }

    public String getFieldName() {
      return structField.getFieldName();
    }

    public ObjectInspector getFieldObjectInspector() {
      return structField.getFieldObjectInspector();
    }
  }

  List<StructObjectInspector> unionObjectInspectors;
  List<MyField> fields;

  protected UnionStructObjectInspector(
      List<StructObjectInspector> unionObjectInspectors) {
    init(unionObjectInspectors);
  }

  void init(List<StructObjectInspector> unionObjectInspectors) {
    this.unionObjectInspectors = unionObjectInspectors;

    int totalSize = 0;
    for (int i = 0; i < unionObjectInspectors.size(); i++) {
      totalSize += unionObjectInspectors.get(i).getAllStructFieldRefs().size();
    }

    fields = new ArrayList<MyField>(totalSize);
    for (int i = 0; i < unionObjectInspectors.size(); i++) {
      StructObjectInspector oi = unionObjectInspectors.get(i);
      for (StructField sf : oi.getAllStructFieldRefs()) {
        fields.add(new MyField(i, sf));
      }
    }
  }

  public final Category getCategory() {
    return Category.STRUCT;
  }

  public String getTypeName() {
    return ObjectInspectorUtils.getStandardStructTypeName(this);
  }

  // Without Data
  @Override
  public StructField getStructFieldRef(String fieldName) {
    return ObjectInspectorUtils.getStandardStructFieldRef(fieldName, fields);
  }

  @Override
  public List<? extends StructField> getAllStructFieldRefs() {
    return fields;
  }

  // With Data
  @Override
  @SuppressWarnings("unchecked")
  public Object getStructFieldData(Object data, StructField fieldRef) {
    if (data == null) {
      return null;
    }
    MyField f = (MyField) fieldRef;
    Object fieldData;
    // We support both List<Object> and Object[]
    // so we have to do differently.
    if (! (data instanceof List)) {
      Object[] list = (Object[]) data;
      assert (list.length == unionObjectInspectors.size());
      fieldData = list[f.structID];
    } else {
      List<Object> list = (List<Object>) data;
      assert (list.size() == unionObjectInspectors.size());
      fieldData = list.get(f.structID);
    }
    return unionObjectInspectors.get(f.structID).getStructFieldData(fieldData,
        f.structField);
  }

  @Override
  @SuppressWarnings("unchecked")
  public List<Object> getStructFieldsDataAsList(Object data) {
    if (data == null) {
      return null;
    }
    // We support both List<Object> and Object[]
    // so we have to do differently.
    if (! (data instanceof List)) {
      data = java.util.Arrays.asList((Object[]) data);
    }
    List<Object> list = (List<Object>) data;
    assert (list.size() == unionObjectInspectors.size());
    // Explode
    ArrayList<Object> result = new ArrayList<Object>(fields.size());
    for (int i = 0; i < unionObjectInspectors.size(); i++) {
      result.addAll(unionObjectInspectors.get(i).getStructFieldsDataAsList(
          list.get(i)));
    }
    return result;
  }

}
