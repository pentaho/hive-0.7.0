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

import org.mortbay.jetty.bio.SocketConnector;
import org.mortbay.jetty.handler.RequestLogHandler;
import org.mortbay.jetty.webapp.WebAppContext;

/**
 * Jetty20Shims.
 *
 */
public class Jetty20Shims implements JettyShims {
  public Server startServer(String listen, int port) throws IOException {
    Server s = new Server();
    s.setupListenerHostPort(listen, port);
    return s;
  }

  private static class Server extends org.mortbay.jetty.Server implements JettyShims.Server {
    public void addWar(String war, String contextPath) {
      WebAppContext wac = new WebAppContext();
      wac.setContextPath(contextPath);
      wac.setWar(war);
      RequestLogHandler rlh = new RequestLogHandler();
      rlh.setHandler(wac);
      this.addHandler(rlh);
    }

    public void setupListenerHostPort(String listen, int port)
        throws IOException {

      SocketConnector connector = new SocketConnector();
      connector.setPort(port);
      connector.setHost(listen);
      this.addConnector(connector);
    }
  }
}
