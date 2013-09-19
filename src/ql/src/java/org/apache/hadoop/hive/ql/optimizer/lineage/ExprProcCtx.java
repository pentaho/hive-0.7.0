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

package org.apache.hadoop.hive.ql.optimizer.lineage;

import java.io.Serializable;

import org.apache.hadoop.hive.ql.exec.Operator;
import org.apache.hadoop.hive.ql.lib.NodeProcessorCtx;

/**
 * The processor context for the lineage information. This contains the
 * lineage context and the column info and operator information that is
 * being used for the current expression.
 */
public class ExprProcCtx implements NodeProcessorCtx {

  /**
   * The lineage context that is being populated.
   */
  private LineageCtx lctx;
  
  /**
   * The input operator in case the current operator is not a leaf.
   */
  private Operator<? extends Serializable> inpOp;
  
  /**
   * Constructor.
   * 
   * @param lctx The lineage context thatcontains the dependencies for the inputs.
   * @param inpOp The input operator to the current operator.
   */
  public ExprProcCtx(LineageCtx lctx,
      Operator<? extends Serializable> inpOp) {
    this.lctx = lctx;
    this.inpOp = inpOp;
  }
  
  /**
   * Gets the lineage context.
   * 
   * @return LineageCtx The lineage context.
   */
  public LineageCtx getLineageCtx() {
    return lctx;
  }
 
  /**
   * Gets the input operator.
   * 
   * @return Operator The input operator - this is null in case the current 
   * operator is a leaf.
   */
  public Operator<? extends Serializable> getInputOperator() {
    return inpOp;
  }
}
