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


package org.apache.hadoop.hive.ql.security;

import java.util.Arrays;
import java.util.List;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hive.ql.metadata.HiveException;
import org.apache.hadoop.hive.shims.ShimLoader;
import org.apache.hadoop.security.UserGroupInformation;

public class HadoopDefaultAuthenticator implements HiveAuthenticationProvider {

  private String userName;
  private List<String> groupNames;
  
  private Configuration conf;

  @Override
  public List<String> getGroupNames() {
    return groupNames;
  }

  @Override
  public String getUserName() {
    return userName;
  }

  @Override
  public void setConf(Configuration conf) {
    this.conf = conf;
    UserGroupInformation ugi = null;
    try {
      ugi = ShimLoader.getHadoopShims().getUGIForConf(conf);
    } catch (Exception e) {
      throw new RuntimeException(e);
    }

    if (ugi == null) {
      throw new RuntimeException(
          "Can not initialize HadoopDefaultAuthenticator.");
    }

    this.userName = ugi.getUserName();
    if (ugi.getGroupNames() != null) {
      this.groupNames = Arrays.asList(ugi.getGroupNames());
    }
  }

  @Override
  public void destroy() throws HiveException {
    return;
  }

  @Override
  public Configuration getConf() {
    return this.conf;
  }

}
