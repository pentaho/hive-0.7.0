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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.hadoop.hive.metastore.api.FieldSchema;
import org.apache.hadoop.hive.metastore.api.Order;
import org.apache.hadoop.hive.ql.exec.Utilities;

/**
 * AlterIndexDesc.
 *
 */
@Explain(displayName = "Alter Index")
public class AlterIndexDesc extends DDLDesc implements Serializable {
  private static final long serialVersionUID = 1L;
  private String indexName;
  private String baseTable;
  private String dbName;
  private HashMap<String, String> props;

  /**
   * alterIndexTypes.
   *
   */
  public static enum AlterIndexTypes {
    ADDPROPS};

  AlterIndexTypes op;

  public AlterIndexDesc() {
  }

  public AlterIndexDesc(AlterIndexTypes type) {
    this.op = type;
  }

  /**
   * @return the name of the index
   */
  @Explain(displayName = "name")
  public String getIndexName() {
    return indexName;
  }

  /**
   * @param indexName
   *          the indexName to set
   */
  public void setIndexName(String indexName) {
    this.indexName = indexName;
  }

  /**
   * @return the baseTable
   */
  @Explain(displayName = "new name")
  public String getBaseTableName() {
    return baseTable;
  }

  /**
   * @param baseTable
   *          the baseTable to set
   */
  public void setBaseTableName(String baseTable) {
    this.baseTable = baseTable;
  }

  /**
   * @return the name of the database that the base table is in
   */
  public String getDbName() {
    return dbName;
  }

  /**
   * @param dbName
   *          the dbName to set
   */
  public void setDbName(String dbName) {
    this.dbName = dbName;
  }

  /**
   * @return the op
   */
  public AlterIndexTypes getOp() {
    return op;
  }

  /**
   * @param op
   *          the op to set
   */
  public void setOp(AlterIndexTypes op) {
    this.op = op;
  }

  /**
   * @return the props
   */
  @Explain(displayName = "properties")
  public HashMap<String, String> getProps() {
    return props;
  }

  /**
   * @param props
   *          the props to set
   */
  public void setProps(HashMap<String, String> props) {
    this.props = props;
  }
}
