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


package org.apache.hadoop.hive.shims;

import java.io.IOException;

/**
 * Since Hadoop ships with different versions of Jetty in different versions,
 * Hive uses a shim layer to access the parts of the API that have changed.
 * Users should obtain an instance of this class using the ShimLoader factory.
 */
public interface JettyShims {

  Server startServer(String listen, int port) throws IOException;

  /**
   * Server.
   *
   */
  interface Server {
    void addWar(String war, String mount);

    void start() throws Exception;

    void join() throws InterruptedException;

    void stop() throws Exception;
  }
}
