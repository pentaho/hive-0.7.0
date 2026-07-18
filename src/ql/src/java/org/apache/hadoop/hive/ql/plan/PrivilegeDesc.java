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

import org.apache.hadoop.hive.ql.security.authorization.Privilege;

@Explain(displayName = "Privilege")
public class PrivilegeDesc implements Serializable, Cloneable {
  private static final long serialVersionUID = 1L;
  
  private Privilege privilege;
  
  private List<String> columns;

  public PrivilegeDesc(Privilege privilege, List<String> columns) {
    super();
    this.privilege = privilege;
    this.columns = columns;
  }

  public PrivilegeDesc() {
    super();
  }

  /**
   * @return privilege definition
   */
  @Explain(displayName = "privilege")
  public Privilege getPrivilege() {
    return privilege;
  }

  /**
   * @param privilege
   */
  public void setPrivilege(Privilege privilege) {
    this.privilege = privilege;
  }

  /**
   * @return columns on which the given privilege take affect.
   */
  @Explain(displayName = "columns")
  public List<String> getColumns() {
    return columns;
  }

  /**
   * @param columns
   */
  public void setColumns(List<String> columns) {
    this.columns = columns;
  }
  
}
