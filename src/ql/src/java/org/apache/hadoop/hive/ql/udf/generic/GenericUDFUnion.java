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

import java.util.Arrays;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.hadoop.hive.ql.exec.Description;
import org.apache.hadoop.hive.ql.exec.UDFArgumentException;
import org.apache.hadoop.hive.ql.metadata.HiveException;
import org.apache.hadoop.hive.serde2.objectinspector.ObjectInspector;
import org.apache.hadoop.hive.serde2.objectinspector.ObjectInspectorFactory;
import org.apache.hadoop.hive.serde2.objectinspector.StandardUnionObjectInspector.StandardUnion;
import org.apache.hadoop.hive.serde2.objectinspector.primitive.IntObjectInspector;

@Description(name = "create_union", value = "_FUNC_(tag, obj1, obj2, obj3, ...)"
   + " - Creates a union with the object for given tag",
   extended = "Example:\n"
   + "  > SELECT _FUNC_(1, 1, \"one\") FROM src LIMIT 1;\n" + "  one")
public class GenericUDFUnion extends GenericUDF {
  Log LOG = LogFactory.getLog("GenericUDFUnion");
  ObjectInspector tagOI;

  @Override
  public ObjectInspector initialize(ObjectInspector[] arguments)
      throws UDFArgumentException {
    tagOI = arguments[0];
    ObjectInspector[] unionOIs = new ObjectInspector[arguments.length-1];
    for (int i = 1; i < arguments.length; i++) {
      unionOIs[i-1] = arguments[i];
    }
    return ObjectInspectorFactory.getStandardUnionObjectInspector(
        Arrays.asList(unionOIs));
  }

  @Override
  public Object evaluate(DeferredObject[] arguments) throws HiveException {
    byte tag = (byte)((IntObjectInspector)tagOI).get(arguments[0].get());
    return new StandardUnion(tag, arguments[tag + 1].get());
  }

  @Override
  public String getDisplayString(String[] children) {
    StringBuilder sb = new StringBuilder();
    sb.append("create_union(");
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
