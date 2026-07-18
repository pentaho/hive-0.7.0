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

import java.util.Iterator;

/**
 * Simple reducer interface.
 */
public interface Reducer {
  /**
   * Reduce.
   * 
   * Note that it is assumed that the key is the first column. Additionally, the
   * key will be repeated as the first column in the records[] array.
   * 
   * @param key
   *          key (first column) for this set of records.
   * @param records
   *          Iterator of records for this key. Note that the first column of
   *          record will also be the key.
   * @param output
   * @throws Exception
   */
  void reduce(String key, Iterator<String[]> records, Output output)
      throws Exception;
}
