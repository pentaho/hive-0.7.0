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

import org.apache.hadoop.hive.serde2.SerDeException;
import org.apache.hadoop.hive.serde2.objectinspector.ObjectInspector;
import org.apache.thrift.TException;
import org.apache.thrift.protocol.TProtocol;

/**
 * DynamicSerDeTypeBase.
 *
 */
public abstract class DynamicSerDeTypeBase extends DynamicSerDeSimpleNode
    implements Serializable {
  private static final long serialVersionUID = 1L;

  public DynamicSerDeTypeBase(int i) {
    super(i);
  }

  public DynamicSerDeTypeBase(thrift_grammar p, int i) {
    super(p, i);
  }

  public void initialize() {
    // for base type, do nothing. Other types, like structs may initialize
    // internal data
    // structures.
  }

  public Class getRealType() throws SerDeException {
    throw new SerDeException("Not implemented in base");
  }

  public Object get(Object obj) {
    throw new RuntimeException("Not implemented in base");
  }

  public abstract Object deserialize(Object reuse, TProtocol iprot)
      throws SerDeException, TException, IllegalAccessException;

  public abstract void serialize(Object o, ObjectInspector oi, TProtocol oprot)
      throws TException, SerDeException, NoSuchFieldException, IllegalAccessException;

  @Override
  public String toString() {
    return "BAD";
  }

  public byte getType() {
    return -1;
  }

  public boolean isPrimitive() {
    return true;
  }

  public boolean isList() {
    return false;
  }

  public boolean isMap() {
    return false;
  }

}
