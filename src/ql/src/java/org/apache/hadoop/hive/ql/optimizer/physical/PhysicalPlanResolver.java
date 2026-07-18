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

import org.apache.hadoop.hive.ql.parse.SemanticException;

/**
 * Physical plan optimization interface. Each resolver has its own set of
 * optimization rules and transformations.
 */
public interface PhysicalPlanResolver {

  /**
   * All physical plan resolvers have to implement this entry method.
   * 
   * @param pctx
   * @return
   */
  PhysicalContext resolve(PhysicalContext pctx) throws SemanticException;

}
