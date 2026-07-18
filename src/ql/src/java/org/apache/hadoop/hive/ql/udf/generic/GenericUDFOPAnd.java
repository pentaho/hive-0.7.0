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
import org.apache.hadoop.hive.ql.exec.UDFArgumentException;
import org.apache.hadoop.hive.ql.exec.UDFArgumentLengthException;
import org.apache.hadoop.hive.ql.metadata.HiveException;
import org.apache.hadoop.hive.serde2.objectinspector.ObjectInspector;
import org.apache.hadoop.hive.serde2.objectinspector.primitive.BooleanObjectInspector;
import org.apache.hadoop.hive.serde2.objectinspector.primitive.PrimitiveObjectInspectorFactory;
import org.apache.hadoop.io.BooleanWritable;

/**
 * GenericUDF Class for computing and.
 */
@Description(name = "and", value = "a _FUNC_ b - Logical and")
public class GenericUDFOPAnd extends GenericUDF {
  private final BooleanWritable result = new BooleanWritable();
  BooleanObjectInspector boi0,boi1;
  @Override
  public ObjectInspector initialize(ObjectInspector[] arguments)
      throws UDFArgumentException {
    if (arguments.length != 2) {
      throw new UDFArgumentLengthException(
          "The operator 'AND' only accepts 2 argument.");
    }
    boi0 = (BooleanObjectInspector) arguments[0];
    boi1 = (BooleanObjectInspector) arguments[1];
    return PrimitiveObjectInspectorFactory.writableBooleanObjectInspector;
  }

  @Override
  public Object evaluate(DeferredObject[] arguments) throws HiveException {
    boolean bool_a0 = false, bool_a1 = false;
    Object a0 = arguments[0].get();
    if (a0 != null) {
      bool_a0 = boi0.get(a0);
      if (bool_a0 == false) {
        result.set(false);
        return result;
      }
    }

    Object a1 = arguments[1].get();
    if (a1 != null) {
      bool_a1 = boi1.get(a1);
      if (bool_a1 == false) {
        result.set(false);
        return result;
      }
    }

    if ((a0 != null && bool_a0 == true) && (a1 != null && bool_a1 == true)) {
      result.set(true);
      return result;
    }

    return null;
  }

  @Override
  public String getDisplayString(String[] children) {
    assert (children.length == 2);
    return "(" + children[0] + " and " + children[1] + ")";
  }

}
