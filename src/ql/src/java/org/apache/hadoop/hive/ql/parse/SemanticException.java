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


package org.apache.hadoop.hive.ql.parse;

import org.apache.hadoop.hive.ql.metadata.HiveException;

/**
 * Exception from SemanticAnalyzer.
 */

public class SemanticException extends HiveException {

  private static final long serialVersionUID = 1L;

  public SemanticException() {
    super();
  }

  public SemanticException(String message) {
    super(message);
  }

  public SemanticException(Throwable cause) {
    super(cause);
  }

  public SemanticException(String message, Throwable cause) {
    super(message, cause);
  }

}
