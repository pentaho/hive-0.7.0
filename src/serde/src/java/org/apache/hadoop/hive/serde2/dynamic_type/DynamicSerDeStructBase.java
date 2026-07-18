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

import java.io.Serializable;
import java.util.List;

import org.apache.hadoop.hive.serde2.SerDeException;
import org.apache.hadoop.hive.serde2.objectinspector.ObjectInspector;
import org.apache.thrift.TException;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.protocol.TStruct;

/**
 * DynamicSerDeStructBase.
 *
 */
public abstract class DynamicSerDeStructBase extends DynamicSerDeTypeBase
    implements Serializable {

  DynamicSerDeFieldList fieldList;

  public DynamicSerDeStructBase(int i) {
    super(i);
  }

  public DynamicSerDeStructBase(thrift_grammar p, int i) {
    super(p, i);
  }

  public abstract DynamicSerDeFieldList getFieldList();

  @Override
  public void initialize() {
    fieldList = getFieldList();
    fieldList.initialize();
  }

  @Override
  public boolean isPrimitive() {
    return false;
  }

  @Override
  public Class getRealType() {
    return List.class;
  }

  @Override
  public Object deserialize(Object reuse, TProtocol iprot)
      throws SerDeException, TException, IllegalAccessException {
    if (thrift_mode) {
      iprot.readStructBegin();
    }

    Object o = fieldList.deserialize(reuse, iprot);

    if (thrift_mode) {
      iprot.readStructEnd();
    }
    return o;
  }

  /**
   * serialize
   * 
   * The way to serialize a Thrift "table" which in thrift land is really a
   * function and thus this class's name.
   * 
   * @param o
   *          - this list should be in the order of the function's params for
   *          now. If we wanted to remove this requirement, we'd need to make it
   *          a List<Pair<String, Object>> with the String being the field name.
   * 
   */
  @Override
  public void serialize(Object o, ObjectInspector oi, TProtocol oprot)
      throws TException, SerDeException, NoSuchFieldException, IllegalAccessException {
    if (thrift_mode) {
      oprot.writeStructBegin(new TStruct(name));
    }

    fieldList.serialize(o, oi, oprot);

    if (thrift_mode) {
      oprot.writeStructEnd();
    }
  }

}
