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

import org.apache.hadoop.hive.serde2.typeinfo.TypeInfo;

/**
 * A callback interface used in conjunction with <tt>GenericUDAFResolver2</tt>
 * interface that allows for a more extensible and flexible means of
 * discovering the parameter types provided for UDAF invocation. Apart from
 * allowing the function implementation to discover the <tt>TypeInfo</tt> of
 * any types provided in the invocation, this also allows the implementation
 * to determine if the parameters were qualified using <tt>DISTINCT</tt>. If
 * no parameters were specified explicitly, it allows the function
 * implementation to test if the invocation used the wildcard syntax
 * such as <tt>FUNCTION(*)</tt>.
 * <p>
 * <b>Note:</b> The implementation of function does not have to handle the
 * actual <tt>DISTINCT</tt> or wildcard implementation. This information is
 * provided only to allow the function implementation to accept or reject
 * such invocations. For example - the implementation of <tt>COUNT</tt> UDAF
 * requires that the <tt>DISTINCT</tt> qualifier be supplied when more than
 * one parameters are specified in the invocation. The actual filtering of
 * data bound to parameter types for <tt>DISTINCT</tt> implementation is
 * handled by the framework and not the <tt>COUNT</tt> UDAF implementation.
 */
public interface GenericUDAFParameterInfo {

  /**
   *
   * @return the parameter type list passed into the UDAF.
   */
  TypeInfo[] getParameters();

  /**
   * Returns <tt>true</tt> if the UDAF invocation was qualified with
   * <tt>DISTINCT</tt> keyword. Note that this is provided for informational
   * purposes only and the function implementation is not expected to ensure
   * the distinct property for the parameter values. That is handled by the
   * framework.
   * @return <tt>true</tt> if the UDAF invocation was qualified with
   * <tt>DISTINCT</tt> keyword, <tt>false</tt> otherwise.
   */
  boolean isDistinct();

  /**
   * Returns <tt>true</tt> if the UDAF invocation was done via the wildcard
   * syntax <tt>FUNCTION(*)</tt>. Note that this is provided for informational
   * purposes only and the function implementation is not expected to ensure
   * the wildcard handling of the target relation. That is handled by the
   * framework.
   * @return <tt>true</tt> if the UDAF invocation was done with a wildcard
   * instead of explicit parameter list.
   */
  boolean isAllColumns();

}
