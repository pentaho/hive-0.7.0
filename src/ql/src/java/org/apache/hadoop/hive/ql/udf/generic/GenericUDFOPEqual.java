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

import org.apache.hadoop.hive.ql.exec.Description;
import org.apache.hadoop.hive.ql.metadata.HiveException;
import org.apache.hadoop.hive.serde2.objectinspector.ObjectInspectorUtils;

/**
 * GenericUDF Class for operation EQUAL.
 */
@Description(name = "=", value = "a _FUNC_ b - Returns TRUE if a equals b and false otherwise")
public class GenericUDFOPEqual extends GenericUDFBaseCompare {
  public GenericUDFOPEqual(){
    this.opName = "EQUAL";
    this.opDisplayName = "=";
  }

  @Override
  public Object evaluate(DeferredObject[] arguments) throws HiveException {
    Object o0,o1;
    o0 = arguments[0].get();
    if (o0 == null) {
      return null;
    }
    o1 = arguments[1].get();
    if (o1 == null) {
      return null;
    }

    switch(compareType) {
    case COMPARE_TEXT:
      result.set(soi0.getPrimitiveWritableObject(o0).equals(
          soi1.getPrimitiveWritableObject(o1)));
      break;
    case COMPARE_INT:
      result.set(ioi0.get(o0) == ioi1.get(o1));
      break;
    case COMPARE_LONG:
      result.set(loi0.get(o0) == loi1.get(o1));
      break;
    case COMPARE_BYTE:
      result.set(byoi0.get(o0) == byoi1.get(o1));
      break;
    case COMPARE_BOOL:
      result.set(boi0.get(o0) == boi1.get(o1));
      break;
    case COMPARE_STRING:
      result.set(soi0.getPrimitiveJavaObject(o0).equals(
          soi1.getPrimitiveJavaObject(o1)));
      break;
    case SAME_TYPE:
      result.set(ObjectInspectorUtils.compare(
          o0, argumentOIs[0], o1, argumentOIs[1]) == 0);
      break;
    default:
      Object converted_o0 = converter0.convert(o0);
      if (converted_o0 == null) {
        return null;
      }
      Object converted_o1 = converter1.convert(o1);
      if (converted_o1 == null) {
        return null;
      }
      result.set(ObjectInspectorUtils.compare(
          converted_o0, compareOI,
          converted_o1, compareOI) == 0);
    }
    return result;
  }

}
