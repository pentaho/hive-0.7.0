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
 * SettableStructObjectInspector.
 *
 */
public abstract class SettableStructObjectInspector extends
    StructObjectInspector {

  /**
   * Create a struct which is not null, but all its fields are null.
   */
  public abstract Object create();

  /**
   * Modify the value of a field. Returns the struct.
   */
  public abstract Object setStructFieldData(Object struct, StructField field,
      Object fieldValue);
}
