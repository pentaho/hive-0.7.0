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
 * Simple wrapper of object with ObjectInspector.
 * 
 * TODO: we need to redefine the hashCode and equals methods, so that it can be
 * put into a HashMap as a key.
 * 
 * This class also serves as a facility for a function that returns both an
 * object and an ObjectInspector.
 */
public class InspectableObject {

  public Object o;
  public ObjectInspector oi;

  public InspectableObject() {
    this(null, null);
  }

  public InspectableObject(Object o, ObjectInspector oi) {
    this.o = o;
    this.oi = oi;
  }

}
