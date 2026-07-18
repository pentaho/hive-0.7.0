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

import java.util.Properties;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hive.serde2.Deserializer;
import org.apache.hadoop.hive.serde2.SerDeException;
import org.apache.hadoop.hive.serde2.objectinspector.ObjectInspector;
import org.apache.hadoop.io.Writable;
import org.apache.thrift.protocol.TProtocolFactory;

/**
 * ThriftDeserializer.
 *
 */
public class ThriftDeserializer implements Deserializer {

  private ThriftByteStreamTypedSerDe tsd;

  public ThriftDeserializer() {
  }

  public void initialize(Configuration job, Properties tbl)
      throws SerDeException {
    try {
      // both the classname and the protocol name are Table properties
      // the only hardwired assumption is that records are fixed on a
      // per Table basis

      String className = tbl
          .getProperty(org.apache.hadoop.hive.serde.Constants.SERIALIZATION_CLASS);
      Class<?> recordClass = job.getClassByName(className);

      String protoName = tbl
          .getProperty(org.apache.hadoop.hive.serde.Constants.SERIALIZATION_FORMAT);
      if (protoName == null) {
        protoName = "TBinaryProtocol";
      }
      // For backward compatibility
      protoName = protoName.replace("com.facebook.thrift.protocol",
          "org.apache.thrift.protocol");

      TProtocolFactory tp = TReflectionUtils
          .getProtocolFactoryByName(protoName);
      tsd = new ThriftByteStreamTypedSerDe(recordClass, tp, tp);

    } catch (Exception e) {
      throw new SerDeException(e);
    }
  }

  public Object deserialize(Writable field) throws SerDeException {
    return tsd.deserialize(field);
  }

  public ObjectInspector getObjectInspector() throws SerDeException {
    return tsd.getObjectInspector();
  }

}
