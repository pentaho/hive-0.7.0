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


package org.apache.hadoop.hive.contrib.mr;

/**
 * Mapper.
 */
public interface Mapper {
  /**
   * Maps a single row into an intermediate rows.
   * 
   * @param record
   *          input record
   * @param output
   *          collect mapped rows.
   * @throws Exception
   *           on error
   */
  void map(String[] record, Output output) throws Exception;
}
