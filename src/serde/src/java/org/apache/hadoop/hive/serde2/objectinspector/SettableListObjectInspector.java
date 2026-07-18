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
 * SettableListObjectInspector.
 *
 */
public interface SettableListObjectInspector extends ListObjectInspector {

  /**
   * Create a list with the given size. All elements will be null.
   * 
   * NOTE: This is different from ArrayList constructor where the argument is
   * capacity. We decided to have size here to allow creation of Java array.
   */
  Object create(int size);

  /**
   * Set the element at index. Returns the list.
   */
  Object set(Object list, int index, Object element);

  /**
   * Resize the list. Returns the list. If the new size is bigger than the
   * current size, new elements will be null. If the new size is smaller than
   * the current size, elements at the end are truncated.
   */
  Object resize(Object list, int newSize);
}
