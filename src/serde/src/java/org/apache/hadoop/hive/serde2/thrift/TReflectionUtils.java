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


package org.apache.hadoop.hive.serde2.thrift;

import org.apache.thrift.protocol.TProtocolFactory;

/**
 * TReflectionUtils.
 *
 */
public final class TReflectionUtils {
  public static final String thriftReaderFname = "read";
  public static final String thriftWriterFname = "write";

  public static final Class<?>[] thriftRWParams;
  static {
    try {
      thriftRWParams = new Class[] {Class
          .forName("org.apache.thrift.protocol.TProtocol")};
    } catch (ClassNotFoundException e) {
      throw new RuntimeException(e);
    }
  }

  public static TProtocolFactory getProtocolFactoryByName(String protocolName)
      throws Exception {
    Class<?> protoClass = Class.forName(protocolName + "$Factory");
    return ((TProtocolFactory) protoClass.newInstance());
  }

  private TReflectionUtils() {
    // prevent instantiation
  }
}
