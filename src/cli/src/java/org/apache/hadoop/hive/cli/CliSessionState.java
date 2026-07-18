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


package org.apache.hadoop.hive.cli;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.apache.hadoop.hive.conf.HiveConf;
import org.apache.hadoop.hive.ql.session.SessionState;

/**
 * CliSessionState.
 * 
 */
public class CliSessionState extends SessionState {
  /**
   * -e option if any that the session has been invoked with.
   */
  public String execString;

  /**
   * -f option if any that the session has been invoked with.
   */
  public String fileName;

  /**
   * properties set from -hiveconf via cmdline.
   */
  public Properties cmdProperties = new Properties();

  /**
   * -i option if any that the session has been invoked with.
   */
  public List<String> initFiles = new ArrayList<String>();

  public CliSessionState() {
    super();
  }

  public CliSessionState(HiveConf conf) {
    super(conf);
  }

}
