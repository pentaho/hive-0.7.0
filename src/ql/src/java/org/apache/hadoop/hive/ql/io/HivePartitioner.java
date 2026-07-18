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


package org.apache.hadoop.hive.ql.io;

import org.apache.hadoop.mapred.Partitioner;

/** Partition keys by their {@link Object#hashCode()}. */
public interface HivePartitioner<K2, V2> extends Partitioner<K2, V2> {

  /**
   * Get the final bucket within a partition.
   */
  public int getBucket(K2 key, V2 value, int numBuckets);
}

