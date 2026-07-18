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


package org.apache.hadoop.hive.serde2.objectinspector.primitive;

import org.apache.hadoop.hive.serde2.objectinspector.PrimitiveObjectInspector;

/**
 * A ByteObjectInspector inspects an Object representing a Byte object.
 */
public interface ByteObjectInspector extends PrimitiveObjectInspector {

  /**
   * Get the byte data.
   */
  byte get(Object o);

}
