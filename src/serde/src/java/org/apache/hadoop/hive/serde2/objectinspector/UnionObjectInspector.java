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
 * UnionObjectInspector works on union data that is stored as UnionObject.
 *
 * It holds the list of the object inspectors corresponding to each type of the
 * object the Union can hold.
 *
 * UnionObjectInspector.
 *
 */
public interface UnionObjectInspector extends ObjectInspector {

  /**
   *  Returns the array of ObjectInspectors that are for each of the tags.
   */
  List<ObjectInspector> getObjectInspectors();

  /**
   *   Return the tag of the object.
   */
  byte getTag(Object o);

  /**
   *  Return the field based on the tag associated with the Object.
   */
  Object getField(Object o);

}
