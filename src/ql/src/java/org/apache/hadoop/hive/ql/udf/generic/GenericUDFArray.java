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

import java.util.ArrayList;

import org.apache.hadoop.hive.ql.exec.Description;
import org.apache.hadoop.hive.ql.exec.UDFArgumentException;
import org.apache.hadoop.hive.ql.exec.UDFArgumentTypeException;
import org.apache.hadoop.hive.ql.metadata.HiveException;
import org.apache.hadoop.hive.serde2.objectinspector.ObjectInspector;
import org.apache.hadoop.hive.serde2.objectinspector.ObjectInspectorConverters;
import org.apache.hadoop.hive.serde2.objectinspector.ObjectInspectorFactory;
import org.apache.hadoop.hive.serde2.objectinspector.PrimitiveObjectInspector;
import org.apache.hadoop.hive.serde2.objectinspector.ObjectInspectorConverters.Converter;
import org.apache.hadoop.hive.serde2.objectinspector.primitive.PrimitiveObjectInspectorFactory;

/**
 * GenericUDFArray.
 *
 */
@Description(name = "array",
    value = "_FUNC_(n0, n1...) - Creates an array with the given elements ")
public class GenericUDFArray extends GenericUDF {
  private Converter[] converters;
  private ArrayList<Object> ret = new ArrayList<Object>();

  @Override
  public ObjectInspector initialize(ObjectInspector[] arguments) throws UDFArgumentException {

    GenericUDFUtils.ReturnObjectInspectorResolver returnOIResolver;

    returnOIResolver = new GenericUDFUtils.ReturnObjectInspectorResolver(true);

    for (int i = 0; i < arguments.length; i++) {
      if (!returnOIResolver.update(arguments[i])) {
        throw new UDFArgumentTypeException(i, "Argument type \""
            + arguments[i].getTypeName()
            + "\" is different from preceding arguments. "
            + "Previous type was \"" + arguments[i - 1].getTypeName() + "\"");
      }
    }

    converters = new Converter[arguments.length];

    ObjectInspector returnOI = returnOIResolver.get();
    if (returnOI == null) {
      returnOI = PrimitiveObjectInspectorFactory
          .getPrimitiveJavaObjectInspector(PrimitiveObjectInspector.PrimitiveCategory.STRING);
    }
    for (int i = 0; i < arguments.length; i++) {
      converters[i] = ObjectInspectorConverters.getConverter(arguments[i],
          returnOI);
    }

    return ObjectInspectorFactory.getStandardListObjectInspector(returnOI);
  }

  @Override
  public Object evaluate(DeferredObject[] arguments) throws HiveException {
    ret.clear();
    for (int i = 0; i < arguments.length; i++) {
      ret.add(converters[i].convert(arguments[i].get()));
    }
    return ret;

  }

  @Override
  public String getDisplayString(String[] children) {
    StringBuilder sb = new StringBuilder();
    sb.append("array(");
    for (int i = 0; i < children.length; i++) {
      sb.append(children[i]);
      if (i + 1 != children.length) {
        sb.append(",");
      }
    }
    sb.append(")");
    return sb.toString();
  }
}
