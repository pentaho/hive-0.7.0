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
import org.apache.hadoop.hive.serde2.objectinspector.primitive.IntObjectInspector;
import org.apache.thrift.TException;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.protocol.TType;

/**
 * DynamicSerDeTypei32.
 *
 */
public class DynamicSerDeTypei32 extends DynamicSerDeTypeBase {

  // production is: i32

  public DynamicSerDeTypei32(int i) {
    super(i);
  }

  public DynamicSerDeTypei32(thrift_grammar p, int i) {
    super(p, i);
  }

  @Override
  public String toString() {
    return "i32";
  }

  @Override
  public Object deserialize(Object reuse, TProtocol iprot)
      throws SerDeException, TException, IllegalAccessException {
    int val = iprot.readI32();
    if (val == 0
        && iprot instanceof org.apache.hadoop.hive.serde2.thrift.WriteNullsProtocol
        && ((org.apache.hadoop.hive.serde2.thrift.WriteNullsProtocol) iprot)
        .lastPrimitiveWasNull()) {
      return null;
    }
    return Integer.valueOf(val);
  }

  @Override
  public void serialize(Object o, ObjectInspector oi, TProtocol oprot)
      throws TException, SerDeException, NoSuchFieldException, IllegalAccessException {
    IntObjectInspector poi = (IntObjectInspector) oi;
    oprot.writeI32(poi.get(o));
  }

  @Override
  public Class getRealType() {
    return java.lang.Integer.class;
  }

  public Integer getRealTypeInstance() {
    return Integer.valueOf(0);
  }

  @Override
  public byte getType() {
    return TType.I32;
  }
}
