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
import org.apache.thrift.TException;
import org.apache.thrift.protocol.TProtocol;

/**
 * DynamicSerDeTypedef.
 *
 */
public class DynamicSerDeTypedef extends DynamicSerDeTypeBase {

  // production is: typedef DefinitionType() this.name

  private static final int FD_DEFINITION_TYPE = 0;

  public DynamicSerDeTypedef(int i) {
    super(i);
  }

  public DynamicSerDeTypedef(thrift_grammar p, int i) {
    super(p, i);
  }

  private DynamicSerDeSimpleNode getDefinitionType() {
    return (DynamicSerDeSimpleNode) jjtGetChild(FD_DEFINITION_TYPE);
  }

  public DynamicSerDeTypeBase getMyType() {
    DynamicSerDeSimpleNode child = getDefinitionType();
    DynamicSerDeTypeBase ret = (DynamicSerDeTypeBase) child.jjtGetChild(0);
    return ret;
  }

  @Override
  public String toString() {
    String result = "typedef " + name + "(";
    result += getDefinitionType().toString();
    result += ")";
    return result;
  }

  @Override
  public byte getType() {
    throw new RuntimeException("not implemented");
  }

  @Override
  public Object deserialize(Object reuse, TProtocol iprot)
      throws SerDeException, TException, IllegalAccessException {
    throw new RuntimeException("not implemented");
  }

  @Override
  public void serialize(Object o, ObjectInspector oi, TProtocol oprot)
      throws TException, SerDeException, NoSuchFieldException, IllegalAccessException {
    throw new RuntimeException("not implemented");
  }

}
