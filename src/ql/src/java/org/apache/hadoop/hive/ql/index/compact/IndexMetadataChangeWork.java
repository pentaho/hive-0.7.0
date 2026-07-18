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


package org.apache.hadoop.hive.ql.index.compact;

import java.io.Serializable;
import java.util.HashMap;

public class IndexMetadataChangeWork implements Serializable {

  private static final long serialVersionUID = 1L;

  private HashMap<String, String> partSpec;
  private String indexTbl;
  private String dbName;
  
  public IndexMetadataChangeWork() {
  }
  
  public IndexMetadataChangeWork(HashMap<String, String> partSpec,
      String indexTbl, String dbName) {
    super();
    this.partSpec = partSpec;
    this.indexTbl = indexTbl;
    this.dbName = dbName;
  }

  public HashMap<String, String> getPartSpec() {
    return partSpec;
  }

  public void setPartSpec(HashMap<String, String> partSpec) {
    this.partSpec = partSpec;
  }

  public String getIndexTbl() {
    return indexTbl;
  }

  public void setIndexTbl(String indexTbl) {
    this.indexTbl = indexTbl;
  }
  
  public String getDbName() {
    return dbName;
  }

  public void setDbName(String dbName) {
    this.dbName = dbName;
  }
  
}
