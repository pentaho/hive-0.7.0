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
 * SwitchDatabaseDesc.
 *
 */
@Explain(displayName = "Switch Database")
public class SwitchDatabaseDesc extends DDLDesc implements Serializable {

  private static final long serialVersionUID = 1L;

  String databaseName;

  public SwitchDatabaseDesc() {
  }

  public SwitchDatabaseDesc(String databaseName) {
    super();
    this.databaseName = databaseName;
  }

  @Explain(displayName = "name")
  public String getDatabaseName() {
    return databaseName;
  }

  public void setDatabaseName(String databaseName) {
    this.databaseName = databaseName;
  }
}
