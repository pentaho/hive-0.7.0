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
import java.util.List;

@Explain(displayName="Revoke")
public class RevokeDesc extends DDLDesc implements Serializable, Cloneable {

  private static final long serialVersionUID = 1L;

  private List<PrivilegeDesc> privileges;

  private List<PrincipalDesc> principals;

  private PrivilegeObjectDesc privilegeSubjectDesc;
  
  public RevokeDesc(){
  }

  public RevokeDesc(List<PrivilegeDesc> privileges,
      List<PrincipalDesc> principals, PrivilegeObjectDesc privilegeSubjectDesc) {
    super();
    this.privileges = privileges;
    this.principals = principals;
    this.privilegeSubjectDesc = privilegeSubjectDesc;
  }

  public List<PrivilegeDesc> getPrivileges() {
    return privileges;
  }

  public void setPrivileges(List<PrivilegeDesc> privileges) {
    this.privileges = privileges;
  }

  public List<PrincipalDesc> getPrincipals() {
    return principals;
  }

  public void setPrincipals(List<PrincipalDesc> principals) {
    this.principals = principals;
  }

  public PrivilegeObjectDesc getPrivilegeSubjectDesc() {
    return privilegeSubjectDesc;
  }

  public void setPrivilegeSubjectDesc(PrivilegeObjectDesc privilegeSubjectDesc) {
    this.privilegeSubjectDesc = privilegeSubjectDesc;
  }
  
}
