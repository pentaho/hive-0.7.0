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


package org.apache.hadoop.hive.ql.processors;

/**
 * Encapsulates the basic response info returned by classes the implement the
 * <code>CommandProcessor</code> interfaace. Typically <code>errorMessage</code>
 * and <code>SQLState</code> will only be set if the <code>responseCode</code>
 * is not 0.
 */
public class CommandProcessorResponse {
  private int responseCode;
  private String errorMessage;
  private String SQLState;

  public CommandProcessorResponse(int responseCode) {
    this(responseCode, null, null);
  }

  public CommandProcessorResponse(int responseCode, String errorMessage, String SQLState) {
    this.responseCode = responseCode;
    this.errorMessage = errorMessage;
    this.SQLState = SQLState;
  }

  public int getResponseCode() { return responseCode; }
  public String getErrorMessage() { return errorMessage; }
  public String getSQLState() { return SQLState; }
}
