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

/**
 * Interface that encapsulates the evaluation logic of a UDAF. One evaluator is
 * needed for every overloaded form of a UDAF .e.g max and min UDAFs would have
 * evaluators for integer, string and other types. On the other hand avg would
 * have an evaluator only for the double type.
 */
public interface UDAFEvaluator {

  /**
   * Initializer. Initializes the state for the evaluator.
   */
  void init();
}
