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

import org.apache.hadoop.io.Text;
import org.apache.thrift.TException;

/**
 * An interface for TProtocols that can write out data in hadoop Text objects
 * (UTF-8 encoded String). This helps a lot with performance because we don't
 * need to do unnecessary UTF-8 decoding and encoding loops.
 */
public interface WriteTextProtocol {

  /**
   * Write Text.
   */
  void writeText(Text text) throws TException;

}
