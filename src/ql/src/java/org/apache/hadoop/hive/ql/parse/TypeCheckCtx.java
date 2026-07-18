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


package org.apache.hadoop.hive.ql.parse;

import org.apache.hadoop.hive.ql.lib.NodeProcessorCtx;

/**
 * This class implements the context information that is used for typechecking
 * phase in query compilation.
 */
public class TypeCheckCtx implements NodeProcessorCtx {

  /**
   * The row resolver of the previous operator. This field is used to generate
   * expression descriptors from the expression ASTs.
   */
  private RowResolver inputRR;

  /**
   * Receives translations which will need to be applied during unparse.
   */
  private UnparseTranslator unparseTranslator;

  /**
   * Potential typecheck error reason.
   */
  private String error;

  /**
   * The node that generated the potential typecheck error
   */
  private ASTNode errorSrcNode;

  /**
   * Constructor.
   *
   * @param inputRR
   *          The input row resolver of the previous operator.
   */
  public TypeCheckCtx(RowResolver inputRR) {
    setInputRR(inputRR);
    error = null;
  }

  /**
   * @param inputRR
   *          the inputRR to set
   */
  public void setInputRR(RowResolver inputRR) {
    this.inputRR = inputRR;
  }

  /**
   * @return the inputRR
   */
  public RowResolver getInputRR() {
    return inputRR;
  }

  /**
   * @param unparseTranslator
   *          the unparseTranslator to set
   */
  public void setUnparseTranslator(UnparseTranslator unparseTranslator) {
    this.unparseTranslator = unparseTranslator;
  }

  /**
   * @return the unparseTranslator
   */
  public UnparseTranslator getUnparseTranslator() {
    return unparseTranslator;
  }

  /**
   * @param error
   *          the error to set
   *
   */
  public void setError(String error, ASTNode errorSrcNode) {
    this.error = error;
    this.errorSrcNode = errorSrcNode;
  }

  /**
   * @return the error
   */
  public String getError() {
    return error;
  }

  public ASTNode getErrorSrcNode() {
    return errorSrcNode;
  }

}
