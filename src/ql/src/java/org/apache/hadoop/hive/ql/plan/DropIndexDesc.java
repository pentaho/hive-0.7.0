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

public class DropIndexDesc {
  
  private static final long serialVersionUID = 1L;
  
  private String indexName;
  
  private String tableName;
  
  /**
   * @param indexName
   * @param tableName
   */
  public DropIndexDesc(String indexName, String tableName) {
    super();
    this.indexName = indexName;
    this.tableName = tableName;
  }

  /**
   * @return index name
   */
  public String getIndexName() {
    return indexName;
  }

  /**
   * @param indexName index name
   */
  public void setIndexName(String indexName) {
    this.indexName = indexName;
  }

  /**
   * @return table name
   */
  public String getTableName() {
    return tableName;
  }

  /**
   * @param tableName table name
   */
  public void setTableName(String tableName) {
    this.tableName = tableName;
  }

}
