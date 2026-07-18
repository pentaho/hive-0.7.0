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


package org.apache.hadoop.hive.ql;

import java.io.File;

import junit.framework.TestCase;

/**
 * Suite for testing running of queries in multi-threaded mode.
 */
public class TestMTQueries extends TestCase {

  private final String inpDir = System
      .getProperty("ql.test.query.clientpositive.dir");
  private final String resDir = System
      .getProperty("ql.test.results.clientpositive.dir");
  private final String logDir = System.getProperty("test.log.dir")
      + "/clientpositive";

  public void testMTQueries1() throws Exception {
    String[] testNames = new String[] {"join1.q", "join2.q", "groupby1.q",
        "groupby2.q", "join3.q", "input1.q", "input19.q"};
    String[] logDirs = new String[testNames.length];
    String[] resDirs = new String[testNames.length];
    File[] qfiles = new File[testNames.length];
    for (int i = 0; i < resDirs.length; i++) {
      logDirs[i] = logDir;
      resDirs[i] = resDir;
      qfiles[i] = new File(inpDir, testNames[i]);
    }

    boolean success = QTestUtil.queryListRunner(qfiles, resDirs, logDirs, true, this);
    if (!success) {
      fail("One or more queries failed");
    }
  }
}
