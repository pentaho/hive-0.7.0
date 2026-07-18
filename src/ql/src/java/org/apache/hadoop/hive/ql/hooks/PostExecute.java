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

import java.util.Set;

import org.apache.hadoop.hive.ql.session.SessionState;
import org.apache.hadoop.security.UserGroupInformation;

/**
 * The post execute hook interface. A list of such hooks can be configured to be
 * called after compilation and before execution.
 */
public interface PostExecute extends Hook {

  /**
   * The run command that is called just before the execution of the query.
   *
   * @param sess
   *          The session state.
   * @param inputs
   *          The set of input tables and partitions.
   * @param outputs
   *          The set of output tables, partitions, local and hdfs directories.
   * @param lInfo
   *           The column level lineage information.
   * @param ugi
   *          The user group security information.
   */
  @Deprecated
  void run(SessionState sess, Set<ReadEntity> inputs,
      Set<WriteEntity> outputs, LineageInfo lInfo,
      UserGroupInformation ugi) throws Exception;

}
