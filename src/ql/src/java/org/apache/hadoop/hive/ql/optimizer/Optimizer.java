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


package org.apache.hadoop.hive.ql.optimizer;

import java.util.ArrayList;
import java.util.List;

import org.apache.hadoop.hive.conf.HiveConf;
import org.apache.hadoop.hive.ql.optimizer.lineage.Generator;
import org.apache.hadoop.hive.ql.optimizer.pcr.PartitionConditionRemover;
import org.apache.hadoop.hive.ql.optimizer.ppr.PartitionPruner;
import org.apache.hadoop.hive.ql.optimizer.unionproc.UnionProcessor;
import org.apache.hadoop.hive.ql.parse.ParseContext;
import org.apache.hadoop.hive.ql.parse.SemanticException;
import org.apache.hadoop.hive.ql.ppd.PredicatePushDown;

/**
 * Implementation of the optimizer.
 */
public class Optimizer {
  private ParseContext pctx;
  private List<Transform> transformations;

  /**
   * Create the list of transformations.
   *
   * @param hiveConf
   */
  public void initialize(HiveConf hiveConf) {
    transformations = new ArrayList<Transform>();
    // Add the transformation that computes the lineage information.
    transformations.add(new Generator());
    if (HiveConf.getBoolVar(hiveConf, HiveConf.ConfVars.HIVEOPTCP)) {
      transformations.add(new ColumnPruner());
    }
    if (HiveConf.getBoolVar(hiveConf, HiveConf.ConfVars.HIVEOPTPPD)) {
      transformations.add(new PredicatePushDown());
      transformations.add(new PartitionPruner());
      transformations.add(new PartitionConditionRemover());
    }

    if (HiveConf.getBoolVar(hiveConf, HiveConf.ConfVars.HIVEOPTGROUPBY)) {
      transformations.add(new GroupByOptimizer());
    }

    transformations.add(new SamplePruner());
    transformations.add(new MapJoinProcessor());
    if (HiveConf.getBoolVar(hiveConf, HiveConf.ConfVars.HIVEOPTBUCKETMAPJOIN)) {
      transformations.add(new BucketMapJoinOptimizer());
      if(HiveConf.getBoolVar(hiveConf, HiveConf.ConfVars.HIVEOPTSORTMERGEBUCKETMAPJOIN)) {
        transformations.add(new SortedMergeBucketMapJoinOptimizer());
      }
    }
    transformations.add(new UnionProcessor());
    transformations.add(new JoinReorder());
    if(HiveConf.getBoolVar(hiveConf, HiveConf.ConfVars.HIVEOPTREDUCEDEDUPLICATION)) {
      transformations.add(new ReduceSinkDeDuplication());
    }
  }

  /**
   * Invoke all the transformations one-by-one, and alter the query plan.
   *
   * @return ParseContext
   * @throws SemanticException
   */
  public ParseContext optimize() throws SemanticException {
    for (Transform t : transformations) {
      pctx = t.transform(pctx);
    }
    return pctx;
  }

  /**
   * @return the pctx
   */
  public ParseContext getPctx() {
    return pctx;
  }

  /**
   * @param pctx
   *          the pctx to set
   */
  public void setPctx(ParseContext pctx) {
    this.pctx = pctx;
  }

}
