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

import org.apache.hadoop.hive.ql.metadata.HiveException;
import org.apache.hadoop.hive.serde2.objectinspector.ObjectInspector;

/**
 * ExprNodeEvaluator.
 *
 */
public abstract class ExprNodeEvaluator {

  /**
   * Initialize should be called once and only once. Return the ObjectInspector
   * for the return value, given the rowInspector.
   */
  public abstract ObjectInspector initialize(ObjectInspector rowInspector) throws HiveException;

  /**
   * Evaluate the expression given the row. This method should use the
   * rowInspector passed in from initialize to inspect the row object. The
   * return value will be inspected by the return value of initialize.
   */
  public abstract Object evaluate(Object row) throws HiveException;

}
