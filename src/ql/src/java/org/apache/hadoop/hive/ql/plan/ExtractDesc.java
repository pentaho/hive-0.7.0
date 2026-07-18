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


package org.apache.hadoop.hive.ql.plan;

import java.io.Serializable;

/**
 * ExtractDesc.
 *
 */
@Explain(displayName = "Extract")
public class ExtractDesc implements Serializable {
  private static final long serialVersionUID = 1L;
  private ExprNodeDesc col;

  public ExtractDesc() {
  }

  public ExtractDesc(final ExprNodeDesc col) {
    this.col = col;
  }

  public ExprNodeDesc getCol() {
    return col;
  }

  public void setCol(final ExprNodeDesc col) {
    this.col = col;
  }
}
