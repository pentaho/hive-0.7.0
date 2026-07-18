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

import org.apache.thrift.protocol.TType;

/**
 * DynamicSerDeStruct.
 *
 */
public class DynamicSerDeStruct extends DynamicSerDeStructBase {

  // production is: struct this.name { FieldList() }

  private static final int FD_FIELD_LIST = 0;

  public DynamicSerDeStruct(int i) {
    super(i);
  }

  public DynamicSerDeStruct(thrift_grammar p, int i) {
    super(p, i);
  }

  @Override
  public String toString() {
    String result = "struct " + name + "(";
    result += getFieldList().toString();
    result += ")";
    return result;
  }

  @Override
  public DynamicSerDeFieldList getFieldList() {
    return (DynamicSerDeFieldList) jjtGetChild(FD_FIELD_LIST);
  }

  @Override
  public byte getType() {
    return TType.STRUCT;
  }

}
