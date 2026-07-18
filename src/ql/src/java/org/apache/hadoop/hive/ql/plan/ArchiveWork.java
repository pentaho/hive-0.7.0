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
import java.util.LinkedHashMap;

/**
 * ArchiveWork.
 *
 */
@Explain(displayName = "Map Reduce")
public class ArchiveWork implements Serializable {
  private static final long serialVersionUID = 1L;
  private String tableName;
  private String dbName;
  private LinkedHashMap<String, String> partSpec;
  private ArchiveActionType type;

  public static enum ArchiveActionType {
    ARCHIVE, UNARCHIVE
  };


  public ArchiveActionType getType() {
    return type;
  }

  public void setType(ArchiveActionType type) {
    this.type = type;
  }

  public ArchiveWork() {
  }

}
