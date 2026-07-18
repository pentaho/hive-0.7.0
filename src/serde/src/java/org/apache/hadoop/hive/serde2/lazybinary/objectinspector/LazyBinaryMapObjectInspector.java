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


package org.apache.hadoop.hive.serde2.lazybinary.objectinspector;

import java.util.Map;

import org.apache.hadoop.hive.serde2.lazybinary.LazyBinaryMap;
import org.apache.hadoop.hive.serde2.objectinspector.ObjectInspector;
import org.apache.hadoop.hive.serde2.objectinspector.StandardMapObjectInspector;

/**
 * ObjectInspector for LazyBinaryMap.
 * 
 * @see LazyBinaryMap
 */
public class LazyBinaryMapObjectInspector extends StandardMapObjectInspector {

  protected LazyBinaryMapObjectInspector(ObjectInspector mapKeyObjectInspector,
      ObjectInspector mapValueObjectInspector) {
    super(mapKeyObjectInspector, mapValueObjectInspector);
  }

  @Override
  public Map<?, ?> getMap(Object data) {
    if (data == null) {
      return null;
    }
    return ((LazyBinaryMap) data).getMap();
  }

  @Override
  public int getMapSize(Object data) {
    if (data == null) {
      return -1;
    }
    return ((LazyBinaryMap) data).getMapSize();
  }

  @Override
  public Object getMapValueElement(Object data, Object key) {
    if (data == null) {
      return -1;
    }
    return ((LazyBinaryMap) data).getMapValueElement(key);
  }
}
