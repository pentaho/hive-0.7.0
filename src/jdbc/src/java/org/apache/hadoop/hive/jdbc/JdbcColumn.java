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
 * Column metadata.
 */
public class JdbcColumn {
  private final String columnName;
  private final String tableName;
  private final String tableCatalog;
  private final String type;
  private final String comment;
  private final int ordinalPos;

  JdbcColumn(String columnName, String tableName, String tableCatalog
          , String type, String comment, int ordinalPos) {
    this.columnName = columnName;
    this.tableName = tableName;
    this.tableCatalog = tableCatalog;
    this.type = type;
    this.comment = comment;
    this.ordinalPos = ordinalPos;
  }

  public String getColumnName() {
    return columnName;
  }

  public String getTableName() {
    return tableName;
  }

  public String getTableCatalog() {
    return tableCatalog;
  }

  public String getType() {
    return type;
  }

  public Integer getSqlType() throws SQLException {
    return HiveResultSetMetaData.hiveTypeToSqlType(type);
  }

  public Integer getColumnSize() {
    if (type.equalsIgnoreCase("string")) {
      return Integer.MAX_VALUE;
    } else if (type.equalsIgnoreCase("tinyint")) {
      return 3;
    } else if (type.equalsIgnoreCase("smallint")) {
      return 5;
    } else if (type.equalsIgnoreCase("int")) {
      return 10;
    } else if (type.equalsIgnoreCase("bigint")) {
      return 19;
    } else if (type.equalsIgnoreCase("float")) {
      return 12;
    } else if (type.equalsIgnoreCase("double")) {
      return 22;
    } else { // anything else including boolean is null
      return null;
    }
  }

  public Integer getNumPrecRadix() {
    if (type.equalsIgnoreCase("tinyint")) {
      return 10;
    } else if (type.equalsIgnoreCase("smallint")) {
      return 10;
    } else if (type.equalsIgnoreCase("int")) {
      return 10;
    } else if (type.equalsIgnoreCase("bigint")) {
      return 10;
    } else if (type.equalsIgnoreCase("float")) {
      return 2;
    } else if (type.equalsIgnoreCase("double")) {
      return 2;
    } else { // anything else including boolean and string is null
      return null;
    }
  }

  public Integer getDecimalDigits() {
    if (type.equalsIgnoreCase("tinyint")) {
      return 0;
    } else if (type.equalsIgnoreCase("smallint")) {
      return 0;
    } else if (type.equalsIgnoreCase("int")) {
      return 0;
    } else if (type.equalsIgnoreCase("bigint")) {
      return 0;
    } else { // anything else including float and double is null
      return null;
    }
  }

  public String getComment() {
    return comment;
  }

  public int getOrdinalPos() {
    return ordinalPos;
  }
}
