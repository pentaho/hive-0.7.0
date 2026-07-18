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
import org.apache.hadoop.hive.ql.exec.UDFArgumentTypeException;
import org.apache.hadoop.hive.ql.metadata.HiveException;
import org.apache.hadoop.hive.serde2.objectinspector.ListObjectInspector;
import org.apache.hadoop.hive.serde2.objectinspector.MapObjectInspector;
import org.apache.hadoop.hive.serde2.objectinspector.ObjectInspector;
import org.apache.hadoop.hive.serde2.objectinspector.PrimitiveObjectInspector;
import org.apache.hadoop.hive.serde2.objectinspector.ObjectInspector.Category;
import org.apache.hadoop.hive.serde2.objectinspector.primitive.PrimitiveObjectInspectorUtils;

/**
 * GenericUDFIndex.
 *
 */
@Description(name = "index", value = "_FUNC_(a, n) - Returns the n-th element of a ")
public class GenericUDFIndex extends GenericUDF {
  private MapObjectInspector mapOI;
  private boolean mapKeyPreferWritable;
  private ListObjectInspector listOI;
  private PrimitiveObjectInspector indexOI;
  private ObjectInspector returnOI;

  @Override
  public ObjectInspector initialize(ObjectInspector[] arguments) throws UDFArgumentException {
    if (arguments.length != 2) {
      throw new UDFArgumentLengthException(
          "The function INDEX accepts exactly 2 arguments.");
    }

    if (arguments[0] instanceof MapObjectInspector) {
      // index into a map
      mapOI = (MapObjectInspector) arguments[0];
      listOI = null;
    } else if (arguments[0] instanceof ListObjectInspector) {
      // index into a list
      listOI = (ListObjectInspector) arguments[0];
      mapOI = null;
    } else {
      throw new UDFArgumentTypeException(0, "\""
          + Category.MAP.toString().toLowerCase() + "\" or \""
          + Category.LIST.toString().toLowerCase()
          + "\" is expected at function INDEX, but \""
          + arguments[0].getTypeName() + "\" is found");
    }

    // index has to be a primitive
    if (arguments[1] instanceof PrimitiveObjectInspector) {
      indexOI = (PrimitiveObjectInspector) arguments[1];
    } else {
      throw new UDFArgumentTypeException(1, "Primitive Type is expected but "
          + arguments[1].getTypeName() + "\" is found");
    }

    if (mapOI != null) {
      returnOI = mapOI.getMapValueObjectInspector();
      ObjectInspector keyOI = mapOI.getMapKeyObjectInspector();
      mapKeyPreferWritable = ((PrimitiveObjectInspector) keyOI)
          .preferWritable();
    } else {
      returnOI = listOI.getListElementObjectInspector();
    }

    return returnOI;
  }

  @Override
  public Object evaluate(DeferredObject[] arguments) throws HiveException {
    assert (arguments.length == 2);
    Object main = arguments[0].get();
    Object index = arguments[1].get();

    if (mapOI != null) {

      Object indexObject;
      if (mapKeyPreferWritable) {
        indexObject = indexOI.getPrimitiveWritableObject(index);
      } else {
        indexObject = indexOI.getPrimitiveJavaObject(index);
      }
      return mapOI.getMapValueElement(main, indexObject);

    } else {

      assert (listOI != null);
      int intIndex = 0;
      try {
        intIndex = PrimitiveObjectInspectorUtils.getInt(index, indexOI);
      } catch (NullPointerException e) {
        // If index is null, we should return null.
        return null;
      } catch (NumberFormatException e) {
        // If index is not a number, we should return null.
        return null;
      }
      return listOI.getListElement(main, intIndex);

    }
  }

  @Override
  public String getDisplayString(String[] children) {
    assert (children.length == 2);
    return children[0] + "[" + children[1] + "]";
  }
}
