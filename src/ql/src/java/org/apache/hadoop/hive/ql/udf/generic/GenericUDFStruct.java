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
import java.util.Arrays;

import org.apache.hadoop.hive.ql.exec.UDFArgumentException;
import org.apache.hadoop.hive.ql.exec.Description;
import org.apache.hadoop.hive.ql.metadata.HiveException;
import org.apache.hadoop.hive.serde2.objectinspector.ObjectInspector;
import org.apache.hadoop.hive.serde2.objectinspector.ObjectInspectorFactory;
import org.apache.hadoop.hive.serde2.objectinspector.StructObjectInspector;

@Description(name = "struct",
    value = "_FUNC_(col1, col2, col3, ...) - Creates a struct with the given field values")
public class GenericUDFStruct extends GenericUDF {
  Object[] ret;

  @Override
  public ObjectInspector initialize(ObjectInspector[] arguments)
      throws UDFArgumentException {
    
    int numFields = arguments.length;
    ret = new Object[numFields];
    
    ArrayList<String> fname = new ArrayList<String>(numFields);
    for (int f = 1; f <= numFields; f++) {
      fname.add("col" + f);
    }
    StructObjectInspector soi = 
      ObjectInspectorFactory.getStandardStructObjectInspector(fname, Arrays.asList(arguments));
    return soi;
  }

  @Override
  public Object evaluate(DeferredObject[] arguments) throws HiveException {
    for (int i = 0; i < arguments.length; i++) {
      ret[i] = arguments[i].get();
    }
    return ret;
  }

  @Override
  public String getDisplayString(String[] children) {
    StringBuilder sb = new StringBuilder();
    sb.append("struct(");
    for (int i = 0; i < children.length; i++) {
      if (i > 0) {
        sb.append(',');
      }
      sb.append(children[i]);
    }
    sb.append(')');
    return sb.toString();
  }
}
