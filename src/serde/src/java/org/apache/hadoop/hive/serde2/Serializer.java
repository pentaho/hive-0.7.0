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


package org.apache.hadoop.hive.serde2;

import java.util.Properties;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hive.serde2.objectinspector.ObjectInspector;
import org.apache.hadoop.io.Writable;

/**
 * HiveSerializer is used to serialize data to a Hadoop Writable object. The
 * serialize In addition to the interface below, all implementations are assume
 * to have a ctor that takes a single 'Table' object as argument.
 * 
 */
public interface Serializer {

  /**
   * Initialize the HiveSerializer.
   * 
   * @param conf
   *          System properties
   * @param tbl
   *          table properties
   * @throws SerDeException
   */
  void initialize(Configuration conf, Properties tbl) throws SerDeException;

  /**
   * Returns the Writable class that would be returned by the serialize method.
   * This is used to initialize SequenceFile header.
   */
  Class<? extends Writable> getSerializedClass();

  /**
   * Serialize an object by navigating inside the Object with the
   * ObjectInspector. In most cases, the return value of this function will be
   * constant since the function will reuse the Writable object. If the client
   * wants to keep a copy of the Writable, the client needs to clone the
   * returned value.
   */
  Writable serialize(Object obj, ObjectInspector objInspector) throws SerDeException;
}
