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
 * An interface for TProtocols that actually write out nulls - This should be
 * for all those that don't actually use fieldids in the written data like
 * TCTLSeparatedProtocol.
 * 
 */
public interface WriteNullsProtocol {
  /**
   * Was the last primitive read really a NULL. Need only be called when the
   * value of the primitive was 0. ie the protocol should return 0 on nulls and
   * the caller will then check if it was actually null For boolean this is
   * false.
   */
  boolean lastPrimitiveWasNull() throws TException;

  /**
   * Write a null.
   */
  void writeNull() throws TException;

}
