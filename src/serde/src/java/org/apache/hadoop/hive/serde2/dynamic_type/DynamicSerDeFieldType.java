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

/**
 * DynamicSerDeFieldType.
 * Basically just a container for the real type so more like a proxy.
 *
 */
public class DynamicSerDeFieldType extends DynamicSerDeSimpleNode {

  // production: this.name | BaseType() | MapType() | SetType() | ListType()

  private final int FD_FIELD_TYPE = 0;

  public DynamicSerDeFieldType(int i) {
    super(i);
  }

  public DynamicSerDeFieldType(thrift_grammar p, int i) {
    super(p, i);
  }

  protected DynamicSerDeTypeBase getMyType() {
    // bugbug, need to deal with a named type here - i.e., look it up and proxy
    // to it
    // should raise an exception if this is a typedef since won't be any
    // children
    // and thus we can quickly find this comment and limitation.
    return (DynamicSerDeTypeBase) jjtGetChild(FD_FIELD_TYPE);
  }
}
