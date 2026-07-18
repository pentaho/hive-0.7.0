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


package org.apache.hadoop.hive.service;

import org.apache.thrift.protocol.TProtocol;

/**
 * Thrift Hive Client Just an empty class that can be used to run queries on a
 * stand alone hive server.
 */
public class HiveClient extends ThriftHive.Client implements HiveInterface {
  public HiveClient(TProtocol prot) {
    super(prot, prot);
  }
}
