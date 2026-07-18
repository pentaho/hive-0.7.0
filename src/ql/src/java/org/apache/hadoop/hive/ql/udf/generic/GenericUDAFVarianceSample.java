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

import org.apache.hadoop.hive.ql.exec.Description;
import org.apache.hadoop.hive.ql.exec.UDFArgumentTypeException;
import org.apache.hadoop.hive.ql.metadata.HiveException;
import org.apache.hadoop.hive.ql.parse.SemanticException;
import org.apache.hadoop.hive.serde2.objectinspector.ObjectInspector;
import org.apache.hadoop.hive.serde2.typeinfo.PrimitiveTypeInfo;
import org.apache.hadoop.hive.serde2.typeinfo.TypeInfo;

/**
 * Compute the sample variance by extending GenericUDAFVariance and overriding
 * the terminate() method of the evaluator.
 * 
 */
@Description(name = "var_samp",
    value = "_FUNC_(x) - Returns the sample variance of a set of numbers")
public class GenericUDAFVarianceSample extends GenericUDAFVariance {

  @Override
  public GenericUDAFEvaluator getEvaluator(TypeInfo[] parameters)
      throws SemanticException {
    if (parameters.length != 1) {
      throw new UDFArgumentTypeException(parameters.length - 1,
          "Exactly one argument is expected.");
    }

    if (parameters[0].getCategory() != ObjectInspector.Category.PRIMITIVE) {
      throw new UDFArgumentTypeException(0,
          "Only primitive type arguments are accepted but "
          + parameters[0].getTypeName() + " is passed.");
    }
    switch (((PrimitiveTypeInfo) parameters[0]).getPrimitiveCategory()) {
    case BYTE:
    case SHORT:
    case INT:
    case LONG:
    case FLOAT:
    case DOUBLE:
    case STRING:
      return new GenericUDAFVarianceSampleEvaluator();
    case BOOLEAN:
    default:
      throw new UDFArgumentTypeException(0,
          "Only numeric or string type arguments are accepted but "
          + parameters[0].getTypeName() + " is passed.");
    }
  }

  /**
   * Compute the sample variance by extending GenericUDAFVarianceEvaluator and
   * overriding the terminate() method of the evaluator.
   */
  public static class GenericUDAFVarianceSampleEvaluator extends
      GenericUDAFVarianceEvaluator {

    @Override
    public Object terminate(AggregationBuffer agg) throws HiveException {
      StdAgg myagg = (StdAgg) agg;

      if (myagg.count == 0) { // SQL standard - return null for zero elements
        return null;
      } else {
        if (myagg.count > 1) {
          getResult().set(myagg.variance / (myagg.count - 1));
        } else { // for one element the variance is always 0
          getResult().set(0);
        }
        return getResult();
      }
    }
  }

}
