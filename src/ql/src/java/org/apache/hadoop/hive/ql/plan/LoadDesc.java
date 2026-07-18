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
 * LoadDesc.
 *
 */
public class LoadDesc implements Serializable {
  private static final long serialVersionUID = 1L;
  private String sourceDir;

  public LoadDesc() {
  }

  public LoadDesc(final String sourceDir) {

    this.sourceDir = sourceDir;
  }

  @Explain(displayName = "source", normalExplain = false)
  public String getSourceDir() {
    return sourceDir;
  }

  public void setSourceDir(final String source) {
    sourceDir = source;
  }
}
