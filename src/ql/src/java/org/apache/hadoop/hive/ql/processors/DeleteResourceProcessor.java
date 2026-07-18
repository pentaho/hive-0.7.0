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

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.hadoop.hive.ql.parse.VariableSubstitution;
import org.apache.hadoop.hive.ql.session.SessionState;
import org.apache.hadoop.hive.ql.session.SessionState.LogHelper;

/**
 * DeleteResourceProcessor.
 *
 */
public class DeleteResourceProcessor implements CommandProcessor {

  public static final Log LOG = LogFactory.getLog(DeleteResourceProcessor.class.getName());
  public static final LogHelper console = new LogHelper(LOG);

  public void init() {
  }

  public CommandProcessorResponse run(String command) {
    SessionState ss = SessionState.get();
    command = new VariableSubstitution().substitute(ss.getConf(),command);
    String[] tokens = command.split("\\s+");

    SessionState.ResourceType t;
    if (tokens.length < 1
        || (t = SessionState.find_resource_type(tokens[0])) == null) {
      console.printError("Usage: delete ["
          + StringUtils.join(SessionState.ResourceType.values(), "|")
          + "] <value> [<value>]*");
      return new CommandProcessorResponse(1);
    }

    if (tokens.length >= 2) {
      for (int i = 1; i < tokens.length; i++) {
        ss.delete_resource(t, tokens[i]);
      }
    } else {
      ss.delete_resource(t);
    }

    return new CommandProcessorResponse(0);
  }
}
