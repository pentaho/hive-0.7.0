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
 * ObjectInspector helps us to look into the internal structure of a complex
 * object.
 *
 * A (probably configured) ObjectInspector instance stands for a specific type
 * and a specific way to store the data of that type in the memory.
 *
 * For native java Object, we can directly access the internal structure through
 * member fields and methods. ObjectInspector is a way to delegate that
 * functionality away from the Object, so that we have more control on the
 * behavior of those actions.
 *
 * An efficient implementation of ObjectInspector should rely on factory, so
 * that we can make sure the same ObjectInspector only has one instance. That
 * also makes sure hashCode() and equals() methods of java.lang.Object directly
 * works for ObjectInspector as well.
 */
public interface ObjectInspector extends Cloneable {

  /**
   * Category.
   *
   */
  public static enum Category {
    PRIMITIVE, LIST, MAP, STRUCT, UNION
  };

  /**
   * Returns the name of the data type that is inspected by this
   * ObjectInspector. This is used to display the type information to the user.
   *
   * For primitive types, the type name is standardized. For other types, the
   * type name can be something like "list<int>", "map<int,string>", java class
   * names, or user-defined type names similar to typedef.
   */
  String getTypeName();

  /**
   * An ObjectInspector must inherit from one of the following interfaces if
   * getCategory() returns: PRIMITIVE: PrimitiveObjectInspector LIST:
   * ListObjectInspector MAP: MapObjectInspector STRUCT: StructObjectInspector.
   */
  Category getCategory();
}
