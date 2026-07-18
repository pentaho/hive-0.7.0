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

import java.util.HashMap;

import org.apache.hadoop.hive.ql.exec.Description;
import org.apache.hadoop.hive.ql.exec.UDFArgumentException;
import org.apache.hadoop.hive.ql.exec.UDFArgumentLengthException;
import org.apache.hadoop.hive.ql.exec.UDFArgumentTypeException;
import org.apache.hadoop.hive.ql.metadata.HiveException;
import org.apache.hadoop.hive.serde2.objectinspector.ObjectInspector;
import org.apache.hadoop.hive.serde2.objectinspector.ObjectInspectorConverters;
import org.apache.hadoop.hive.serde2.objectinspector.ObjectInspectorFactory;
import org.apache.hadoop.hive.serde2.objectinspector.PrimitiveObjectInspector;
import org.apache.hadoop.hive.serde2.objectinspector.ObjectInspectorConverters.Converter;
import org.apache.hadoop.hive.serde2.objectinspector.primitive.PrimitiveObjectInspectorFactory;

/**
 * GenericUDFMap.
 *
 */
@Description(name = "map", value = "_FUNC_(key0, value0, key1, value1...) - "
    + "Creates a map with the given key/value pairs ")
public class GenericUDFMap extends GenericUDF {
  Converter[] converters;
  HashMap<Object, Object> ret = new HashMap<Object, Object>();

  @Override
  public ObjectInspector initialize(ObjectInspector[] arguments) throws UDFArgumentException {

    if (arguments.length % 2 != 0) {
      throw new UDFArgumentLengthException(
          "Arguments must be in key/value pairs");
    }

    GenericUDFUtils.ReturnObjectInspectorResolver keyOIResolver =
        new GenericUDFUtils.ReturnObjectInspectorResolver(true);
    GenericUDFUtils.ReturnObjectInspectorResolver valueOIResolver =
        new GenericUDFUtils.ReturnObjectInspectorResolver(true);

    for (int i = 0; i < arguments.length; i++) {
      if (i % 2 == 0) {
        // Keys
        if (!(arguments[i] instanceof PrimitiveObjectInspector)) {
          throw new UDFArgumentTypeException(1,
              "Primitive Type is expected but " + arguments[i].getTypeName()
              + "\" is found");
        }
        if (!keyOIResolver.update(arguments[i])) {
          throw new UDFArgumentTypeException(i, "Key type \""
              + arguments[i].getTypeName()
              + "\" is different from preceding key types. "
              + "Previous key type was \"" + arguments[i - 2].getTypeName()
              + "\"");
        }
      } else {
        // Values
        if (!valueOIResolver.update(arguments[i])) {
          throw new UDFArgumentTypeException(i, "Value type \""
              + arguments[i].getTypeName()
              + "\" is different from preceding value types. "
              + "Previous value type was \"" + arguments[i - 2].getTypeName()
              + "\"");
        }
      }
    }

    ObjectInspector keyOI = keyOIResolver.get();
    ObjectInspector valueOI = valueOIResolver.get();

    if (keyOI == null) {
      keyOI = PrimitiveObjectInspectorFactory
          .getPrimitiveJavaObjectInspector(PrimitiveObjectInspector.PrimitiveCategory.STRING);
    }
    if (valueOI == null) {
      valueOI = PrimitiveObjectInspectorFactory
          .getPrimitiveJavaObjectInspector(PrimitiveObjectInspector.PrimitiveCategory.STRING);
    }

    converters = new Converter[arguments.length];

    for (int i = 0; i < arguments.length; i++) {
      converters[i] = ObjectInspectorConverters.getConverter(arguments[i],
          i % 2 == 0 ? keyOI : valueOI);
    }

    return ObjectInspectorFactory.getStandardMapObjectInspector(keyOI, valueOI);
  }

  @Override
  public Object evaluate(DeferredObject[] arguments) throws HiveException {
    ret.clear();
    for (int i = 0; i < arguments.length; i += 2) {
      ret.put(converters[i].convert(arguments[i].get()), converters[i + 1]
          .convert(arguments[i + 1].get()));
    }
    return ret;
  }

  @Override
  public String getDisplayString(String[] children) {
    StringBuilder sb = new StringBuilder();
    sb.append("map(");
    assert (children.length % 2 == 0);
    for (int i = 0; i < children.length; i += 2) {
      sb.append(children[i]);
      sb.append(":");
      sb.append(children[i + 1]);
      if (i + 2 != children.length) {
        sb.append(",");
      }
    }
    sb.append(")");
    return sb.toString();
  }
}
