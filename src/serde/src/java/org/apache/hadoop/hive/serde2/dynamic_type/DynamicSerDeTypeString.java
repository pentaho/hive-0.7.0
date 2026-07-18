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


package org.apache.hadoop.hive.serde2.dynamic_type;

import org.apache.hadoop.hive.serde2.SerDeException;
import org.apache.hadoop.hive.serde2.objectinspector.ObjectInspector;
import org.apache.hadoop.hive.serde2.objectinspector.primitive.StringObjectInspector;
import org.apache.hadoop.hive.serde2.thrift.WriteTextProtocol;
import org.apache.thrift.TException;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.protocol.TType;

/**
 * DynamicSerDeTypeString.
 *
 */
public class DynamicSerDeTypeString extends DynamicSerDeTypeBase {

  // production is: string

  public DynamicSerDeTypeString(int i) {
    super(i);
  }

  public DynamicSerDeTypeString(thrift_grammar p, int i) {
    super(p, i);
  }

  @Override
  public Class getRealType() {
    return java.lang.String.class;
  }

  @Override
  public String toString() {
    return "string";
  }

  public String deserialize(TProtocol iprot) throws SerDeException, TException,
      IllegalAccessException {
    return iprot.readString();
  }

  @Override
  public Object deserialize(Object reuse, TProtocol iprot)
      throws SerDeException, TException, IllegalAccessException {
    return iprot.readString();
  }

  @Override
  public void serialize(Object o, ObjectInspector oi, TProtocol oprot)
      throws TException, SerDeException, NoSuchFieldException, IllegalAccessException {
    StringObjectInspector poi = (StringObjectInspector) oi;
    if (oprot instanceof WriteTextProtocol) {
      ((WriteTextProtocol) oprot).writeText(poi.getPrimitiveWritableObject(o));
    } else {
      oprot.writeString(poi.getPrimitiveJavaObject(o));
    }
  }

  @Override
  public byte getType() {
    return TType.STRING;
  }
}
