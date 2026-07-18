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
 * CopyWork.
 *
 */
@Explain(displayName = "Copy")
public class CopyWork implements Serializable {
  private static final long serialVersionUID = 1L;
  private String fromPath;
  private String toPath;

  public CopyWork() {
  }

  public CopyWork(final String fromPath, final String toPath) {
    this.fromPath = fromPath;
    this.toPath = toPath;
  }

  @Explain(displayName = "source")
  public String getFromPath() {
    return fromPath;
  }

  public void setFromPath(final String fromPath) {
    this.fromPath = fromPath;
  }

  @Explain(displayName = "destination")
  public String getToPath() {
    return toPath;
  }

  public void setToPath(final String toPath) {
    this.toPath = toPath;
  }
}
