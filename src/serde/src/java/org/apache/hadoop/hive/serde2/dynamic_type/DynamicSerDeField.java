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
 * DynamicSerDeField.
 *
 */
public class DynamicSerDeField extends DynamicSerDeSimpleNode {

  // production is:
  // [this.fieldid :] Requiredness() FieldType() this.name FieldValue()
  // [CommaOrSemicolon()]

  private final int FD_REQUIREDNESS = 0;
  private final int FD_FIELD_TYPE = 1;

  public boolean isSkippable() {
    return ((DynamicSerDeFieldRequiredness) jjtGetChild(FD_REQUIREDNESS))
        .getRequiredness() == DynamicSerDeFieldRequiredness.RequirednessTypes.Skippable;
  }

  public DynamicSerDeFieldType getFieldType() {
    return (DynamicSerDeFieldType) jjtGetChild(FD_FIELD_TYPE);
  }

  public DynamicSerDeField(int i) {
    super(i);
  }

  public DynamicSerDeField(thrift_grammar p, int i) {
    super(p, i);
  }

}
