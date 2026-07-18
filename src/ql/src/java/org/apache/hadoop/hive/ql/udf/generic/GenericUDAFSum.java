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

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.hadoop.hive.ql.exec.Description;
import org.apache.hadoop.hive.ql.exec.UDFArgumentTypeException;
import org.apache.hadoop.hive.ql.metadata.HiveException;
import org.apache.hadoop.hive.ql.parse.SemanticException;
import org.apache.hadoop.hive.serde2.io.DoubleWritable;
import org.apache.hadoop.hive.serde2.objectinspector.ObjectInspector;
import org.apache.hadoop.hive.serde2.objectinspector.PrimitiveObjectInspector;
import org.apache.hadoop.hive.serde2.objectinspector.primitive.PrimitiveObjectInspectorFactory;
import org.apache.hadoop.hive.serde2.objectinspector.primitive.PrimitiveObjectInspectorUtils;
import org.apache.hadoop.hive.serde2.typeinfo.PrimitiveTypeInfo;
import org.apache.hadoop.hive.serde2.typeinfo.TypeInfo;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.util.StringUtils;

/**
 * GenericUDAFSum.
 *
 */
@Description(name = "sum", value = "_FUNC_(x) - Returns the sum of a set of numbers")
public class GenericUDAFSum extends AbstractGenericUDAFResolver {

  static final Log LOG = LogFactory.getLog(GenericUDAFSum.class.getName());

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
      return new GenericUDAFSumLong();
    case FLOAT:
    case DOUBLE:
    case STRING:
      return new GenericUDAFSumDouble();
    case BOOLEAN:
    default:
      throw new UDFArgumentTypeException(0,
          "Only numeric or string type arguments are accepted but "
          + parameters[0].getTypeName() + " is passed.");
    }
  }

  /**
   * GenericUDAFSumDouble.
   *
   */
  public static class GenericUDAFSumDouble extends GenericUDAFEvaluator {
    private PrimitiveObjectInspector inputOI;
    private DoubleWritable result;

    @Override
    public ObjectInspector init(Mode m, ObjectInspector[] parameters) throws HiveException {
      assert (parameters.length == 1);
      super.init(m, parameters);
      result = new DoubleWritable(0);
      inputOI = (PrimitiveObjectInspector) parameters[0];
      return PrimitiveObjectInspectorFactory.writableDoubleObjectInspector;
    }

    /** class for storing double sum value. */
    static class SumDoubleAgg implements AggregationBuffer {
      boolean empty;
      double sum;
    }

    @Override
    public AggregationBuffer getNewAggregationBuffer() throws HiveException {
      SumDoubleAgg result = new SumDoubleAgg();
      reset(result);
      return result;
    }

    @Override
    public void reset(AggregationBuffer agg) throws HiveException {
      SumDoubleAgg myagg = (SumDoubleAgg) agg;
      myagg.empty = true;
      myagg.sum = 0;
    }

    boolean warned = false;

    @Override
    public void iterate(AggregationBuffer agg, Object[] parameters) throws HiveException {
      assert (parameters.length == 1);
      try {
        merge(agg, parameters[0]);
      } catch (NumberFormatException e) {
        if (!warned) {
          warned = true;
          LOG.warn(getClass().getSimpleName() + " "
              + StringUtils.stringifyException(e));
          LOG
              .warn(getClass().getSimpleName()
              + " ignoring similar exceptions.");
        }
      }
    }

    @Override
    public Object terminatePartial(AggregationBuffer agg) throws HiveException {
      return terminate(agg);
    }

    @Override
    public void merge(AggregationBuffer agg, Object partial) throws HiveException {
      if (partial != null) {
        SumDoubleAgg myagg = (SumDoubleAgg) agg;
        myagg.empty = false;
        myagg.sum += PrimitiveObjectInspectorUtils.getDouble(partial, inputOI);
      }
    }

    @Override
    public Object terminate(AggregationBuffer agg) throws HiveException {
      SumDoubleAgg myagg = (SumDoubleAgg) agg;
      if (myagg.empty) {
        return null;
      }
      result.set(myagg.sum);
      return result;
    }

  }

  /**
   * GenericUDAFSumLong.
   *
   */
  public static class GenericUDAFSumLong extends GenericUDAFEvaluator {
    private PrimitiveObjectInspector inputOI;
    private LongWritable result;

    @Override
    public ObjectInspector init(Mode m, ObjectInspector[] parameters) throws HiveException {
      assert (parameters.length == 1);
      super.init(m, parameters);
      result = new LongWritable(0);
      inputOI = (PrimitiveObjectInspector) parameters[0];
      return PrimitiveObjectInspectorFactory.writableLongObjectInspector;
    }

    /** class for storing double sum value. */
    static class SumLongAgg implements AggregationBuffer {
      boolean empty;
      long sum;
    }

    @Override
    public AggregationBuffer getNewAggregationBuffer() throws HiveException {
      SumLongAgg result = new SumLongAgg();
      reset(result);
      return result;
    }

    @Override
    public void reset(AggregationBuffer agg) throws HiveException {
      SumLongAgg myagg = (SumLongAgg) agg;
      myagg.empty = true;
      myagg.sum = 0;
    }

    private boolean warned = false;

    @Override
    public void iterate(AggregationBuffer agg, Object[] parameters) throws HiveException {
      assert (parameters.length == 1);
      try {
        merge(agg, parameters[0]);
      } catch (NumberFormatException e) {
        if (!warned) {
          warned = true;
          LOG.warn(getClass().getSimpleName() + " "
              + StringUtils.stringifyException(e));
        }
      }
    }

    @Override
    public Object terminatePartial(AggregationBuffer agg) throws HiveException {
      return terminate(agg);
    }

    @Override
    public void merge(AggregationBuffer agg, Object partial) throws HiveException {
      if (partial != null) {
        SumLongAgg myagg = (SumLongAgg) agg;
        myagg.sum += PrimitiveObjectInspectorUtils.getLong(partial, inputOI);
        myagg.empty = false;
      }
    }

    @Override
    public Object terminate(AggregationBuffer agg) throws HiveException {
      SumLongAgg myagg = (SumLongAgg) agg;
      if (myagg.empty) {
        return null;
      }
      result.set(myagg.sum);
      return result;
    }

  }

}
