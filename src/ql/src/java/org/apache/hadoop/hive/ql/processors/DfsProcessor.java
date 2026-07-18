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

import java.io.PrintStream;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FsShell;
import org.apache.hadoop.hive.ql.parse.VariableSubstitution;
import org.apache.hadoop.hive.ql.session.SessionState;
import org.apache.hadoop.hive.ql.session.SessionState.LogHelper;

/**
 * DfsProcessor.
 *
 */
public class DfsProcessor implements CommandProcessor {

  public static final Log LOG = LogFactory.getLog(DfsProcessor.class.getName());
  public static final LogHelper console = new LogHelper(LOG);

  private final FsShell dfs;

  public DfsProcessor(Configuration conf) {
    dfs = new FsShell(conf);
  }

  public void init() {
  }

  public CommandProcessorResponse run(String command) {


    try {
      SessionState ss = SessionState.get();
      command = new VariableSubstitution().substitute(ss.getConf(),command);

      String[] tokens = command.split("\\s+");
      PrintStream oldOut = System.out;

      if (ss != null && ss.out != null) {
        System.setOut(ss.out);
      }

      int ret = dfs.run(tokens);
      if (ret != 0) {
        console.printError("Command failed with exit code = " + ret);
      }

      System.setOut(oldOut);
      return new CommandProcessorResponse(ret);

    } catch (Exception e) {
      console.printError("Exception raised from DFSShell.run "
          + e.getLocalizedMessage(), org.apache.hadoop.util.StringUtils
          .stringifyException(e));
      return new CommandProcessorResponse(1);
    }
  }

}
