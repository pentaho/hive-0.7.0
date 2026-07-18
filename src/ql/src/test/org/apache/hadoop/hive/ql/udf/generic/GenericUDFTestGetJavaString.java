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

import org.apache.hadoop.hive.ql.exec.UDFArgumentException;
import org.apache.hadoop.hive.ql.metadata.HiveException;
import org.apache.hadoop.hive.serde2.objectinspector.ObjectInspector;
import org.apache.hadoop.hive.serde2.objectinspector.primitive.PrimitiveObjectInspectorFactory;
import org.apache.hadoop.hive.serde2.objectinspector.primitive.StringObjectInspector;

/**
 * A test GenericUDF to return native Java's string type
 */
public class GenericUDFTestGetJavaString extends GenericUDF {
  ObjectInspector[] argumentOIs;

  @Override
  public ObjectInspector initialize(ObjectInspector[] arguments) throws UDFArgumentException {
    argumentOIs = arguments;
    return PrimitiveObjectInspectorFactory.javaStringObjectInspector;
  }

  @Override
  public Object evaluate(DeferredObject[] arguments) throws HiveException {
    if (arguments[0].get() == null) {
      return null;
    }
    return ((StringObjectInspector) argumentOIs[0]).getPrimitiveJavaObject(arguments[0].get());
  }

  @Override
  public String getDisplayString(String[] children) {
    assert (children.length == 1);
    return "GenericUDFTestGetJavaString(" + children[0] + ")";
  }
}
