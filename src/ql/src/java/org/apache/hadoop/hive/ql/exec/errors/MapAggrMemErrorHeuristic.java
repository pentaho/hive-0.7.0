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

import org.apache.hadoop.hive.conf.HiveConf;
import org.apache.hadoop.mapred.JobConf;

/**
 * Detects out-of-memory errors when hash tables in map-based aggregation group
 * by queries take up too much memory.
 *
 * Conditions to check
 *
 * 1. The query contains a group by.
 * 2. Map-side aggregation is turned on.
 * 3. There is a out of memory exception in the log.
 */
public class MapAggrMemErrorHeuristic extends RegexErrorHeuristic {

  private static final String OUT_OF_MEMORY_REGEX = "OutOfMemoryError";
  private boolean configMatches = false;

  public MapAggrMemErrorHeuristic() {
    setQueryRegex("group by");
    getLogRegexes().add(OUT_OF_MEMORY_REGEX);
  }

  @Override
  public void init(String query, JobConf conf) {
    super.init(query, conf);
    configMatches = HiveConf.getBoolVar(conf, HiveConf.ConfVars.HIVEMAPSIDEAGGREGATE);
  }

  @Override
  public ErrorAndSolution getErrorAndSolution() {
    ErrorAndSolution es = null;
    if(getQueryMatches() && configMatches) {
      List<String> matchingLines = getRegexToLogLines().get(OUT_OF_MEMORY_REGEX);

      if (matchingLines.size() > 0) {
        String confName = HiveConf.ConfVars.HIVEMAPAGGRHASHMEMORY.toString();
        float confValue =  HiveConf.getFloatVar(getConf(),
            HiveConf.ConfVars.HIVEMAPAGGRHASHMEMORY);

        es = new ErrorAndSolution(
            "Out of memory due to hash maps used in map-side aggregation.",
            "Currently " + confName + " is set to " + confValue + ". " +
            "Try setting it to a lower value. i.e " +
            "'set " + confName + " = " + confValue/2 + ";'");
      }
    }
    reset();
    return es;
  }
}
