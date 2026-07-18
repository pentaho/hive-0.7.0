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

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.hive.metastore.api.MetaException;

/**
 * Define a set of APIs that may vary in different environments
 */
public interface MetaStoreFS {

  /**
   * delete a directory
   * 
   * @param f
   * @param recursive
   * @return
   * @throws MetaException
   */
  public boolean deleteDir(FileSystem fs, Path f, boolean recursive,
      Configuration conf) throws MetaException;

}
