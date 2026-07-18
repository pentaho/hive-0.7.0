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


package org.apache.hadoop.hive.ql.udf.generic;

import org.apache.hadoop.hive.ql.metadata.HiveException;

/**
 * Collector gets data from a source.
 */
public interface Collector {
  /**
   * Other classes will call collect() with the data that it has.
   * 
   * @param input
   */
  void collect(Object input) throws HiveException;
}
