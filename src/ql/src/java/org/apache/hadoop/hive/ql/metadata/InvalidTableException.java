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


package org.apache.hadoop.hive.ql.metadata;

/**
 * Generic exception class for Hive.
 * 
 */

public class InvalidTableException extends HiveException {
  String tableName;

  public InvalidTableException(String tableName) {
    super();
    this.tableName = tableName;
  }

  public InvalidTableException(String message, String tableName) {
    super(message);
    this.tableName = tableName;
  }

  public InvalidTableException(Throwable cause, String tableName) {
    super(cause);
    this.tableName = tableName;
  }

  public InvalidTableException(String message, Throwable cause, String tableName) {
    super(message, cause);
    this.tableName = tableName;
  }

  public String getTableName() {
    return tableName;
  }

}
