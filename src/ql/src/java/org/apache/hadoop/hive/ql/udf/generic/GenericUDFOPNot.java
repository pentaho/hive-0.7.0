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
 * GenericUDFOPNot.
 *
 */
@Description(name = "not", value = "_FUNC_ a - Logical not")
public class GenericUDFOPNot extends GenericUDF {
  private final BooleanWritable result = new BooleanWritable();
  BooleanObjectInspector boi;

  @Override
  public ObjectInspector initialize(ObjectInspector[] arguments)
      throws UDFArgumentException {
    if (arguments.length != 1) {
      throw new UDFArgumentLengthException(
          "The operator NOT only accepts 1 argument.");
    }
    boi = (BooleanObjectInspector) arguments[0];
    return PrimitiveObjectInspectorFactory.writableBooleanObjectInspector;
  }

  @Override
  public Object evaluate(DeferredObject[] arguments) throws HiveException {
    Object a0 = arguments[0].get();
    if (a0 == null) {
      return null;
    }
    result.set(!boi.get(a0));
    return result;
  }

  @Override
  public String getDisplayString(String[] children) {
    assert (children.length == 1);
    return "(not " + children[0] + ")";
  }

}
