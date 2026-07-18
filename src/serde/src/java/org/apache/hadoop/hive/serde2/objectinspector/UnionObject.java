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
 * The UnionObject.
 *
 * It has tag followed by the object it is holding.
 *
 */
public interface UnionObject {
  /**
   * Get the tag of the union.
   *
   * @return the tag byte
   */
  byte getTag();

  /**
   * Get the Object.
   *
   * @return The Object union is holding
   */
  Object getObject();

}
