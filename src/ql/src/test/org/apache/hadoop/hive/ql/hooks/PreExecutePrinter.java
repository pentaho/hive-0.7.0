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


package org.apache.hadoop.hive.ql.hooks;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

import org.apache.hadoop.hive.ql.session.SessionState;
import org.apache.hadoop.hive.ql.session.SessionState.LogHelper;
import org.apache.hadoop.security.UserGroupInformation;

/**
 * Implementation of a pre execute hook that simply prints out its parameters to
 * standard output.
 */
public class PreExecutePrinter implements ExecuteWithHookContext {

  @Override
  public void run(HookContext hookContext) throws Exception {
    SessionState ss = SessionState.get();
    Set<ReadEntity> inputs = hookContext.getInputs();
    Set<WriteEntity> outputs = hookContext.getOutputs();
    UserGroupInformation ugi = hookContext.getUgi();
    this.run(ss,inputs,outputs,ugi);
  }

  public void run(SessionState sess, Set<ReadEntity> inputs,
      Set<WriteEntity> outputs, UserGroupInformation ugi)
    throws Exception {

    LogHelper console = SessionState.getConsole();

    if (console == null) {
      return;
    }

    if (sess != null) {
      console.printError("PREHOOK: query: " + sess.getCmd().trim());
      console.printError("PREHOOK: type: " + sess.getCommandType());
    }

    printEntities(console, inputs, "PREHOOK: Input: ");
    printEntities(console, outputs, "PREHOOK: Output: ");
  }

  static void printEntities(LogHelper console, Set<?> entities, String prefix) {
    List<String> strings = new ArrayList<String>();
    for (Object o : entities) {
      strings.add(o.toString());
    }
    Collections.sort(strings);
    for (String s : strings) {
      console.printError(prefix + s);
    }
  }
}
