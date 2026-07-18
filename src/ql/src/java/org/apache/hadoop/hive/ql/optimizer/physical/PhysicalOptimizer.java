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


package org.apache.hadoop.hive.ql.optimizer.physical;

import java.util.ArrayList;
import java.util.List;

import org.apache.hadoop.hive.conf.HiveConf;
import org.apache.hadoop.hive.ql.metadata.HiveException;
import org.apache.hadoop.hive.ql.parse.SemanticException;

/**
 * A hierarchy physical optimizer, which contains a list of
 * PhysicalPlanResolver. Each resolver has its own set of optimization rule.
 */
public class PhysicalOptimizer {
  private PhysicalContext pctx;
  private List<PhysicalPlanResolver> resolvers;

  public PhysicalOptimizer(PhysicalContext pctx, HiveConf hiveConf) {
    super();
    this.pctx = pctx;
    initialize(hiveConf);
  }

  /**
   * create the list of physical plan resolvers.
   *
   * @param hiveConf
   */
  private void initialize(HiveConf hiveConf) {
    resolvers = new ArrayList<PhysicalPlanResolver>();
    if (hiveConf.getBoolVar(HiveConf.ConfVars.HIVESKEWJOIN)) {
      resolvers.add(new SkewJoinResolver());
    }
    if (hiveConf.getBoolVar(HiveConf.ConfVars.HIVECONVERTJOIN)) {
      resolvers.add(new CommonJoinResolver());
    }
    resolvers.add(new MapJoinResolver());
  }

  /**
   * invoke all the resolvers one-by-one, and alter the physical plan.
   *
   * @return PhysicalContext
   * @throws HiveException
   */
  public PhysicalContext optimize() throws SemanticException {
    for (PhysicalPlanResolver r : resolvers) {
      pctx = r.resolve(pctx);
    }
    return pctx;
  }

}
