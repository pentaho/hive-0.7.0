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

import java.util.List;

import org.apache.hadoop.conf.Configurable;
import org.apache.hadoop.hive.ql.metadata.HiveException;

/**
 * HiveAuthenticationProvider is an interface for authentication. The
 * implementation should return userNames and groupNames.
 */
public interface HiveAuthenticationProvider extends Configurable{
  
  public String getUserName();
  
  public List<String> getGroupNames();
  
  public void destroy() throws HiveException;

}
