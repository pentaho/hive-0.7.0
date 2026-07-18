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
 * Collects output.
 * 
 * It's the responsibility of the caller to ensure the output is in the correct
 * format (contains the correct number of columns, etc.)
 */
public interface Output {
  /**
   * Add a row to the output.
   * 
   * @param record
   * @throws Exception
   */
  void collect(String[] record) throws Exception;
}
