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


package org.apache.hadoop.hive.ql.security.authorization;

import java.util.EnumSet;

/**
 * PrivilegeScope describes a hive defined privilege's scope
 * (global/database/table/column). For example some hive privileges are
 * db-level only, some are global, and some are table only.
 */
public enum PrivilegeScope {
  
  USER_LEVEL_SCOPE((short) 0x01), 
  DB_LEVEL_SCOPE((short) 0x02), 
  TABLE_LEVEL_SCOPE((short) 0x04), 
  COLUMN_LEVEL_SCOPE((short) 0x08);

  private short mode;

  private PrivilegeScope(short mode) {
    this.mode = mode;
  }

  public short getMode() {
    return mode;
  }

  public void setMode(short mode) {
    this.mode = mode;
  }
  
  public static EnumSet<PrivilegeScope> ALLSCOPE = EnumSet.of(
      PrivilegeScope.USER_LEVEL_SCOPE, PrivilegeScope.DB_LEVEL_SCOPE,
      PrivilegeScope.TABLE_LEVEL_SCOPE, PrivilegeScope.COLUMN_LEVEL_SCOPE);

  public static EnumSet<PrivilegeScope> ALLSCOPE_EXCEPT_COLUMN = EnumSet.of(
      PrivilegeScope.USER_LEVEL_SCOPE, PrivilegeScope.DB_LEVEL_SCOPE,
      PrivilegeScope.TABLE_LEVEL_SCOPE);

}
