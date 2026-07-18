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

import java.util.HashMap;
import java.util.Map;

import org.apache.hadoop.hive.ql.plan.PartitionDesc;

/**
 * IOPrepareCache is used to cache pre-query io-related objects.
 * It should be cleared every time a new query issued.
 *  
 */
public class IOPrepareCache {
  
  private static ThreadLocal<IOPrepareCache> threadLocalIOPrepareCache = new ThreadLocal<IOPrepareCache>();
  
  public static IOPrepareCache get() {
    IOPrepareCache cache = IOPrepareCache.threadLocalIOPrepareCache.get();
    if (cache == null) {
      threadLocalIOPrepareCache.set(new IOPrepareCache());
      cache = IOPrepareCache.threadLocalIOPrepareCache.get();
    }

    return cache;
  }
  
  public void clear() {
    if(partitionDescMap != null) {
      partitionDescMap.clear();      
    }
  }
  
  private Map<Map<String, PartitionDesc>, Map<String, PartitionDesc>> partitionDescMap;
  
  public Map<Map<String, PartitionDesc>, Map<String, PartitionDesc>> allocatePartitionDescMap() {
    if (partitionDescMap == null) {
      partitionDescMap = new HashMap<Map<String, PartitionDesc>, Map<String, PartitionDesc>>();
    }
    return partitionDescMap;
  }

  public Map<Map<String, PartitionDesc>, Map<String, PartitionDesc>> getPartitionDescMap() {
    return partitionDescMap;
  }

  public void setPartitionDescMap(
      Map<Map<String, PartitionDesc>, Map<String, PartitionDesc>> partitionDescMap) {
    this.partitionDescMap = partitionDescMap;
  } 

}
