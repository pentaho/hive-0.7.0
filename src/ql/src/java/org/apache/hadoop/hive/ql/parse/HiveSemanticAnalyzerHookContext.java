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


package org.apache.hadoop.hive.ql.parse;

import org.apache.hadoop.conf.Configurable;
import org.apache.hadoop.hive.ql.metadata.Hive;
import org.apache.hadoop.hive.ql.metadata.HiveException;

/**
 * Context information provided by Hive to implementations of
 * HiveSemanticAnalyzerHook.
 */
public interface HiveSemanticAnalyzerHookContext extends Configurable{
  /**
   * @return the Hive db instance; hook implementations can use this for
   * purposes such as getting configuration information or making metastore calls
   */
  public Hive getHive() throws HiveException;
}
