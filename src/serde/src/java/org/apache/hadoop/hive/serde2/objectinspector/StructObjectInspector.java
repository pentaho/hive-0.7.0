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

import java.util.List;

/**
 * StructObjectInspector.
 *
 */
public abstract class StructObjectInspector implements ObjectInspector {

  // ** Methods that does not need a data object **
  /**
   * Returns all the fields.
   */
  public abstract List<? extends StructField> getAllStructFieldRefs();

  /**
   * Look up a field.
   */
  public abstract StructField getStructFieldRef(String fieldName);

  // ** Methods that need a data object **
  /**
   * returns null for data = null.
   */
  public abstract Object getStructFieldData(Object data, StructField fieldRef);

  /**
   * returns null for data = null.
   */
  public abstract List<Object> getStructFieldsDataAsList(Object data);

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    List<? extends StructField> fields = getAllStructFieldRefs();
    sb.append(getClass().getName());
    sb.append("<");
    for (int i = 0; i < fields.size(); i++) {
      if (i > 0) {
        sb.append(",");
      }
      sb.append(fields.get(i).getFieldObjectInspector().toString());
    }
    sb.append(">");
    return sb.toString();
  }
}
