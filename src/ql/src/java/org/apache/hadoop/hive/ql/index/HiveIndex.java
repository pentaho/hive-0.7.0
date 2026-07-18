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


package org.apache.hadoop.hive.ql.index;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Holds index related constants
 */
public class HiveIndex {

  public static final Log l4j = LogFactory.getLog("HiveIndex");

  public static String INDEX_TABLE_CREATETIME = "hive.index.basetbl.dfs.lastModifiedTime";

  public static enum IndexType {
    COMPACT_SUMMARY_TABLE("compact", "org.apache.hadoop.hive.ql.index.compact.CompactIndexHandler");

    private IndexType(String indexType, String className) {
      indexTypeName = indexType;
      this.handlerClsName = className;
    }

    private final String indexTypeName;
    private final String handlerClsName;

    public String getName() {
      return indexTypeName;
    }

    public String getHandlerClsName() {
      return handlerClsName;
    }
  }

  public static IndexType getIndexType(String name) {
    IndexType[] types = IndexType.values();
    for (IndexType type : types) {
      if(type.getName().equals(name.toLowerCase())) {
        return type;
      }
    }
    return null;
  }

  public static IndexType getIndexTypeByClassName(String className) {
    IndexType[] types = IndexType.values();
    for (IndexType type : types) {
      if(type.getHandlerClsName().equals(className)) {
        return type;
      }
    }
    return null;
  }

}

