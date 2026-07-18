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
import org.apache.hadoop.hive.serde2.objectinspector.primitive.LongObjectInspector;
import org.apache.thrift.TException;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.protocol.TType;

/**
 * DynamicSerDeTypei64.
 *
 */
public class DynamicSerDeTypei64 extends DynamicSerDeTypeBase {

  @Override
  public Class getRealType() {
    return Long.valueOf(0).getClass();
  }

  // production is: i64

  public DynamicSerDeTypei64(int i) {
    super(i);
  }

  public DynamicSerDeTypei64(thrift_grammar p, int i) {
    super(p, i);
  }

  @Override
  public String toString() {
    return "i64";
  }

  @Override
  public Object deserialize(Object reuse, TProtocol iprot)
      throws SerDeException, TException, IllegalAccessException {
    long val = iprot.readI64();
    if (val == 0
        && iprot instanceof org.apache.hadoop.hive.serde2.thrift.WriteNullsProtocol
        && ((org.apache.hadoop.hive.serde2.thrift.WriteNullsProtocol) iprot)
        .lastPrimitiveWasNull()) {
      return null;
    }
    return Long.valueOf(val);
  }

  @Override
  public void serialize(Object o, ObjectInspector oi, TProtocol oprot)
      throws TException, SerDeException, NoSuchFieldException, IllegalAccessException {
    LongObjectInspector poi = (LongObjectInspector) oi;
    oprot.writeI64(poi.get(o));
  }

  @Override
  public byte getType() {
    return TType.I64;
  }
}
