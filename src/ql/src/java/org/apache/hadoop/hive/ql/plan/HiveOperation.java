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

import org.apache.hadoop.hive.ql.security.authorization.Privilege;

public enum HiveOperation {
  
  EXPLAIN("EXPLAIN", null, null),
  LOAD("LOAD", null, new Privilege[]{Privilege.ALTER_DATA}),
  CREATEDATABASE("CREATEDATABASE", null, null),
  DROPDATABASE("DROPDATABASE", null, null),
  SWITCHDATABASE("SWITCHDATABASE", null, null),
  DROPTABLE ("DROPTABLE", null, new Privilege[]{Privilege.DROP}),
  DESCTABLE("DESCTABLE", null, null),
  DESCFUNCTION("DESCFUNCTION", null, null),
  MSCK("MSCK", null, null),
  ALTERTABLE_ADDCOLS("ALTERTABLE_ADDCOLS", new Privilege[]{Privilege.ALTER_METADATA}, null),
  ALTERTABLE_REPLACECOLS("ALTERTABLE_REPLACECOLS", new Privilege[]{Privilege.ALTER_METADATA}, null),
  ALTERTABLE_RENAMECOL("ALTERTABLE_RENAMECOL", new Privilege[]{Privilege.ALTER_METADATA}, null),
  ALTERTABLE_RENAME("ALTERTABLE_RENAME", new Privilege[]{Privilege.ALTER_METADATA}, null),
  ALTERTABLE_DROPPARTS("ALTERTABLE_DROPPARTS", new Privilege[]{Privilege.DROP}, null),
  ALTERTABLE_ADDPARTS("ALTERTABLE_ADDPARTS", new Privilege[]{Privilege.CREATE}, null),
  ALTERTABLE_TOUCH("ALTERTABLE_TOUCH", null, null),
  ALTERTABLE_ARCHIVE("ALTERTABLE_ARCHIVE", new Privilege[]{Privilege.ALTER_DATA}, null),
  ALTERTABLE_UNARCHIVE("ALTERTABLE_UNARCHIVE", new Privilege[]{Privilege.ALTER_DATA}, null),
  ALTERTABLE_PROPERTIES("ALTERTABLE_PROPERTIES", new Privilege[]{Privilege.ALTER_METADATA}, null),
  ALTERTABLE_SERIALIZER("ALTERTABLE_SERIALIZER", new Privilege[]{Privilege.ALTER_METADATA}, null),
  ALTERTABLE_SERDEPROPERTIES("ALTERTABLE_SERDEPROPERTIES", new Privilege[]{Privilege.ALTER_METADATA}, null),
  ALTERTABLE_CLUSTER_SORT("ALTERTABLE_CLUSTER_SORT", new Privilege[]{Privilege.ALTER_METADATA}, null),
  SHOWDATABASES("SHOWDATABASES", new Privilege[]{Privilege.SHOW_DATABASE}, null),
  SHOWTABLES("SHOWTABLES", null, null),
  SHOW_TABLESTATUS("SHOW_TABLESTATUS", null, null),
  SHOWFUNCTIONS("SHOWFUNCTIONS", null, null),
  SHOWINDEXES("SHOWINDEXES", null, null),
  SHOWPARTITIONS("SHOWPARTITIONS", null, null),
  SHOWLOCKS("SHOWLOCKS", null, null),
  CREATEFUNCTION("CREATEFUNCTION", null, null),
  DROPFUNCTION("DROPFUNCTION", null, null),
  CREATEVIEW("CREATEVIEW", null, null),
  DROPVIEW("DROPVIEW", null, null),
  CREATEINDEX("CREATEINDEX", null, null),
  DROPINDEX("DROPINDEX", null, null),
  ALTERINDEX_REBUILD("ALTERINDEX_REBUILD", null, null),
  ALTERVIEW_PROPERTIES("ALTERVIEW_PROPERTIES", null, null),
  LOCKTABLE("LOCKTABLE",  new Privilege[]{Privilege.LOCK}, null),
  UNLOCKTABLE("UNLOCKTABLE",  new Privilege[]{Privilege.LOCK}, null),
  CREATEROLE("CREATEROLE", null, null),
  DROPROLE("DROPROLE", null, null),
  GRANT_PRIVILEGE("GRANT_PRIVILEGE", null, null),
  REVOKE_PRIVILEGE("REVOKE_PRIVILEGE", null, null),
  SHOW_GRANT("SHOW_GRANT", null, null),
  GRANT_ROLE("GRANT_ROLE", null, null),
  REVOKE_ROLE("REVOKE_ROLE", null, null),
  SHOW_ROLE_GRANT("SHOW_ROLE_GRANT", null, null),
  ALTERTABLE_PROTECTMODE("ALTERTABLE_PROTECTMODE", new Privilege[]{Privilege.ALTER_METADATA}, null),
  ALTERPARTITION_PROTECTMODE("ALTERPARTITION_PROTECTMODE", new Privilege[]{Privilege.ALTER_METADATA}, null),
  ALTERTABLE_FILEFORMAT("ALTERTABLE_FILEFORMAT", new Privilege[]{Privilege.ALTER_METADATA}, null),
  ALTERPARTITION_FILEFORMAT("ALTERPARTITION_FILEFORMAT", new Privilege[]{Privilege.ALTER_METADATA}, null),
  ALTERTABLE_LOCATION("ALTERTABLE_LOCATION", new Privilege[]{Privilege.ALTER_DATA}, null),
  ALTERPARTITION_LOCATION("ALTERPARTITION_LOCATION", new Privilege[]{Privilege.ALTER_DATA}, null),
  CREATETABLE("CREATETABLE", null, new Privilege[]{Privilege.CREATE}),
  CREATETABLE_AS_SELECT("CREATETABLE_AS_SELECT", new Privilege[]{Privilege.SELECT}, new Privilege[]{Privilege.CREATE}),
  QUERY("QUERY", new Privilege[]{Privilege.SELECT}, new Privilege[]{Privilege.ALTER_DATA, Privilege.CREATE}), 
  ALTERINDEX_PROPS("ALTERINDEX_PROPS",null, null), 
  ALTERDATABASE("ALTERDATABASE", null, null), 
  DESCDATABASE("DESCDATABASE", null, null),
  ;

