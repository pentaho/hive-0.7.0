/*!
* Copyright 2010 - 2013 Pentaho Corporation.  All rights reserved.
*
* Licensed under the Apache License, Version 2.0 (the "License");
* you may not use this file except in compliance with the License.
* You may obtain a copy of the License at
*
* http://www.apache.org/licenses/LICENSE-2.0
*
* Unless required by applicable law or agreed to in writing, software
* distributed under the License is distributed on an "AS IS" BASIS,
* WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
* See the License for the specific language governing permissions and
* limitations under the License.
*
*/

package org.apache.hadoop.hive.ql.lib;

import java.util.Stack;

import org.apache.hadoop.hive.ql.parse.SemanticException;

/**
 * Base class for processing operators which is no-op. The specific processors
 * can register their own context with the dispatcher.
 */
public interface NodeProcessor {

  /**
   * Generic process for all ops that don't have specific implementations.
   * 
   * @param nd
   *          operator to process
   * @param procCtx
   *          operator processor context
   * @param nodeOutputs
   *          A variable argument list of outputs from other nodes in the walk
   * @return Object to be returned by the process call
   * @throws SemanticException
   */
  Object process(Node nd, Stack<Node> stack, NodeProcessorCtx procCtx,
      Object... nodeOutputs) throws SemanticException;
}
