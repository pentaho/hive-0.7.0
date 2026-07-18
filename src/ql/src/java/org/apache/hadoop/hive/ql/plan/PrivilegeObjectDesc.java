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

import java.util.HashMap;

@Explain(displayName="privilege subject")
public class PrivilegeObjectDesc {

  private boolean table;

  private String object;

  private HashMap<String, String> partSpec;

  public PrivilegeObjectDesc(boolean isTable, String object,
      HashMap<String, String> partSpec) {
    super();
    this.table = isTable;
    this.object = object;
    this.partSpec = partSpec;
  }

  public PrivilegeObjectDesc() {
  }

  @Explain(displayName="is table")
  public boolean getTable() {
    return table;
  }

  public void setTable(boolean isTable) {
    this.table = isTable;
  }

  @Explain(displayName="object")
  public String getObject() {
    return object;
  }

  public void setObject(String object) {
    this.object = object;
  }

  @Explain(displayName="partition spec")
  public HashMap<String, String> getPartSpec() {
    return partSpec;
  }

  public void setPartSpec(HashMap<String, String> partSpec) {
    this.partSpec = partSpec;
  }

}
