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


package org.apache.hadoop.hive.metastore;

import org.apache.hadoop.hive.conf.HiveConf;
import org.apache.hadoop.hive.conf.HiveConf.ConfVars;


public class TestRemoteHiveMetaStore extends TestHiveMetaStore {
  private static final String METASTORE_PORT = "29083";
  private static boolean isServerStarted = false;

  public TestRemoteHiveMetaStore() {
    super();
    isThriftClient = true;
  }

  private static class RunMS implements Runnable {

      @Override
      public void run() {
        HiveMetaStore.main(new String[] { METASTORE_PORT });
      }

    }

  @Override
  protected void setUp() throws Exception {
    super.setUp();

    if (isServerStarted) {
      assertNotNull("Unable to connect to the MetaStore server", client);
      return;
    }

    System.out.println("Starting MetaStore Server on port " + METASTORE_PORT);
    Thread t = new Thread(new RunMS());
    t.start();
    isServerStarted = true;

    // Wait a little bit for the metastore to start. Should probably have
    // a better way of detecting if the metastore has started?
    Thread.sleep(5000);

    // hive.metastore.local should be defined in HiveConf
    hiveConf.set("hive.metastore.local", "false");
    hiveConf.setVar(HiveConf.ConfVars.METASTOREURIS, "thrift://localhost:" + METASTORE_PORT);
    hiveConf.setIntVar(HiveConf.ConfVars.METASTORETHRIFTRETRIES, 3);
    hiveConf.setIntVar(ConfVars.METASTORE_CLIENT_CONNECT_RETRY_DELAY, 60);

    client = new HiveMetaStoreClient(hiveConf);
  }

}
