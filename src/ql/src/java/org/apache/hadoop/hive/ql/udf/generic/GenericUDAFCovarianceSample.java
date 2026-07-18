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
 * Compute the sample covariance by extending GenericUDAFCovariance and overriding
 * the terminate() method of the evaluator.
 *
 */
@Description(name = "covar_samp",
    value = "_FUNC_(x,y) - Returns the sample covariance of a set of number pairs",
    extended = "The function takes as arguments any pair of numeric types and returns a double.\n"
        + "Any pair with a NULL is ignored. If the function is applied to an empty set, NULL\n"
        + "will be returned. Otherwise, it computes the following:\n"
        + "   (SUM(x*y)-SUM(x)*SUM(y)/COUNT(x,y))/(COUNT(x,y)-1)\n"
        + "where neither x nor y is null.")
public class GenericUDAFCovarianceSample extends GenericUDAFCovariance {

  @Override
  public GenericUDAFEvaluator getEvaluator(TypeInfo[] parameters)
      throws SemanticException {
    if (parameters.length != 2) {
      throw new UDFArgumentTypeException(parameters.length - 1,
          "Exactly two arguments are expected.");
    }

    if (parameters[0].getCategory() != ObjectInspector.Category.PRIMITIVE) {
      throw new UDFArgumentTypeException(0,
          "Only primitive type arguments are accepted but "
          + parameters[0].getTypeName() + " is passed.");
    }

    if (parameters[1].getCategory() != ObjectInspector.Category.PRIMITIVE) {
        throw new UDFArgumentTypeException(1,
            "Only primitive type arguments are accepted but "
            + parameters[1].getTypeName() + " is passed.");
    }

    switch (((PrimitiveTypeInfo) parameters[0]).getPrimitiveCategory()) {
    case BYTE:
    case SHORT:
    case INT:
    case LONG:
    case FLOAT:
    case DOUBLE:
      switch (((PrimitiveTypeInfo) parameters[1]).getPrimitiveCategory()) {
      case BYTE:
      case SHORT:
      case INT:
      case LONG:
      case FLOAT:
      case DOUBLE:
        return new GenericUDAFCovarianceSampleEvaluator();
      case STRING:
      case BOOLEAN:
      default:
        throw new UDFArgumentTypeException(1,
            "Only numeric or string type arguments are accepted but "
            + parameters[1].getTypeName() + " is passed.");
      }
    case STRING:
    case BOOLEAN:
    default:
      throw new UDFArgumentTypeException(0,
          "Only numeric or string type arguments are accepted but "
          + parameters[0].getTypeName() + " is passed.");
    }
  }

  /**
   * Compute the sample covariance by extending GenericUDAFCovarianceEvaluator and
   * overriding the terminate() method of the evaluator.
   */
  public static class GenericUDAFCovarianceSampleEvaluator extends
      GenericUDAFCovarianceEvaluator {

    @Override
    public Object terminate(AggregationBuffer agg) throws HiveException {
      StdAgg myagg = (StdAgg) agg;

      if (myagg.count == 0) { // SQL standard - return null for zero elements
        return null;
      } else {
        if (myagg.count > 1) {
          getResult().set(myagg.covar / (myagg.count - 1));
        } else { // the covariance of a singleton set is always 0
          getResult().set(0);
        }
        return getResult();
      }
    }
  }

}
