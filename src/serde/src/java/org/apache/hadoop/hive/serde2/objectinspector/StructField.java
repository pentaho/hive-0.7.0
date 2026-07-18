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
 * StructField is an empty interface.
 * 
 * Classes implementing this interface are considered to represent a field of a
 * struct for this serde package.
 */
public interface StructField {

  /**
   * Get the name of the field. The name should be always in lower-case.
   */
  String getFieldName();

  /**
   * Get the ObjectInspector for the field.
   */
  ObjectInspector getFieldObjectInspector();

}
