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

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.hadoop.hive.serde2.columnar.ColumnarStruct;
import org.apache.hadoop.io.Text;

/**
 * ColumnarStructObjectInspector works on struct data that is stored in
 * ColumnarStruct.
 *
 * The names of the struct fields and the internal structure of the struct
 * fields are specified in the ctor of the ColumnarStructObjectInspector.
 *
 * Always use the ObjectInspectorFactory to create new ObjectInspector objects,
 * instead of directly creating an instance of this class.
 */
class ColumnarStructObjectInspector extends StructObjectInspector {

  public static final Log LOG = LogFactory
      .getLog(ColumnarStructObjectInspector.class.getName());

  protected static class MyField implements StructField {
    protected int fieldID;
    protected String fieldName;
    protected ObjectInspector fieldObjectInspector;

    public MyField(int fieldID, String fieldName,
        ObjectInspector fieldObjectInspector) {
      this.fieldID = fieldID;
      this.fieldName = fieldName.toLowerCase();
      this.fieldObjectInspector = fieldObjectInspector;
    }

    public int getFieldID() {
      return fieldID;
    }

    public String getFieldName() {
      return fieldName;
    }

    public ObjectInspector getFieldObjectInspector() {
      return fieldObjectInspector;
    }

    @Override
    public String toString() {
      return "" + fieldID + ":" + fieldName;
    }
  }

  protected List<MyField> fields;

  @Override
  public String getTypeName() {
    return ObjectInspectorUtils.getStandardStructTypeName(this);
  }

  Text nullSequence;

  /**
   * Call ObjectInspectorFactory.getLazySimpleStructObjectInspector instead.
   */
  public ColumnarStructObjectInspector(List<String> structFieldNames,
      List<ObjectInspector> structFieldObjectInspectors, Text nullSequence) {
    init(structFieldNames, structFieldObjectInspectors, nullSequence);
  }

  protected void init(List<String> structFieldNames,
      List<ObjectInspector> structFieldObjectInspectors, Text nullSequence) {
    assert (structFieldNames.size() == structFieldObjectInspectors.size());

    this.nullSequence = nullSequence;

    fields = new ArrayList<MyField>(structFieldNames.size());
    for (int i = 0; i < structFieldNames.size(); i++) {
      fields.add(new MyField(i, structFieldNames.get(i),
          structFieldObjectInspectors.get(i)));
    }
  }

  protected ColumnarStructObjectInspector(List<StructField> fields,
      Text nullSequence) {
    init(fields, nullSequence);
  }

  protected void init(List<StructField> fields, Text nullSequence) {
    this.nullSequence = nullSequence;

    this.fields = new ArrayList<MyField>(fields.size());
    for (int i = 0; i < fields.size(); i++) {
      this.fields.add(new MyField(i, fields.get(i).getFieldName(), fields
          .get(i).getFieldObjectInspector()));
    }
  }

  @Override
  public final Category getCategory() {
    return Category.STRUCT;
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
  public Object getStructFieldData(Object data, StructField fieldRef) {
    if (data == null) {
      return null;
    }
    ColumnarStruct struct = (ColumnarStruct) data;
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
    ColumnarStruct struct = (ColumnarStruct) data;
    return struct.getFieldsAsList();
  }
}
