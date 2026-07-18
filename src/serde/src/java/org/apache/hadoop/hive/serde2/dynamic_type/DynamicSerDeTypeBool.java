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
import org.apache.hadoop.hive.serde2.objectinspector.primitive.BooleanObjectInspector;
import org.apache.thrift.TException;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.protocol.TType;

/**
 * DynamicSerDeTypeBool.
 *
 */
public class DynamicSerDeTypeBool extends DynamicSerDeTypeBase {

  // production is: bool

  public DynamicSerDeTypeBool(int i) {
    super(i);
  }

  public DynamicSerDeTypeBool(thrift_grammar p, int i) {
    super(p, i);
  }

  @Override
  public String toString() {
    return "bool";
  }

  @Override
  public Object deserialize(Object reuse, TProtocol iprot)
      throws SerDeException, TException, IllegalAccessException {
    boolean val = iprot.readBool();
    if (val == false
        && iprot instanceof org.apache.hadoop.hive.serde2.thrift.WriteNullsProtocol
        && ((org.apache.hadoop.hive.serde2.thrift.WriteNullsProtocol) iprot)
        .lastPrimitiveWasNull()) {
      return null;
    }
    return Boolean.valueOf(val);
  }

  @Override
  public void serialize(Object o, ObjectInspector oi, TProtocol oprot)
      throws TException, SerDeException, NoSuchFieldException, IllegalAccessException {
    BooleanObjectInspector poi = (BooleanObjectInspector) oi;
    oprot.writeBool(poi.get(o));
  }

  @Override
  public byte getType() {
    return TType.BOOL;
  }

  @Override
  public Class getRealType() {
    return java.lang.Boolean.class;
  }

  public Boolean getRealTypeInstance() {
    return Boolean.FALSE;
  }

}
