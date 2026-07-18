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

/**
 * SettableMapObjectInspector.
 *
 */
public interface SettableMapObjectInspector extends MapObjectInspector {

  /**
   * Create an empty map.
   */
  Object create();

  /**
   * Add a key-value pair to the map. Return the map.
   */
  Object put(Object map, Object key, Object value);

  /**
   * Remove a key-value pair from the map. Return the map.
   */
  Object remove(Object map, Object key);

  /**
   * Clear the map. Return the map.
   */
  Object clear(Object map);
}
