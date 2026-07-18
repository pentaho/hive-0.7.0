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


package org.apache.hadoop.hive.metastore;

import org.apache.hadoop.hive.metastore.api.MetaException;
import org.apache.hadoop.hive.metastore.api.Partition;
import org.apache.hadoop.hive.metastore.api.Table;

/**
 * HiveMetaHook defines notification methods which are invoked as part
 * of transactions against the metastore, allowing external catalogs
 * such as HBase to be kept in sync with Hive's metastore.
 *
 *<p>
 *
 * Implementations can use {@link MetaStoreUtils#isExternalTable} to
 * distinguish external tables from managed tables.
 */
public interface HiveMetaHook {
  /**
   * Called before a new table definition is added to the metastore
   * during CREATE TABLE.
   *
   * @param table new table definition
   */
  public void preCreateTable(Table table)
    throws MetaException;

  /**
   * Called after failure adding a new table definition to the metastore
   * during CREATE TABLE.
   *
   * @param table new table definition
   */
  public void rollbackCreateTable(Table table)
    throws MetaException;

  /**
   * Called after successfully adding a new table definition to the metastore
   * during CREATE TABLE.
   *
   * @param table new table definition
   */
  public void commitCreateTable(Table table)
    throws MetaException;

  /**
   * Called before a table definition is removed from the metastore
   * during DROP TABLE.
   *
   * @param table table definition
   */
  public void preDropTable(Table table)
    throws MetaException;

  /**
   * Called after failure removing a table definition from the metastore
   * during DROP TABLE.
   *
   * @param table table definition
   */
  public void rollbackDropTable(Table table)
    throws MetaException;

  /**
   * Called after successfully removing a table definition from the metastore
   * during DROP TABLE.
   *
   * @param table table definition
   *
   * @param deleteData whether to delete data as well; this should typically
   * be ignored in the case of an external table
   */
  public void commitDropTable(Table table, boolean deleteData)
    throws MetaException;
}
