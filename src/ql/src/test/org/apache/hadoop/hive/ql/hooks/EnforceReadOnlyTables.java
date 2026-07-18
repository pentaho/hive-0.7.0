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

import static org.apache.hadoop.hive.metastore.MetaStoreUtils.DEFAULT_DATABASE_NAME;

import java.util.Set;

import org.apache.hadoop.hive.ql.QTestUtil;
import org.apache.hadoop.hive.ql.metadata.Table;
import org.apache.hadoop.hive.ql.session.SessionState;
import org.apache.hadoop.security.UserGroupInformation;

/**
 * Implementation of a pre execute hook that prevents modifications
 * of read-only tables used by the test framework
 */
public class EnforceReadOnlyTables implements ExecuteWithHookContext {

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

    for (WriteEntity w: outputs) {
      if ((w.getTyp() == WriteEntity.Type.TABLE) ||
          (w.getTyp() == WriteEntity.Type.PARTITION)) {
        Table t = w.getTable();
        if (DEFAULT_DATABASE_NAME.equalsIgnoreCase(t.getDbName())
            && QTestUtil.srcTables.contains(t.getTableName())) {
          throw new RuntimeException ("Cannot overwrite read-only table: " + t.getTableName());
        }
      }
    }
  }
}
