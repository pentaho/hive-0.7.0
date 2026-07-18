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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;
import java.util.regex.Pattern;

import org.apache.hadoop.mapred.JobConf;

/**
 * Simple heuristic where the query and the lines of the task log file are run
 * through regular expressions to see if they resemble a known error condition.
 *
 * Only a single regular expression can be supplied to match the query whereas
 * multiple regular expressions can be supplied to match lines from the log file.
 * A mapping is maintained from the regular expression to the lines from the log
 * file that it matched.
 */
public abstract class RegexErrorHeuristic implements ErrorHeuristic {

  private String query = null;
  private JobConf conf = null;

  // Pattern to look for in the hive query and whether it matched
  private String queryRegex = null;
  private boolean queryMatches = false;

  // The regexes to look for in the log files
  private final Set<String> logRegexes = new HashSet<String>();

  // Mapping from the regex to lines in the log file where find() == true
  private final Map<String, List<String>> regexToLogLines = new HashMap<String, List<String>>();
  private final Map<String, Pattern> regexToPattern = new HashMap<String, Pattern>();

  public RegexErrorHeuristic() {
  }

  protected void setQueryRegex(String queryRegex) {
    this.queryRegex  = queryRegex;
  }

  protected String getQueryRegex() {
    return queryRegex;
  }

  protected boolean getQueryMatches() {
    return queryMatches;
  }

  protected Set<String> getLogRegexes() {
    return this.logRegexes;
  }

  protected Map<String, List<String>> getRegexToLogLines() {
    return this.regexToLogLines;
  }

  protected JobConf getConf() {
    return conf;
  }

  @Override
  /**
   * Before init is called, logRegexes and queryRegexes should be populated.
   */
  public void init(String query, JobConf conf) {
    this.query = query;
    this.conf = conf;

    assert((logRegexes!=null) && (queryRegex != null));

    Pattern queryPattern = Pattern.compile(queryRegex, Pattern.CASE_INSENSITIVE);
    queryMatches = queryPattern.matcher(query).find();

    for(String regex : logRegexes) {
      regexToPattern.put(regex, Pattern.compile(regex, Pattern.CASE_INSENSITIVE));
      regexToLogLines.put(regex, new ArrayList<String>());
    }

  }

  @Override
  public abstract ErrorAndSolution getErrorAndSolution();

  @Override
  public void processLogLine(String line) {
    if(queryMatches) {
      for(Entry<String, Pattern> e : regexToPattern.entrySet()) {
        String regex = e.getKey();
        Pattern p = e.getValue();
        boolean lineMatches = p.matcher(line).find();
        if(lineMatches) {
          regexToLogLines.get(regex).add(line);
       }
      }
    }
  }

  /**
   * Resets to state before any processLogLine() calls.
   */
  protected void reset() {
    for(List<String> lst : regexToLogLines.values()) {
      lst.clear();
    }
  }
}
