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


package org.apache.hadoop.hive.hbase;

import org.apache.hadoop.hive.ql.QTestUtil;

/**
 * HBaseQTestUtil initializes HBase-specific test fixtures.
 */
public class HBaseQTestUtil extends QTestUtil {
  public HBaseQTestUtil(
    String outDir, String logDir, boolean miniMr, HBaseTestSetup setup)
    throws Exception {

    super(outDir, logDir, miniMr, null);
    setup.preTest(conf);
    super.init();
  }

  public void init() throws Exception {
    // defer
  }
}
