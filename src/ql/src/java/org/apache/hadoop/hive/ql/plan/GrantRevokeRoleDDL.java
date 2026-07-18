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

import org.apache.hadoop.hive.metastore.api.PrincipalType;

@Explain(displayName="grant or revoke roles")
public class GrantRevokeRoleDDL {
  
  private boolean grant;
  
  private List<PrincipalDesc> principalDesc;
  
  private List<String> roles;
  
  private String grantor;
  
  private PrincipalType grantorType;
  
  private boolean grantOption;
  
  public GrantRevokeRoleDDL() {
  }

  public GrantRevokeRoleDDL(boolean grant, List<String> roles,
      List<PrincipalDesc> principalDesc, String grantor,
      PrincipalType grantorType, boolean grantOption) {
    super();
    this.grant = grant;
    this.principalDesc = principalDesc;
    this.roles = roles;
    this.grantor = grantor;
    this.grantorType = grantorType;
    this.grantOption = grantOption;
  }

  /**
   * @return grant or revoke privileges
   */
  @Explain(displayName="grant (or revoke)")
  public boolean getGrant() {
    return grant;
  }

  public void setGrant(boolean grant) {
    this.grant = grant;
  }

  /**
   * @return a list of principals
   */
  @Explain(displayName="principals")
  public List<PrincipalDesc> getPrincipalDesc() {
    return principalDesc;
  }

  public void setPrincipalDesc(List<PrincipalDesc> principalDesc) {
    this.principalDesc = principalDesc;
  }

  /**
   * @return a list of roles
   */
  @Explain(displayName="roles")
  public List<String> getRoles() {
    return roles;
  }

  public void setRoles(List<String> roles) {
    this.roles = roles;
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

  public boolean isGrantOption() {
    return grantOption;
  }

  public void setGrantOption(boolean grantOption) {
    this.grantOption = grantOption;
  }  

}
