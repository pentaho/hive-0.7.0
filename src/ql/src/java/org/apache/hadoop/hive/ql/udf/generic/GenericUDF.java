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


package org.apache.hadoop.hive.ql.udf.generic;

import org.apache.hadoop.hive.ql.exec.UDFArgumentException;
import org.apache.hadoop.hive.ql.metadata.HiveException;
import org.apache.hadoop.hive.ql.udf.UDFType;
import org.apache.hadoop.hive.serde2.objectinspector.ObjectInspector;

/**
 * A Generic User-defined function (GenericUDF) for the use with Hive.
 * 
 * New GenericUDF classes need to inherit from this GenericUDF class.
 * 
 * The GenericUDF are superior to normal UDFs in the following ways: 1. It can
 * accept arguments of complex types, and return complex types. 2. It can accept
 * variable length of arguments. 3. It can accept an infinite number of function
 * signature - for example, it's easy to write a GenericUDF that accepts
 * array<int>, array<array<int>> and so on (arbitrary levels of nesting). 4. It
 * can do short-circuit evaluations using DeferedObject.
 */
@UDFType(deterministic = true)
public abstract class GenericUDF {

  /**
   * A Defered Object allows us to do lazy-evaluation and short-circuiting.
   * GenericUDF use DeferedObject to pass arguments.
   */
  public static interface DeferredObject {
    Object get() throws HiveException;
  };

  /**
   * The constructor.
   */
  public GenericUDF() {
  }

  /**
   * Initialize this GenericUDF. This will be called once and only once per
   * GenericUDF instance.
   * 
   * @param arguments
   *          The ObjectInspector for the arguments
   * @throws UDFArgumentException
   *           Thrown when arguments have wrong types, wrong length, etc.
   * @return The ObjectInspector for the return value
   */
  public abstract ObjectInspector initialize(ObjectInspector[] arguments)
      throws UDFArgumentException;

  /**
   * Evaluate the GenericUDF with the arguments.
   * 
   * @param arguments
   *          The arguments as DeferedObject, use DeferedObject.get() to get the
   *          actual argument Object. The Objects can be inspected by the
   *          ObjectInspectors passed in the initialize call.
   * @return The
   */
  public abstract Object evaluate(DeferredObject[] arguments)
      throws HiveException;

  /**
   * Get the String to be displayed in explain.
   */
  public abstract String getDisplayString(String[] children);

}
