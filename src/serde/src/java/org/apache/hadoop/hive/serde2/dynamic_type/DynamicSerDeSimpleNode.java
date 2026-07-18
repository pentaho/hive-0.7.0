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
 * DynamicSerDeSimpleNode.
 *
 */
public class DynamicSerDeSimpleNode extends SimpleNode {
  protected static final boolean thrift_mode = true;

  public DynamicSerDeSimpleNode(int i) {
    super(i);
  }

  public DynamicSerDeSimpleNode(thrift_grammar p, int i) {
    super(p, i);
  }

  protected int fieldid;
  protected String name;
}
