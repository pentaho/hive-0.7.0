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

import org.apache.thrift.protocol.TMessageType;

/**
 * DynamicSerDeFunction.
 *
 */
public class DynamicSerDeFunction extends DynamicSerDeStructBase {

  // production is: Async() FunctionType() NAME FieldList() Throws()
  // [CommaOrSemicolon]

  private final int FD_FIELD_LIST = 2;

  public DynamicSerDeFunction(int i) {
    super(i);
  }

  public DynamicSerDeFunction(thrift_grammar p, int i) {
    super(p, i);
  }

  @Override
  public DynamicSerDeFieldList getFieldList() {
    return (DynamicSerDeFieldList) jjtGetChild(FD_FIELD_LIST);
  }

  @Override
  public String toString() {
    String result = "function " + name + " (";
    result += getFieldList().toString();
    result += ")";
    return result;
  }

  @Override
  public byte getType() {
    return TMessageType.CALL;
  }

}