  private String operationName;
  
  private Privilege[] inputRequiredPrivileges;
  
  private Privilege[] outputRequiredPrivileges;
  
  public Privilege[] getInputRequiredPrivileges() {
    return inputRequiredPrivileges;
  }

  public Privilege[] getOutputRequiredPrivileges() {
    return outputRequiredPrivileges;
  }

  public String getOperationName() {
    return operationName;
  }

  private HiveOperation(String operationName,
      Privilege[] inputRequiredPrivileges, Privilege[] outputRequiredPrivileges) {
    this.operationName = operationName;
    this.inputRequiredPrivileges = inputRequiredPrivileges;
    this.outputRequiredPrivileges = outputRequiredPrivileges;
  }
  
  public static class PrivilegeAgreement {
    
    private Privilege[] inputUserLevelRequiredPriv;
    private Privilege[] inputDBLevelRequiredPriv;
    private Privilege[] inputTableLevelRequiredPriv;
    private Privilege[] inputColumnLevelRequiredPriv;
    private Privilege[] outputUserLevelRequiredPriv;
    private Privilege[] outputDBLevelRequiredPriv;
    private Privilege[] outputTableLevelRequiredPriv;
    private Privilege[] outputColumnLevelRequiredPriv;
    
    public PrivilegeAgreement putUserLevelRequiredPriv(
        Privilege[] inputUserLevelRequiredPriv,
        Privilege[] outputUserLevelRequiredPriv) {
      this.inputUserLevelRequiredPriv = inputUserLevelRequiredPriv;
      this.outputUserLevelRequiredPriv = outputUserLevelRequiredPriv;
      return this;
    }

    public PrivilegeAgreement putDBLevelRequiredPriv(
        Privilege[] inputDBLevelRequiredPriv,
        Privilege[] outputDBLevelRequiredPriv) {
      this.inputDBLevelRequiredPriv = inputDBLevelRequiredPriv;
      this.outputDBLevelRequiredPriv = outputDBLevelRequiredPriv;
      return this;
    }
    
    public PrivilegeAgreement putTableLevelRequiredPriv(
        Privilege[] inputTableLevelRequiredPriv,
        Privilege[] outputTableLevelRequiredPriv) {
      this.inputTableLevelRequiredPriv = inputTableLevelRequiredPriv;
      this.outputTableLevelRequiredPriv = outputTableLevelRequiredPriv;
      return this;
    }

    public PrivilegeAgreement putColumnLevelRequiredPriv(
        Privilege[] inputColumnLevelPriv, Privilege[] outputColumnLevelPriv) {
      this.inputColumnLevelRequiredPriv = inputColumnLevelPriv;
      this.outputColumnLevelRequiredPriv = outputColumnLevelPriv;
      return this;
    }

    public Privilege[] getInputUserLevelRequiredPriv() {
      return inputUserLevelRequiredPriv;
    }

    public Privilege[] getInputDBLevelRequiredPriv() {
      return inputDBLevelRequiredPriv;
    }

    public Privilege[] getInputTableLevelRequiredPriv() {
      return inputTableLevelRequiredPriv;
    }

    public Privilege[] getInputColumnLevelRequiredPriv() {
      return inputColumnLevelRequiredPriv;
    }

    public Privilege[] getOutputUserLevelRequiredPriv() {
      return outputUserLevelRequiredPriv;
    }

    public Privilege[] getOutputDBLevelRequiredPriv() {
      return outputDBLevelRequiredPriv;
    }

    public Privilege[] getOutputTableLevelRequiredPriv() {
      return outputTableLevelRequiredPriv;
    }

    public Privilege[] getOutputColumnLevelRequiredPriv() {
      return outputColumnLevelRequiredPriv;
    }
  }
}
