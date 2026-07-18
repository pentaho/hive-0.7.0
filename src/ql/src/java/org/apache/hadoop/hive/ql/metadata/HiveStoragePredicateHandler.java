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


package org.apache.hadoop.hive.ql.metadata;

import org.apache.hadoop.mapred.JobConf;
import org.apache.hadoop.hive.ql.plan.ExprNodeDesc;
import org.apache.hadoop.hive.serde2.Deserializer;

/**
 * HiveStoragePredicateHandler is an optional companion to {@link
 * HiveStorageHandler}; it should only be implemented by handlers which
 * support decomposition of predicates being pushed down into table scans.
 */
public interface HiveStoragePredicateHandler {

  /**
   * Gives the storage handler a chance to decompose a predicate.  The storage
   * handler should analyze the predicate and return the portion of it which
   * cannot be evaluated during table access.  For example, if the original
   * predicate is <code>x = 2 AND upper(y)='YUM'</code>, the storage handler
   * might be able to handle <code>x = 2</code> but leave the "residual"
   * <code>upper(y)='YUM'</code> for Hive to deal with.  The breakdown
   * need not be non-overlapping; for example, given the
   * predicate <code>x LIKE 'a%b'</code>, the storage handler might
   * be able to evaluate the prefix search <code>x LIKE 'a%'</code>, leaving
   * <code>x LIKE '%b'</code> as the residual.
   *
   * @param jobConf contains a job configuration matching the one that
   * will later be passed to getRecordReader and getSplits
   *
   * @param deserializer deserializer which will be used when
   * fetching rows
   *
   * @param predicate predicate to be decomposed
   *
   * @return decomposed form of predicate, or null if no pushdown is
   * possible at all
   */
  public DecomposedPredicate decomposePredicate(
    JobConf jobConf,
    Deserializer deserializer,
    ExprNodeDesc predicate);

  /**
   * Struct class for returning multiple values from decomposePredicate.
   */
  public static class DecomposedPredicate {
    /**
     * Portion of predicate to be evaluated by storage handler.  Hive
     * will pass this into the storage handler's input format.
     */
    public ExprNodeDesc pushedPredicate;

    /**
     * Portion of predicate to be post-evaluated by Hive for any rows
     * which are returned by storage handler.
     */
    public ExprNodeDesc residualPredicate;
  }
}
