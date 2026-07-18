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

import org.apache.hadoop.mapred.lib.HashPartitioner;

/** Partition keys by their {@link Object#hashCode()}. */
public class DefaultHivePartitioner<K2, V2> extends HashPartitioner<K2, V2> implements HivePartitioner<K2, V2> {

  /** Use {@link Object#hashCode()} to partition. */
  public int getBucket(K2 key, V2 value, int numBuckets) {
    return (key.hashCode() & Integer.MAX_VALUE) % numBuckets;
  }

}
