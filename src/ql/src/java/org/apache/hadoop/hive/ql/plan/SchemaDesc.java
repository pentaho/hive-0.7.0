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
 * SchemaDesc.
 *
 */
public class SchemaDesc implements Serializable {
  private static final long serialVersionUID = 1L;
  private String schema;

  public SchemaDesc() {
  }

  public SchemaDesc(final String schema) {
    this.schema = schema;
  }

  public String getSchema() {
    return schema;
  }

  public void setSchema(final String schema) {
    this.schema = schema;
  }

}
