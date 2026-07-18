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


package org.apache.hadoop.hive.ql.lib;

import java.util.Collection;
import java.util.HashMap;

import org.apache.hadoop.hive.ql.parse.SemanticException;

/**
 * Interface for operator graph walker.
 */
public interface GraphWalker {

  /**
   * starting point for walking.
   * 
   * @param startNodes
   *          list of starting operators
   * @param nodeOutput
   *          If this parameter is not null, the call to the function returns
   *          the map from node to objects returned by the processors.
   * @throws SemanticException
   */
  void startWalking(Collection<Node> startNodes,
      HashMap<Node, Object> nodeOutput) throws SemanticException;

}
