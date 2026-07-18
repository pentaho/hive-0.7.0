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

import java.util.HashMap;

import org.apache.hadoop.hive.ql.security.authorization.Privilege.PrivilegeType;

/**
 * PrivilegeRegistry is used to do privilege lookups. Given a privilege name, it
 * will return the Privilege object.
 */
public class PrivilegeRegistry {

  protected static HashMap<PrivilegeType, Privilege> Registry = new HashMap<PrivilegeType, Privilege>();

  static {
    Registry.put(Privilege.ALL.getPriv(), Privilege.ALL);
    Registry.put(Privilege.ALTER_DATA.getPriv(), Privilege.ALTER_DATA);
    Registry.put(Privilege.ALTER_METADATA.getPriv(), Privilege.ALTER_METADATA);
    Registry.put(Privilege.CREATE.getPriv(), Privilege.CREATE);
    Registry.put(Privilege.DROP.getPriv(), Privilege.DROP);
    Registry.put(Privilege.INDEX.getPriv(), Privilege.INDEX);
    Registry.put(Privilege.LOCK.getPriv(), Privilege.LOCK);
    Registry.put(Privilege.SELECT.getPriv(), Privilege.SELECT);
    Registry.put(Privilege.SHOW_DATABASE.getPriv(),
        Privilege.SHOW_DATABASE);
  }

  public static Privilege getPrivilege(PrivilegeType privilegeType) {
    return Registry.get(privilegeType);
  }

  public static Privilege getPrivilege(int privilegeToken) {
    return Registry.get(Privilege.getPrivTypeByToken(privilegeToken));
  }

  public static Privilege getPrivilege(String privilegeName) {
    return Registry.get(Privilege.getPrivTypeByName(privilegeName));
  }

}
