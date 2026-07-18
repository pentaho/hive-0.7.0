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

import org.apache.hadoop.hive.metastore.api.PrincipalType;

@Explain(displayName = "Grant")
public class GrantDesc extends DDLDesc implements Serializable, Cloneable {

  private static final long serialVersionUID = 1L;

  private List<PrivilegeDesc> privileges;

  private List<PrincipalDesc> principals;

  private boolean grantOption;
  
  private String grantor;
  
  private PrincipalType grantorType;

  private PrivilegeObjectDesc privilegeSubjectDesc;

  public GrantDesc(PrivilegeObjectDesc privilegeSubject,
      List<PrivilegeDesc> privilegeDesc, List<PrincipalDesc> principalDesc,
      String grantor, PrincipalType grantorType, boolean grantOption) {
    super();
    this.privilegeSubjectDesc = privilegeSubject;
    this.privileges = privilegeDesc;
    this.principals = principalDesc;
    this.grantor = grantor;
    this.grantorType = grantorType;
    this.grantOption = grantOption;
  }

  /**
   * @return privileges
   */
  @Explain(displayName = "Privileges")
  public List<PrivilegeDesc> getPrivileges() {
    return privileges;
  }

  /**
   * @param privileges
   */
  public void setPrivileges(List<PrivilegeDesc> privileges) {
    this.privileges = privileges;
  }

  /**
   * @return principals 
   */
  @Explain(displayName = "Principals")
  public List<PrincipalDesc> getPrincipals() {
    return principals;
  }

  /**
   * @param principals
   */
  public void setPrincipals(List<PrincipalDesc> principals) {
    this.principals = principals;
  }

  /**
   * @return grant option
   */
  @Explain(displayName = "grant option")
  public boolean isGrantOption() {
    return grantOption;
  }

  /**
   * @param grantOption
   */
  public void setGrantOption(boolean grantOption) {
    this.grantOption = grantOption;
  }

  /**
   * @return privilege subject
   */
  @Explain(displayName="privilege subject")
  public PrivilegeObjectDesc getPrivilegeSubjectDesc() {
    return privilegeSubjectDesc;
  }

  /**
   * @param privilegeSubjectDesc
   */
  public void setPrivilegeSubjectDesc(PrivilegeObjectDesc privilegeSubjectDesc) {
    this.privilegeSubjectDesc = privilegeSubjectDesc;
  }

  public String getGrantor() {
    return grantor;
  }

  public void setGrantor(String grantor) {
    this.grantor = grantor;
  }

  public PrincipalType getGrantorType() {
    return grantorType;
  }

  public void setGrantorType(PrincipalType grantorType) {
    this.grantorType = grantorType;
  }

}
