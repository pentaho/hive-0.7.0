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

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hive.conf.HiveConf;
import org.apache.hadoop.hive.ql.metadata.Hive;
import org.apache.hadoop.hive.ql.metadata.HiveException;
import org.apache.hadoop.hive.ql.security.HiveAuthenticationProvider;

public abstract class HiveAuthorizationProviderBase implements
    HiveAuthorizationProvider {
  
  protected HiveAuthenticationProvider authenticator;

  protected Hive hive_db;
  
  private Configuration conf;

  public void setConf(Configuration conf) {
    this.conf = conf;
    try {
      init(conf);
    } catch (HiveException e) {
      throw new RuntimeException(e);
    }
  }

  public void init(Configuration conf) throws HiveException {
    hive_db = Hive.get(new HiveConf(conf, HiveAuthorizationProvider.class));
  }
  
  public Configuration getConf() {
    return this.conf;
  }

  public HiveAuthenticationProvider getAuthenticator() {
    return authenticator;
  }

  public void setAuthenticator(HiveAuthenticationProvider authenticator) {
    this.authenticator = authenticator;
  }

}
