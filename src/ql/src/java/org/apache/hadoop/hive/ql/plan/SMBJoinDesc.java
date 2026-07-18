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
import java.util.HashMap;

@Explain(displayName = "Sorted Merge Bucket Map Join Operator")
public class SMBJoinDesc extends MapJoinDesc implements Serializable {

  private static final long serialVersionUID = 1L;
  
  private MapredLocalWork localWork;
  
  //keep a mapping from tag to the fetch operator alias 
  private HashMap<Byte, String> tagToAlias;

  public SMBJoinDesc(MapJoinDesc conf) {
    super(conf);
  }

  public SMBJoinDesc() {
  }

  public MapredLocalWork getLocalWork() {
    return localWork;
  }

  public void setLocalWork(MapredLocalWork localWork) {
    this.localWork = localWork;
  }

  public HashMap<Byte, String> getTagToAlias() {
    return tagToAlias;
  }

  public void setTagToAlias(HashMap<Byte, String> tagToAlias) {
    this.tagToAlias = tagToAlias;
  }
  
}
