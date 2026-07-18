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

import org.apache.hadoop.hive.ql.parse.ParseContext;
import org.apache.hadoop.hive.ql.parse.SemanticException;

/**
 * Optimizer interface. All the rule-based optimizations implement this
 * interface. All the transformations are invoked sequentially. They take the
 * current parse context (which contains the operator tree among other things),
 * perform all the optimizations, and then return the updated parse context.
 */
public interface Transform {
  /**
   * All transformation steps implement this interface.
   * 
   * @param pctx
   *          input parse context
   * @return ParseContext
   * @throws SemanticException
   */
  ParseContext transform(ParseContext pctx) throws SemanticException;
}
