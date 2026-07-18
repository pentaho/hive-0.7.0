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
 * LimitDesc.
 *
 */
@Explain(displayName = "Limit")
public class LimitDesc implements Serializable {
  private static final long serialVersionUID = 1L;
  private int limit;

  public LimitDesc() {
  }

  public LimitDesc(final int limit) {
    this.limit = limit;
  }

  public int getLimit() {
    return limit;
  }

  public void setLimit(final int limit) {
    this.limit = limit;
  }

}
