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

import org.apache.hadoop.hive.metastore.api.PrincipalType;

@Explain(displayName = "Principal")
public class PrincipalDesc implements Serializable, Cloneable {

  private static final long serialVersionUID = 1L;
  
  private String name;
  
  private PrincipalType type;

  public PrincipalDesc(String name, PrincipalType type) {
    super();
    this.name = name;
    this.type = type;
  }

  public PrincipalDesc() {
    super();
  }

  @Explain(displayName="name")
  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  @Explain(displayName="type")
  public PrincipalType getType() {
    return type;
  }

  public void setType(PrincipalType type) {
    this.type = type;
  }

}
