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


package org.apache.hadoop.hive.serde2.objectinspector;

import java.util.Map;

/**
 * MapObjectInspector.
 *
 */
public interface MapObjectInspector extends ObjectInspector {

  // ** Methods that does not need a data object **
  // Map Type
  ObjectInspector getMapKeyObjectInspector();

  ObjectInspector getMapValueObjectInspector();

  // ** Methods that need a data object **
  // In this function, key has to be of the same structure as the Map expects.
  // Most cases key will be primitive type, so it's OK.
  // In rare cases that key is not primitive, the user is responsible for
  // defining
  // the hashCode() and equals() methods of the key class.
  Object getMapValueElement(Object data, Object key);

  /**
   * returns null for data = null.
   * 
   * Note: This method should not return a Map object that is reused by the same
   * MapObjectInspector, because it's possible that the same MapObjectInspector
   * will be used in multiple places in the code.
   * 
   * However it's OK if the Map object is part of the Object data.
   */
  Map<?, ?> getMap(Object data);

  /**
   * returns -1 for NULL map.
   */
  int getMapSize(Object data);
}
