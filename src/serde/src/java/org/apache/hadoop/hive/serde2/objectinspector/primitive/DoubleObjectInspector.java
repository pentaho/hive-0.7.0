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
 * A DoubleObjectInspector inspects an Object representing a Double.
 */
public interface DoubleObjectInspector extends PrimitiveObjectInspector {

  /**
   * Get the double data.
   */
  double get(Object o);
}
