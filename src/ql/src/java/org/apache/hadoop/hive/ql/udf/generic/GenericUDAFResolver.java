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

import org.apache.hadoop.hive.ql.parse.SemanticException;
import org.apache.hadoop.hive.serde2.typeinfo.TypeInfo;

/**
 * A Generic User-defined aggregation function (GenericUDAF) for the use with
 * Hive.
 *
 * GenericUDAFResolver is used at compile time. We use GenericUDAFResolver to
 * find out the GenericUDAFEvaluator for the parameter types.
 *
 * @deprecated Use {@link GenericUDAFResolver2} instead.
 */
@Deprecated
public interface GenericUDAFResolver {

  /**
   * Get the evaluator for the parameter types.
   *
   * The reason that this function returns an object instead of a class is
   * because it is possible that the object needs some configuration (that can
   * be serialized). In that case the class of the object has to implement the
   * Serializable interface. At execution time, we will deserialize the object
   * from the plan and use it to evaluate the aggregations.
   * <p>
   * If the class of the object does not implement Serializable, then we will
   * create a new instance of the class at execution time.
   * </p>
   * @param parameters
   *          The types of the parameters. We need the type information to know
   *          which evaluator class to use.
   * @throws SemanticException
   */
  GenericUDAFEvaluator getEvaluator(TypeInfo[] parameters)
    throws SemanticException;
}
