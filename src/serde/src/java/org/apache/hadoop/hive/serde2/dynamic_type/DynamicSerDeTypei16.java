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
import org.apache.hadoop.hive.serde2.objectinspector.primitive.ShortObjectInspector;
import org.apache.thrift.TException;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.protocol.TType;

/**
 * DynamicSerDeTypei16.
 *
 */
public class DynamicSerDeTypei16 extends DynamicSerDeTypeBase {

  @Override
  public Class getRealType() {
    return Integer.valueOf(2).getClass();
  }

  // production is: i16

  public DynamicSerDeTypei16(int i) {
    super(i);
  }

  public DynamicSerDeTypei16(thrift_grammar p, int i) {
    super(p, i);
  }

  @Override
  public String toString() {
    return "i16";
  }

  @Override
  public Object deserialize(Object reuse, TProtocol iprot)
      throws SerDeException, TException, IllegalAccessException {
    int val = iprot.readI16();
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
    ShortObjectInspector poi = (ShortObjectInspector) oi;
    oprot.writeI16(poi.get(o));
  }

  @Override
  public byte getType() {
    return TType.I16;
  }
}
