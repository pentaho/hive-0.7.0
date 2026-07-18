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


package org.apache.hadoop.hive.serde2.typeinfo;

import java.io.Serializable;

import org.apache.hadoop.hive.serde2.objectinspector.ObjectInspector.Category;

/**
 * Stores information about a type. Always use the TypeInfoFactory to create new
 * TypeInfo objects.
 *
 * We support 5 categories of types: 1. Primitive objects (String, Number, etc)
 * 2. List objects (a list of objects of a single type) 3. Map objects (a map
 * from objects of one type to objects of another type) 4. Struct objects (a
 * list of fields with names and their own types) 5. Union objects
 */
public abstract class TypeInfo implements Serializable {

  private static final long serialVersionUID = 1L;

  protected TypeInfo() {
  }

  /**
   * The Category of this TypeInfo. Possible values are Primitive, List, Map,
   * Struct and Union, which corresponds to the 5 sub-classes of TypeInfo.
   */
  public abstract Category getCategory();

  /**
   * A String representation of the TypeInfo.
   */
  public abstract String getTypeName();

  @Override
  public String toString() {
    return getTypeName();
  }

  @Override
  public abstract boolean equals(Object o);

  @Override
  public abstract int hashCode();
}
