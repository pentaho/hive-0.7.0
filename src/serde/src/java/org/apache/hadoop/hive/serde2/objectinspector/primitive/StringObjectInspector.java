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
import org.apache.hadoop.io.Text;

/**
 * A StringObjectInspector inspects an Object representing a String.
 */
public interface StringObjectInspector extends PrimitiveObjectInspector {

  /**
   * Get the Text representation of the data.
   */
  Text getPrimitiveWritableObject(Object o);

  /**
   * Get the String representation of the data.
   */
  String getPrimitiveJavaObject(Object o);
}
