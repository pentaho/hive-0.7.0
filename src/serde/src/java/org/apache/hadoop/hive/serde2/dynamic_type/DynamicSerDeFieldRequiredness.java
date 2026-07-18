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
 * DynamicSerDeFieldRequiredness.
 *
 */
public class DynamicSerDeFieldRequiredness extends SimpleNode {
  /**
   * RequirednessTypes.
   *
   */
  public enum RequirednessTypes {
    Required, Skippable, Optional,
  };

  /**
   * Is this a required, skippable or optional field. Used by DynamicSerDe for
   * optimizations.
   */
  protected RequirednessTypes requiredness;

  /**
   * Get the requiredness attribute of this field.
   */
  public RequirednessTypes getRequiredness() {
    return requiredness;
  }

  public DynamicSerDeFieldRequiredness(int id) {
    super(id);
  }

  public DynamicSerDeFieldRequiredness(thrift_grammar p, int id) {
    super(p, id);
  }

}
