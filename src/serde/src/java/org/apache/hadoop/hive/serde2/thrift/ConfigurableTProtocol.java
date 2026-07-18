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
import org.apache.thrift.TException;

/**
 * An interface for TProtocols that need to have properties passed in to
 * initialize them. e.g., separators for TCTLSeparatedProtocol. If there was a
 * regex like deserializer, the regex could be passed in in this manner.
 */
public interface ConfigurableTProtocol {
  /**
   * Initialize the TProtocol.
   * 
   * @param conf
   *          System properties
   * @param tbl
   *          table properties
   * @throws TException
   */
  void initialize(Configuration conf, Properties tbl) throws TException;

}
