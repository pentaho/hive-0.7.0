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


package org.apache.hadoop.hive.ql.processors;

import static org.apache.commons.lang.StringUtils.isBlank;
import java.util.Map;
import java.util.HashMap;

import org.apache.hadoop.hive.ql.Driver;
import org.apache.hadoop.hive.ql.session.SessionState;
import org.apache.hadoop.hive.conf.HiveConf;

/**
 * CommandProcessorFactory.
 *
 */
public final class CommandProcessorFactory {

  private CommandProcessorFactory() {
    // prevent instantiation
  }

  static Map<HiveConf, Driver> mapDrivers = new HashMap<HiveConf, Driver>();
  public static CommandProcessor get(String cmd) {
    return get(cmd, null);
  }

  public static CommandProcessor get(String cmd, HiveConf conf) {
    String cmdl = cmd.toLowerCase();

    if ("set".equals(cmdl)) {
      return new SetProcessor();
    } else if ("dfs".equals(cmdl)) {
      SessionState ss = SessionState.get();
      return new DfsProcessor(ss.getConf());
    } else if ("add".equals(cmdl)) {
      return new AddResourceProcessor();
    } else if ("delete".equals(cmdl)) {
      return new DeleteResourceProcessor();
    } else if (!isBlank(cmd)) {
      if (conf == null) {
        return new Driver();
      }

      Driver drv = mapDrivers.get(conf);
      if (drv == null) {
        drv = new Driver();
        mapDrivers.put(conf, drv);
      }
      drv.init();
      return drv;
    }

    return null;
  }

  public static void clean(HiveConf conf) {
    Driver drv = mapDrivers.get(conf);
    if (drv != null) {
      drv.destroy();
    }

    mapDrivers.remove(conf);
  }
}
