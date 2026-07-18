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
import org.apache.hadoop.hive.ql.exec.UDFArgumentException;
import org.apache.hadoop.hive.ql.exec.UDFArgumentTypeException;
import org.apache.hadoop.hive.ql.metadata.HiveException;
import org.apache.hadoop.hive.serde2.objectinspector.ObjectInspector;
import org.apache.hadoop.hive.serde2.objectinspector.ObjectInspectorUtils;
import org.apache.hadoop.hive.serde2.objectinspector.PrimitiveObjectInspector;
import org.apache.hadoop.hive.serde2.objectinspector.ObjectInspector.Category;
import org.apache.hadoop.hive.serde2.objectinspector.primitive.PrimitiveObjectInspectorFactory;
import org.apache.hadoop.io.IntWritable;

/**
 * GenericUDFField.
 * 
 */
@Description(name = "field", value = "_FUNC_(str, str1, str2, ...) - "
    + "returns the index of str in the str1,str2,... list or 0 if not found", extended = "All primitive types are supported, arguments are compared using str.equals(x)."
    + " If str is NULL, the return value is 0.")
public class GenericUDFField extends GenericUDF {
  private ObjectInspector[] argumentOIs;

  @Override
  public ObjectInspector initialize(ObjectInspector[] arguments)
      throws UDFArgumentException {
    if (arguments.length < 2) {
      throw new UDFArgumentException(
          "The function FIELD(str, str1, str2, ...) needs at least two arguments.");
    }

    argumentOIs = arguments;

    for (int i = 0; i < arguments.length; i++) {
      Category category = arguments[i].getCategory();
      if (category != Category.PRIMITIVE) {
        throw new UDFArgumentTypeException(i, "The "
            + GenericUDFUtils.getOrdinal(i + 1)
            + " argument of function FIELD is expected to a "
            + Category.PRIMITIVE.toString().toLowerCase() + " type, but "
            + category.toString().toLowerCase() + " is found");
      }
    }

    return PrimitiveObjectInspectorFactory.writableIntObjectInspector;
  }

  private final IntWritable r = new IntWritable();

  @Override
  public Object evaluate(DeferredObject[] arguments) throws HiveException {
    if (arguments[0].get() == null) {
      r.set(0);
      return r;
    }

    for (int i = 1; i < arguments.length; i++) {
      if (arguments[i].get() == null) {
        continue;
      }
      if (ObjectInspectorUtils.compare(arguments[0].get(), argumentOIs[0],
          arguments[i].get(), argumentOIs[i]) == 0) {
        r.set(i);
        return r;
      }
    }

    r.set(0);
    return r;
  }

  @Override
  public String getDisplayString(String[] children) {
    assert (children.length >= 2);

    final StringBuilder sb = new StringBuilder();
    sb.append("field(");
    sb.append(StringUtils.join(children, ", "));
    sb.append(")");

    return sb.toString();
  }
}
