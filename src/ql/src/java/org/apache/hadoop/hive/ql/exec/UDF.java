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


package org.apache.hadoop.hive.ql.exec;

import org.apache.hadoop.hive.ql.udf.UDFType;

/**
 * A User-defined function (UDF) for the use with Hive.
 * 
 * New UDF classes need to inherit from this UDF class.
 * 
 * Required for all UDF classes: 1. Implement one or more methods named
 * "evaluate" which will be called by Hive. The following are some examples:
 * public int evaluate(); public int evaluate(int a); public double evaluate(int
 * a, double b); public String evaluate(String a, int b, String c);
 * 
 * "evaluate" should never be a void method. However it can return "null" if
 * needed.
 */
@UDFType(deterministic = true)
public class UDF {

  /**
   * The resolver to use for method resolution.
   */
  private UDFMethodResolver rslv;

  /**
   * The constructor.
   */
  public UDF() {
    rslv = new DefaultUDFMethodResolver(this.getClass());
  }

  /**
   * The constructor with user-provided UDFMethodResolver.
   */
  protected UDF(UDFMethodResolver rslv) {
    this.rslv = rslv;
  }

  /**
   * Sets the resolver.
   * 
   * @param rslv
   *          The method resolver to use for method resolution.
   */
  public void setResolver(UDFMethodResolver rslv) {
    this.rslv = rslv;
  }

  /**
   * Get the method resolver.
   */
  public UDFMethodResolver getResolver() {
    return rslv;
  }
}
