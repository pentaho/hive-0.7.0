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
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Detects the condition where there is a error with one of the input files in
 * the query.
 *
 * Conditions to check:
 * 1. EOFException in log
 * 2. A line indicating the split file in the log. This is needed to
 *    generate the proper error message.
 *
 */

public class DataCorruptErrorHeuristic extends RegexErrorHeuristic {

  private static final String SPLIT_REGEX = "split:.*";
  private static final String EXCEPTION_REGEX = "EOFException";

  public DataCorruptErrorHeuristic() {
    setQueryRegex(".*");
    getLogRegexes().add(SPLIT_REGEX);
    getLogRegexes().add(EXCEPTION_REGEX);
  }

  @Override
  public ErrorAndSolution getErrorAndSolution() {
    ErrorAndSolution es = null;

    if(getQueryMatches()) {
      Map<String, List<String>> rll = getRegexToLogLines();
      if (rll.get(EXCEPTION_REGEX).size() > 0 &&
          rll.get(SPLIT_REGEX).size() > 0) {

        // There should only be a single split line...
        assert(rll.get(SPLIT_REGEX).size()==1);
        String splitLogLine = rll.get(SPLIT_REGEX).get(0);

        // Extract only 'split: hdfs://...'
        Pattern p = Pattern.compile(SPLIT_REGEX, Pattern.CASE_INSENSITIVE);
        Matcher m = p.matcher(splitLogLine);
        m.find();
        String splitStr = m.group();

        es = new ErrorAndSolution(
            "Data file " + splitStr + " is corrupted.",
            "Replace file. i.e. by re-running the query that produced the " +
            "source table / partition.");
      }
    }
    reset();
    return es;
  }
}
