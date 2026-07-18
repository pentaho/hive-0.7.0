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

/**
 *
 * ExecuteWithHookContext is a new interface that the Pre/Post Execute Hook can run with the HookContext.
 *
 */

public interface ExecuteWithHookContext extends Hook {

  /**
   *
   * @param hookContext
   *     The hook context passed to each hooks.
   * @throws Exception
   */
  void run(HookContext hookContext) throws Exception;

}
