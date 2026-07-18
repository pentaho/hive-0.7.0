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

import org.apache.thrift.TException;

/**
 * An interface for TProtocols that can efficiently skip fields when
 * deserializing.
 */
public interface SkippableTProtocol {
  void skip(byte type) throws TException;
}
