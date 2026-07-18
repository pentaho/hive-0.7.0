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

import org.apache.hadoop.conf.Configurable;
import org.apache.hadoop.hive.metastore.api.InvalidOperationException;
import org.apache.hadoop.hive.metastore.api.MetaException;
import org.apache.hadoop.hive.metastore.api.Table;

/**
 * Interface for Alter Table and Alter Partition code
 */
public interface AlterHandler extends Configurable {

  /**
   * handles alter table
   * 
   * @param msdb
   *          object to get metadata
   * @param wh
   *          TODO
   * @param dbname
   *          database of the table being altered
   * @param name
   *          original name of the table being altered. same as
   *          <i>newTable.tableName</i> if alter op is not a rename.
   * @param newTable
   *          new table object
   * @throws InvalidOperationException
   *           thrown if the newTable object is invalid
   * @throws MetaException
   *           thrown if there is any other erro
   */
  public abstract void alterTable(RawStore msdb, Warehouse wh, String dbname,
      String name, Table newTable) throws InvalidOperationException,
      MetaException;
}
