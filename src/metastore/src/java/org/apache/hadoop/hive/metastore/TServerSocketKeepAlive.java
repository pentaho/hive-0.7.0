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

import java.net.SocketException;

import org.apache.thrift.transport.TServerSocket;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.transport.TTransportException;

/**
 * TServerSocketKeepAlive - like TServerSocket, but will enable keepalive for
 * accepted sockets.
 *
 */
public class TServerSocketKeepAlive extends TServerSocket {
  public TServerSocketKeepAlive(int port) throws TTransportException {
    super(port, 0);
  }

  @Override
  protected TSocket acceptImpl() throws TTransportException {
    TSocket ts = super.acceptImpl();
    try {
      ts.getSocket().setKeepAlive(true);
    } catch (SocketException e) {
      throw new TTransportException(e);
    }
    return ts;
  }
}
