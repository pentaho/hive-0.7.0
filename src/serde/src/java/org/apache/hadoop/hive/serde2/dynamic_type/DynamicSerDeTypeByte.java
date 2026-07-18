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
import org.apache.hadoop.hive.serde2.objectinspector.primitive.ByteObjectInspector;
import org.apache.thrift.TException;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.protocol.TType;

/**
 * DynamicSerDeTypeByte.
 *
 */
public class DynamicSerDeTypeByte extends DynamicSerDeTypeBase {

  // production is: byte

  public DynamicSerDeTypeByte(int i) {
    super(i);
  }

  public DynamicSerDeTypeByte(thrift_grammar p, int i) {
    super(p, i);
  }

  @Override
  public String toString() {
    return "byte";
  }

  public Byte deserialize(TProtocol iprot) throws SerDeException, TException,
      IllegalAccessException {
    byte val = iprot.readByte();
    if (val == 0
        && iprot instanceof org.apache.hadoop.hive.serde2.thrift.WriteNullsProtocol
        && ((org.apache.hadoop.hive.serde2.thrift.WriteNullsProtocol) iprot)
        .lastPrimitiveWasNull()) {
      return null;
    }
    return Byte.valueOf(val);
  }

  @Override
  public Object deserialize(Object reuse, TProtocol iprot)
      throws SerDeException, TException, IllegalAccessException {
    return deserialize(iprot);
  }

  @Override
  public void serialize(Object o, ObjectInspector oi, TProtocol oprot)
      throws TException, SerDeException, NoSuchFieldException, IllegalAccessException {
    ByteObjectInspector poi = (ByteObjectInspector) oi;
    oprot.writeByte(poi.get(o));
  }

  @Override
  public byte getType() {
    return TType.BYTE;
  }
}
