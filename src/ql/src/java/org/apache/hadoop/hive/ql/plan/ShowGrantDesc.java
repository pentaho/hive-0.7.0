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

import java.util.List;

@Explain(displayName="show grant desc")
public class ShowGrantDesc {
  
  private PrincipalDesc principalDesc;

  private PrivilegeObjectDesc hiveObj;
  
  private List<String> columns;

  private String resFile;

  public ShowGrantDesc(){
  }
  
  public ShowGrantDesc(String resFile, PrincipalDesc principalDesc,
      PrivilegeObjectDesc subjectObj, List<String> columns) {
    this.resFile = resFile;
    this.principalDesc = principalDesc;
    this.hiveObj = subjectObj;
    this.columns = columns;
  }
  
  @Explain(displayName="principal desc")
  public PrincipalDesc getPrincipalDesc() {
    return principalDesc;
  }

  public void setPrincipalDesc(PrincipalDesc principalDesc) {
    this.principalDesc = principalDesc;
  }

  @Explain(displayName="object")
  public PrivilegeObjectDesc getHiveObj() {
    return hiveObj;
  }

  public void setHiveObj(PrivilegeObjectDesc subjectObj) {
    this.hiveObj = subjectObj;
  }
  
  public String getResFile() {
    return resFile;
  }

  public void setResFile(String resFile) {
    this.resFile = resFile;
  }
  
  public List<String> getColumns() {
    return columns;
  }

  public void setColumns(List<String> columns) {
    this.columns = columns;
  }
  
}
