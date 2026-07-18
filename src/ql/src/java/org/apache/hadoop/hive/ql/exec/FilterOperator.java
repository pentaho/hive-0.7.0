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

import java.io.Serializable;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hive.conf.HiveConf;
import org.apache.hadoop.hive.ql.metadata.HiveException;
import org.apache.hadoop.hive.ql.plan.FilterDesc;
import org.apache.hadoop.hive.ql.plan.api.OperatorType;
import org.apache.hadoop.hive.serde2.objectinspector.ObjectInspector;
import org.apache.hadoop.hive.serde2.objectinspector.PrimitiveObjectInspector;
import org.apache.hadoop.io.LongWritable;

/**
 * Filter operator implementation.
 **/
public class FilterOperator extends Operator<FilterDesc> implements
    Serializable {

  private static final long serialVersionUID = 1L;

  /**
   * Counter.
   *
   */
  public static enum Counter {
    FILTERED, PASSED
  }

  private final transient LongWritable filtered_count, passed_count;
  private transient ExprNodeEvaluator conditionEvaluator;
  private transient PrimitiveObjectInspector conditionInspector;
  private transient int consecutiveFails;
  transient int heartbeatInterval;

  public FilterOperator() {
    super();
    filtered_count = new LongWritable();
    passed_count = new LongWritable();
    consecutiveFails = 0;
  }

  @Override
  protected void initializeOp(Configuration hconf) throws HiveException {
    try {
      heartbeatInterval = HiveConf.getIntVar(hconf,
          HiveConf.ConfVars.HIVESENDHEARTBEAT);
      conditionEvaluator = ExprNodeEvaluatorFactory.get(conf.getPredicate());
      statsMap.put(Counter.FILTERED, filtered_count);
      statsMap.put(Counter.PASSED, passed_count);
      conditionInspector = null;
    } catch (Throwable e) {
      throw new HiveException(e);
    }
    initializeChildren(hconf);
  }

  @Override
  public void processOp(Object row, int tag) throws HiveException {
    ObjectInspector rowInspector = inputObjInspectors[tag];
    if (conditionInspector == null) {
      conditionInspector = (PrimitiveObjectInspector) conditionEvaluator
          .initialize(rowInspector);
    }
    Object condition = conditionEvaluator.evaluate(row);
    Boolean ret = (Boolean) conditionInspector
        .getPrimitiveJavaObject(condition);
    if (Boolean.TRUE.equals(ret)) {
      forward(row, rowInspector);
      passed_count.set(passed_count.get() + 1);
      consecutiveFails = 0;
    } else {
      filtered_count.set(filtered_count.get() + 1);
      consecutiveFails++;

      // In case of a lot of consecutive failures, send a heartbeat in order to
      // avoid timeout
      if (((consecutiveFails % heartbeatInterval) == 0) && (reporter != null)) {
        reporter.progress();
      }
    }
  }

  /**
   * @return the name of the operator
   */
  @Override
  public String getName() {
    return new String("FIL");
  }

  @Override
  public OperatorType getType() {
    return OperatorType.FILTER;
  }
}
