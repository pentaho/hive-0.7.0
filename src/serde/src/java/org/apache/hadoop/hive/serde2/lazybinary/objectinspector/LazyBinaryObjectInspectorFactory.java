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

package org.apache.hadoop.hive.serde2.lazybinary.objectinspector;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.hadoop.hive.serde2.objectinspector.ObjectInspector;

/**
 * ObjectInspectorFactory is the primary way to create new ObjectInspector
 * instances.
 * 
 * SerDe classes should call the static functions in this library to create an
 * ObjectInspector to return to the caller of SerDe2.getObjectInspector().
 * 
 * The reason of having caches here is that ObjectInspectors do not have an
 * internal state - so ObjectInspectors with the same construction parameters
 * should result in exactly the same ObjectInspector.
 */

public final class LazyBinaryObjectInspectorFactory {

  static HashMap<ArrayList<Object>, LazyBinaryStructObjectInspector> cachedLazyBinaryStructObjectInspector = new HashMap<ArrayList<Object>, LazyBinaryStructObjectInspector>();

  public static LazyBinaryStructObjectInspector getLazyBinaryStructObjectInspector(
      List<String> structFieldNames,
      List<ObjectInspector> structFieldObjectInspectors) {
    ArrayList<Object> signature = new ArrayList<Object>();
    signature.add(structFieldNames);
    signature.add(structFieldObjectInspectors);
    LazyBinaryStructObjectInspector result = cachedLazyBinaryStructObjectInspector
        .get(signature);
    if (result == null) {
      result = new LazyBinaryStructObjectInspector(structFieldNames,
          structFieldObjectInspectors);
      cachedLazyBinaryStructObjectInspector.put(signature, result);
    }
    return result;
  }

  static HashMap<ArrayList<Object>, LazyBinaryListObjectInspector> cachedLazyBinaryListObjectInspector = new HashMap<ArrayList<Object>, LazyBinaryListObjectInspector>();

  public static LazyBinaryListObjectInspector getLazyBinaryListObjectInspector(
      ObjectInspector listElementObjectInspector) {
    ArrayList<Object> signature = new ArrayList<Object>();
    signature.add(listElementObjectInspector);
    LazyBinaryListObjectInspector result = cachedLazyBinaryListObjectInspector
        .get(signature);
    if (result == null) {
      result = new LazyBinaryListObjectInspector(listElementObjectInspector);
      cachedLazyBinaryListObjectInspector.put(signature, result);
    }
    return result;
  }

  static HashMap<ArrayList<Object>, LazyBinaryMapObjectInspector> cachedLazyBinaryMapObjectInspector = new HashMap<ArrayList<Object>, LazyBinaryMapObjectInspector>();

  public static LazyBinaryMapObjectInspector getLazyBinaryMapObjectInspector(
      ObjectInspector mapKeyObjectInspector,
      ObjectInspector mapValueObjectInspector) {
    ArrayList<Object> signature = new ArrayList<Object>();
    signature.add(mapKeyObjectInspector);
    signature.add(mapValueObjectInspector);
    LazyBinaryMapObjectInspector result = cachedLazyBinaryMapObjectInspector
        .get(signature);
    if (result == null) {
      result = new LazyBinaryMapObjectInspector(mapKeyObjectInspector,
          mapValueObjectInspector);
      cachedLazyBinaryMapObjectInspector.put(signature, result);
    }
    return result;
  }

  private LazyBinaryObjectInspectorFactory() {
    // prevent instantiation
  }
}
