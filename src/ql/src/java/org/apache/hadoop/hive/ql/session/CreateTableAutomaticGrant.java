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


package org.apache.hadoop.hive.ql.session;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.hadoop.hive.conf.HiveConf;
import org.apache.hadoop.hive.metastore.api.PrincipalType;
import org.apache.hadoop.hive.metastore.api.PrivilegeGrantInfo;
import org.apache.hadoop.hive.ql.metadata.HiveException;
import org.apache.hadoop.hive.ql.security.authorization.Privilege;
import org.apache.hadoop.hive.ql.security.authorization.PrivilegeRegistry;

public class CreateTableAutomaticGrant {
  private Map<String, List<PrivilegeGrantInfo>> userGrants;
  private Map<String, List<PrivilegeGrantInfo>> groupGrants;
  private Map<String, List<PrivilegeGrantInfo>> roleGrants;

  public static CreateTableAutomaticGrant create(HiveConf conf)
      throws HiveException {
    CreateTableAutomaticGrant grants = new CreateTableAutomaticGrant();
    grants.userGrants = getGrantMap(HiveConf.getVar(conf,
        HiveConf.ConfVars.HIVE_AUTHORIZATION_TABLE_USER_GRANTS));
    grants.groupGrants = getGrantMap(HiveConf.getVar(conf,
        HiveConf.ConfVars.HIVE_AUTHORIZATION_TABLE_GROUP_GRANTS));
    grants.roleGrants = getGrantMap(HiveConf.getVar(conf,
        HiveConf.ConfVars.HIVE_AUTHORIZATION_TABLE_ROLE_GRANTS));
    
    String grantor = null;
    if (SessionState.get() != null
        && SessionState.get().getAuthenticator() != null) {
      grantor = SessionState.get().getAuthenticator().getUserName();
      List<PrivilegeGrantInfo> ownerGrant = getGrantorInfoList(HiveConf.getVar(conf,
          HiveConf.ConfVars.HIVE_AUTHORIZATION_TABLE_OWNER_GRANTS));
      if(ownerGrant != null) {
        if (grants.userGrants == null) {
          grants.userGrants = new HashMap<String, List<PrivilegeGrantInfo>>();
        }
        grants.userGrants.put(grantor, ownerGrant);
      }
    }
    return grants;
  }

  private static Map<String, List<PrivilegeGrantInfo>> getGrantMap(String grantMapStr)
      throws HiveException {
    if (grantMapStr != null && !grantMapStr.trim().equals("")) {
      String[] grantArrayStr = grantMapStr.split(";");
      Map<String, List<PrivilegeGrantInfo>> grantsMap = new HashMap<String, List<PrivilegeGrantInfo>>();
      for (String grantStr : grantArrayStr) {
        String[] principalListAndPrivList = grantStr.split(":");
        if (principalListAndPrivList.length != 2
            || principalListAndPrivList[0] == null
            || principalListAndPrivList[0].trim().equals("")) {
          throw new HiveException(
              "Can not understand the config privilege definition " + grantStr);
        }
        String userList = principalListAndPrivList[0];
        String privList = principalListAndPrivList[1];
        List<PrivilegeGrantInfo> grantInfoList = getGrantorInfoList(privList);
        if(grantInfoList != null) {
          String[] users = userList.split(",");
          for (String user : users) {
            grantsMap.put(user, grantInfoList);
          }
        }
      }
      return grantsMap;
    }
    return null;
  }

  private static List<PrivilegeGrantInfo> getGrantorInfoList(String privList)
      throws HiveException {
    if (privList == null || privList.trim().equals("")) {
      return null;
    }
    checkPrivilege(privList);
    String[] grantArray = privList.split(",");
    List<PrivilegeGrantInfo> grantInfoList = new ArrayList<PrivilegeGrantInfo>();
    String grantor = null;
    if (SessionState.get().getAuthenticator() != null) {
      grantor = SessionState.get().getAuthenticator().getUserName();  
    }
    for (String grant : grantArray) {
      grantInfoList.add(new PrivilegeGrantInfo(grant, -1, grantor,
          PrincipalType.USER, true));
    }
    return grantInfoList;
  }

  private static void checkPrivilege(String ownerGrantsInConfig)
      throws HiveException {
    String[] ownerGrantArray = ownerGrantsInConfig.split(",");
    // verify the config
    for (String ownerGrant : ownerGrantArray) {
      Privilege prive = PrivilegeRegistry.getPrivilege(ownerGrant);
      if (prive == null) {
        throw new HiveException("Privilege " + ownerGrant + " is not found.");
      }
    }
  }

  public Map<String, List<PrivilegeGrantInfo>> getUserGrants() {
    return userGrants;
  }

  public Map<String, List<PrivilegeGrantInfo>> getGroupGrants() {
    return groupGrants;
  }

  public Map<String, List<PrivilegeGrantInfo>> getRoleGrants() {
    return roleGrants;
  }
}
