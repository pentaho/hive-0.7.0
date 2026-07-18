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

import java.util.ArrayList;

/**
 * ParseException.
 *
 */
public class ParseException extends Exception {

  private static final long serialVersionUID = 1L;
  ArrayList<ParseError> errors;

  public ParseException(ArrayList<ParseError> errors) {
    super();
    this.errors = errors;
  }

  @Override
  public String getMessage() {

    StringBuilder sb = new StringBuilder();
    for (ParseError err : errors) {
      sb.append(err.getMessage());
      sb.append("\n");
    }

    return sb.toString();
  }

}
