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

import java.lang.reflect.Type;
import java.util.Properties;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hive.serde2.ByteStreamTypedSerDe;
import org.apache.hadoop.hive.serde2.SerDeException;
import org.apache.hadoop.hive.serde2.objectinspector.ObjectInspectorFactory;
import org.apache.hadoop.io.Writable;
import org.apache.thrift.TBase;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.protocol.TProtocolFactory;
import org.apache.thrift.transport.TIOStreamTransport;

/**
 * ThriftByteStreamTypedSerDe.
 *
 */
public class ThriftByteStreamTypedSerDe extends ByteStreamTypedSerDe {

  protected TIOStreamTransport outTransport, inTransport;
  protected TProtocol outProtocol, inProtocol;

  private void init(TProtocolFactory inFactory, TProtocolFactory outFactory)
      throws Exception {
    outTransport = new TIOStreamTransport(bos);
    inTransport = new TIOStreamTransport(bis);
    outProtocol = outFactory.getProtocol(outTransport);
    inProtocol = inFactory.getProtocol(inTransport);
  }

  @Override
  public void initialize(Configuration job, Properties tbl)
      throws SerDeException {
    throw new SerDeException(
        "ThriftByteStreamTypedSerDe is still semi-abstract");
  }

  public ThriftByteStreamTypedSerDe(Type objectType,
      TProtocolFactory inFactory, TProtocolFactory outFactory)
      throws SerDeException {
    super(objectType);
    try {
      init(inFactory, outFactory);
    } catch (Exception e) {
      throw new SerDeException(e);
    }
  }

  @Override
  protected ObjectInspectorFactory.ObjectInspectorOptions getObjectInspectorOptions() {
    return ObjectInspectorFactory.ObjectInspectorOptions.THRIFT;
  }

  @Override
  public Object deserialize(Writable field) throws SerDeException {
    Object obj = super.deserialize(field);
    try {
      ((TBase) obj).read(inProtocol);
    } catch (Exception e) {
      throw new SerDeException(e);
    }
    return obj;
  }

}
