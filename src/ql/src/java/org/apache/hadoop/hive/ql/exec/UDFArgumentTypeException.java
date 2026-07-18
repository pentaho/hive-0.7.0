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


package org.apache.hadoop.hive.ql.exec;

/**
 * exception class, thrown when udf arguments have wrong types.
 */

public class UDFArgumentTypeException extends UDFArgumentException {

  int argumentId;

  public UDFArgumentTypeException() {
    super();
  }

  public UDFArgumentTypeException(int argumentId, String message) {
    super(message);
    this.argumentId = argumentId;
  }

  public int getArgumentId() {
    return argumentId;
  }
}
