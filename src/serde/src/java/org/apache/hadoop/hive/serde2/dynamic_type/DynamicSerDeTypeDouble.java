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
import org.apache.hadoop.hive.serde2.objectinspector.primitive.DoubleObjectInspector;
import org.apache.thrift.TException;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.protocol.TType;

/**
 * DynamicSerDeTypeDouble.
 *
 */
public class DynamicSerDeTypeDouble extends DynamicSerDeTypeBase {

  // production is: double

  public DynamicSerDeTypeDouble(int i) {
    super(i);
  }

  public DynamicSerDeTypeDouble(thrift_grammar p, int i) {
    super(p, i);
  }

  @Override
  public String toString() {
    return "double";
  }

  @Override
  public Object deserialize(Object reuse, TProtocol iprot)
      throws SerDeException, TException, IllegalAccessException {
    double val = iprot.readDouble();
    if (val == 0
        && iprot instanceof org.apache.hadoop.hive.serde2.thrift.WriteNullsProtocol
        && ((org.apache.hadoop.hive.serde2.thrift.WriteNullsProtocol) iprot)
        .lastPrimitiveWasNull()) {
      return null;
    }
    return Double.valueOf(val);
  }

  @Override
  public void serialize(Object o, ObjectInspector oi, TProtocol oprot)
      throws TException, SerDeException, NoSuchFieldException, IllegalAccessException {
    DoubleObjectInspector poi = (DoubleObjectInspector) oi;
    oprot.writeDouble(poi.get(o));
  }

  @Override
  public byte getType() {
    return TType.DOUBLE;
  }

  @Override
  public Class getRealType() {
    return java.lang.Double.class;
  }

  public Double getRealTypeInstance() {
    return Double.valueOf(0);
  }
}
