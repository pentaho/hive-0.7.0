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


package org.apache.hadoop.hive.ql.exec.errors;

/**
 * Immutable class for storing a possible error and a resolution suggestion.
 */
public class ErrorAndSolution {

  private String error = null;
  private String solution = null;

  ErrorAndSolution(String error, String solution) {
    this.error = error;
    this.solution = solution;
  }

  public String getError() {
    return error;
  }

  public String getSolution() {
    return solution;
  }

  @Override
  public boolean equals(Object o) {
    if (!(o instanceof ErrorAndSolution)) {
      return false;
    }
    ErrorAndSolution e = (ErrorAndSolution)o;

    return e.error == this.error && e.solution == this.solution;
  }

  @Override
  public int hashCode() {
    return error.hashCode() * 37 + solution.hashCode();
  }
}
