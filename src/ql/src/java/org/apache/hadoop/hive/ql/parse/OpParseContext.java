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
import java.io.Serializable;
/**
 * Implementation of the Operator Parse Context. It maintains the parse context
 * that may be needed by an operator. Currently, it only maintains the row
 * resolver.
 **/

public class OpParseContext implements Serializable {
  private static final long serialVersionUID = 1L;
  private RowResolver rr; // row resolver for the operator

  public OpParseContext() {
  }

  /**
   * @param rr
   *          row resolver
   */
  public OpParseContext(RowResolver rr) {
    this.rr = rr;
  }

  /**
   * @return the row resolver
   */
  public RowResolver getRowResolver() {
    return rr;
  }

  /**
   * @param rr
   *          the row resolver to set
   */
  public void setRowResolver(RowResolver rr) {
    this.rr = rr;
  }
}
