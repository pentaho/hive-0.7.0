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

import org.apache.hadoop.mapred.JobConf;

/**
 * Classes implementing ErrorHeuristic are able to generate a possible cause and
 * solution for Hive jobs that have failed by examining the query, task log
 * files, and the job configuration.
 *
 * A class implementing ErrorHeuristic should only detect one type of error.
 *
 */
public interface ErrorHeuristic {

  /**
   * Initialize this error heuristic. Must be called before any other methods
   * are called
   * @param query
   * @param jobConf
   */
  void init(String query, JobConf jobConf);

  /**
   * Process the given log line. It should be called for every line in the task
   * log file, in sequence.
   *
   * @param line
   */
  void processLogLine(String line);

  /**
   * Examine the hive query, job configuration, and the lines from the task log
   * seen so far though processLogLine() and generate a possible cause/solution.
   * Once this method is called, the implementing class should be reset to the
   * state before any processLogLine() calls were made.
   *
   * @return a matching error, or null if a suitable match wasn't found.
   *
   */
  ErrorAndSolution getErrorAndSolution();
}
