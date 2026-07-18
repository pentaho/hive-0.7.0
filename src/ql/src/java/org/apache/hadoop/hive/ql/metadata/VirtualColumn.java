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


package org.apache.hadoop.hive.ql.metadata;

import java.io.Serializable;
import java.util.HashMap;

import org.apache.hadoop.hive.serde2.typeinfo.PrimitiveTypeInfo;
import org.apache.hadoop.hive.serde2.typeinfo.TypeInfoFactory;

public class VirtualColumn implements Serializable {

  private static final long serialVersionUID = 1L;

  public static HashMap<String, VirtualColumn> registry = new HashMap<String, VirtualColumn>();
  
  public static VirtualColumn FILENAME = new VirtualColumn("INPUT__FILE__NAME", (PrimitiveTypeInfo)TypeInfoFactory.stringTypeInfo);
  public static VirtualColumn BLOCKOFFSET = new VirtualColumn("BLOCK__OFFSET__INSIDE__FILE", (PrimitiveTypeInfo)TypeInfoFactory.longTypeInfo);
  
  static {
    registry.put(FILENAME.name, FILENAME);
    registry.put(BLOCKOFFSET.name, BLOCKOFFSET);
  }
  
  private String name;
  private PrimitiveTypeInfo typeInfo;
  private boolean isHidden = true;

  public VirtualColumn() {
  }
  
  public VirtualColumn(String name, PrimitiveTypeInfo typeInfo) {
    this(name, typeInfo, true);
  }
  
  VirtualColumn(String name, PrimitiveTypeInfo typeInfo, boolean isHidden) {
    this.name = name;
    this.typeInfo = typeInfo;
    this.isHidden = isHidden;
  }
  
  public PrimitiveTypeInfo getTypeInfo() {
    return typeInfo;
  }

  public void setTypeInfo(PrimitiveTypeInfo typeInfo) {
    this.typeInfo = typeInfo;
  }

  public String getName() {
    return this.name;
  }
  
  public void setName(String name) {
    this.name = name;
  }
  
  public boolean isHidden() {
    return isHidden;
  }
  
  public boolean getIsHidden() {
    return isHidden;
  }

  public void setIsHidden(boolean isHidden) {
    this.isHidden = isHidden;
  }
  
  public boolean equals(Object o) {
    if (o == null) {
      return false;
    }
    if (this == o) {
      return true;
    }
    VirtualColumn c = (VirtualColumn) o;
    return this.name.equals(c.name)
        && this.typeInfo.getTypeName().equals(c.getTypeInfo().getTypeName());
  }

}
