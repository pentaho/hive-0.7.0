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
 * DropDatabaseDesc.
 *
 */
@Explain(displayName = "Drop Database")
public class DropDatabaseDesc extends DDLDesc implements Serializable {
  private static final long serialVersionUID = 1L;

  String databaseName;
  boolean ifExists;

  public DropDatabaseDesc(String databaseName, boolean ifExists) {
    super();
    this.databaseName = databaseName;
    this.ifExists = ifExists;
  }

  @Explain(displayName = "database")
  public String getDatabaseName() {
    return databaseName;
  }

  public void setDatabaseName(String databaseName) {
    this.databaseName = databaseName;
  }

  @Explain(displayName = "if exists")
  public boolean getIfExists() {
    return ifExists;
  }

  public void setIfExists(boolean ifExists) {
    this.ifExists = ifExists;
  }
}
