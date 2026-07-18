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

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Detects when a query has failed because a user's script that was specified in
 * transform returns a non-zero error code.
 *
 * Conditions to check:
 *
 * 1. "Script failed with code <some number>" is in the log
 *
 */

public class ScriptErrorHeuristic extends RegexErrorHeuristic {

  private static final String FAILED_REGEX = "Script failed with code [0-9]+";

  public ScriptErrorHeuristic() {
    setQueryRegex(".*");
    getLogRegexes().add(FAILED_REGEX);
  }

  @Override
  public ErrorAndSolution getErrorAndSolution() {
    ErrorAndSolution es = null;

    if(getQueryMatches()) {
      for(List<String> matchingLines : getRegexToLogLines().values()) {
        // There should really only be one line with "Script failed..."
        if (matchingLines.size() > 0) {
          assert(matchingLines.size() == 1);

          // Get "Script failed with code <some number>"
          Matcher m1 = Pattern.compile(FAILED_REGEX).matcher(matchingLines.get(0));
          m1.find();
          String failedStr = m1.group();

          // Get "<some number>"
          Matcher m2 = Pattern.compile("[0-9]+").matcher(failedStr);
          m2.find();
          String errorCode = m2.group();

          es = new ErrorAndSolution(
            "A user-supplied transfrom script has exited with error code " +
            errorCode + " instead of 0.",
            "Verify that the script can properly handle all the input rows " +
            "without throwing exceptions and exits properly.");
        }
      }
    }

    reset();
    return es;
  }
}
