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

import org.apache.commons.lang.StringUtils;
import org.apache.hadoop.hive.ql.exec.Description;
import org.apache.hadoop.hive.ql.exec.UDFArgumentTypeException;
import org.apache.hadoop.hive.ql.metadata.HiveException;
import org.apache.hadoop.hive.serde2.objectinspector.ObjectInspector;
import org.apache.hadoop.hive.serde2.objectinspector.ObjectInspectorUtils;
import org.apache.hadoop.hive.serde2.objectinspector.primitive.PrimitiveObjectInspectorFactory;
import org.apache.hadoop.io.IntWritable;

/**
 * GenericUDF Class for computing hash values.
 */
@Description(name = "hash", value = "_FUNC_(a1, a2, ...) - Returns a hash value of the arguments")
public class GenericUDFHash extends GenericUDF {
  private ObjectInspector[] argumentOIs;

  @Override
  public ObjectInspector initialize(ObjectInspector[] arguments) throws UDFArgumentTypeException {

    argumentOIs = arguments;
    return PrimitiveObjectInspectorFactory.writableIntObjectInspector;
  }

  private IntWritable result = new IntWritable();

  @Override
  public Object evaluate(DeferredObject[] arguments) throws HiveException {
    // See
    // http://java.sun.com/j2se/1.5.0/docs/api/java/util/List.html#hashCode()
    int r = 0;
    for (int i = 0; i < arguments.length; i++) {
      r = r * 31
          + ObjectInspectorUtils.hashCode(arguments[i].get(), argumentOIs[i]);
    }
    result.set(r);
    return result;
  }

  @Override
  public String getDisplayString(String[] children) {
    return "hash(" + StringUtils.join(children, ',') + ")";
  }

}
