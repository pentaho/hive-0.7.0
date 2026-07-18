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


package org.apache.hadoop.hive.ql.metadata;

public class AuthorizationException extends RuntimeException {
  /**
   */
  private static final long serialVersionUID = 1L;

  public AuthorizationException() {
    super();
  }

  /**
   * Constructs an {@link AuthorizationException} with the specified detail
   * message.
   * 
   * @param s
   *          the detail message.
   */
  public AuthorizationException(String message) {
    super(message);
  }

  /**
   * Constructs an {@link AuthorizationException} with the specified cause.
   * 
   * @param cause
   *          the cause
   */
  public AuthorizationException(Throwable cause) {
    super(cause);
  }


  public AuthorizationException(String message, Throwable cause) {
    super(message, cause);
  }
}
