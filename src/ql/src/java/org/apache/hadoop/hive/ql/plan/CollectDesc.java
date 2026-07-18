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
 * CollectDesc.
 *
 */
@Explain(displayName = "Collect")
public class CollectDesc implements Serializable {
  private static final long serialVersionUID = 1L;
  Integer bufferSize;

  public CollectDesc() {
  }

  public CollectDesc(final Integer bufferSize) {
    this.bufferSize = bufferSize;
  }

  public Integer getBufferSize() {
    return bufferSize;
  }

  public void setBufferSize(Integer bufferSize) {
    this.bufferSize = bufferSize;
  }
}
