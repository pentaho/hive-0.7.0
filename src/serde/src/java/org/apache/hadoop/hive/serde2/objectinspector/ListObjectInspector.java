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

import java.util.List;

/**
 * ListObjectInspector.
 *
 */
public interface ListObjectInspector extends ObjectInspector {

  // ** Methods that does not need a data object **
  ObjectInspector getListElementObjectInspector();

  // ** Methods that need a data object **
  /**
   * returns null for null list, out-of-the-range index.
   */
  Object getListElement(Object data, int index);

  /**
   * returns -1 for data = null.
   */
  int getListLength(Object data);

  /**
   * returns null for data = null.
   * 
   * Note: This method should not return a List object that is reused by the
   * same ListObjectInspector, because it's possible that the same
   * ListObjectInspector will be used in multiple places in the code.
   * 
   * However it's OK if the List object is part of the Object data.
   */
  List<?> getList(Object data);

}
