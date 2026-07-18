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


package org.apache.hadoop.hive.jdbc;

import java.sql.SQLException;

/**
 * Table metadata.
 */
public class JdbcTable {
  private String tableCatalog;
  private String tableName;
  private String type;
  private String comment;

  public JdbcTable(String tableCatalog, String tableName, String type, String comment) {
    this.tableCatalog = tableCatalog;
    this.tableName = tableName;
    this.type = type;
    this.comment = comment;
  }

  public String getTableCatalog() {
    return tableCatalog;
  }

  public String getTableName() {
    return tableName;
  }

  public String getType() {
    return type;
  }

  public String getSqlTableType() throws SQLException {
    return HiveDatabaseMetaData.toJdbcTableType(type);
  }

  public String getComment() {
    return comment;
  }
}
