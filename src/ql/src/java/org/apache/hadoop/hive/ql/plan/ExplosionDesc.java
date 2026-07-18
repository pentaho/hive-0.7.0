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


package org.apache.hadoop.hive.ql.plan;

import java.io.Serializable;

/**
 * ExplosionDesc.
 *
 */
@Explain(displayName = "Explosion")
public class ExplosionDesc implements Serializable {
  private static final long serialVersionUID = 1L;
  private String fieldName;
  private int position;

  public ExplosionDesc() {
  }

  public ExplosionDesc(final String fieldName, final int position) {
    this.fieldName = fieldName;
    this.position = position;
  }

  public String getFieldName() {
    return fieldName;
  }

  public void setFieldName(final String fieldName) {
    this.fieldName = fieldName;
  }

  public int getPosition() {
    return position;
  }

  public void setPosition(final int position) {
    this.position = position;
  }
}
