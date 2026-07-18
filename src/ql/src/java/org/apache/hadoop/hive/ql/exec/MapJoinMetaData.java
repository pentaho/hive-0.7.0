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


package org.apache.hadoop.hive.ql.exec;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.apache.hadoop.hive.ql.exec.HashTableSinkOperator.HashTableSinkObjectCtx;

public class MapJoinMetaData {
  static transient Map<Integer, HashTableSinkObjectCtx> mapMetadata = new HashMap<Integer, HashTableSinkObjectCtx>();
  static ArrayList<Object> list = new ArrayList<Object>();

  public MapJoinMetaData(){

  }
  public static void put(Integer key, HashTableSinkObjectCtx value){
    mapMetadata.put(key, value);
  }
  public static HashTableSinkObjectCtx get(Integer key){
    return mapMetadata.get(key);
  }

  public static void clear(){
    mapMetadata.clear();
  }

  public static ArrayList<Object> getList(){
    list.clear();
    return list;
  }

}
