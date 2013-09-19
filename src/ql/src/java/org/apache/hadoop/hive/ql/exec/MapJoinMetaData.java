/*!
* Copyright 2010 - 2013 Pentaho Corporation.  All rights reserved.
*
* Licensed under the Apache License, Version 2.0 (the "License");
* you may not use this file except in compliance with the License.
* You may obtain a copy of the License at
*
* http://www.apache.org/licenses/LICENSE-2.0
*
* Unless required by applicable law or agreed to in writing, software
* distributed under the License is distributed on an "AS IS" BASIS,
* WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
* See the License for the specific language governing permissions and
* limitations under the License.
*
*/

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
