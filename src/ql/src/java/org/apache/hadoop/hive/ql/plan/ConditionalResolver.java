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


package org.apache.hadoop.hive.ql.plan;

import java.io.Serializable;
import java.util.List;

import org.apache.hadoop.hive.conf.HiveConf;
import org.apache.hadoop.hive.ql.exec.Task;

/**
 * Conditional task resolution interface. This is invoked at run time to get the
 * task to invoke. Developers can plug in their own resolvers.
 */
public interface ConditionalResolver {
  /**
   * All conditional resolvers implement this interface.
   *
   * @param conf
   *          configuration
   * @param ctx
   *          opaque context
   * @return position of the task
   */
  List<Task<? extends Serializable>> getTasks(HiveConf conf, Object ctx);

}
