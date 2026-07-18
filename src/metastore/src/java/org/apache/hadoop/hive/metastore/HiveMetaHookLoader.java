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
import org.apache.hadoop.hive.metastore.api.Table;

/**
 * HiveMetaHookLoader is responsible for loading a {@link HiveMetaHook}
 * for a given table.
 */
public interface HiveMetaHookLoader {
  /**
   * Loads a hook for the specified table.
   *
   * @param tbl table of interest
   *
   * @return hook, or null if none registered
   */
  public HiveMetaHook getHook(Table tbl) throws MetaException;
}

// End HiveMetaHookLoader.java
