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


package org.apache.hadoop.hive.ql.history;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.hadoop.hive.ql.history.HiveHistory.Keys;
import org.apache.hadoop.hive.ql.history.HiveHistory.Listener;
import org.apache.hadoop.hive.ql.history.HiveHistory.QueryInfo;
import org.apache.hadoop.hive.ql.history.HiveHistory.RecordTypes;
import org.apache.hadoop.hive.ql.history.HiveHistory.TaskInfo;

/**
 * HiveHistoryViewer.
 *
 */
public class HiveHistoryViewer implements Listener {

  String historyFile;

  String sessionId;

  // Job Hash Map
  private final HashMap<String, QueryInfo> jobInfoMap = new HashMap<String, QueryInfo>();

  // Task Hash Map
  private final HashMap<String, TaskInfo> taskInfoMap = new HashMap<String, TaskInfo>();

  public HiveHistoryViewer(String path) {
    historyFile = path;
    init();
  }

  public String getSessionId() {
    return sessionId;
  }

  public Map<String, QueryInfo> getJobInfoMap() {
    return jobInfoMap;
  }

  public Map<String, TaskInfo> getTaskInfoMap() {
    return taskInfoMap;
  }

  /**
   * Parse history files.
   */
  void init() {

    try {
      HiveHistory.parseHiveHistory(historyFile, this);
    } catch (IOException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }

  }

  /**
   * Implementation Listner interface function.
   * 
   * @see org.apache.hadoop.hive.ql.history.HiveHistory.Listener#handle(org.apache.hadoop.hive.ql.history.HiveHistory.RecordTypes,
   *      java.util.Map)
   */
  public void handle(RecordTypes recType, Map<String, String> values) {

    if (recType == RecordTypes.SessionStart) {
      sessionId = values.get(Keys.SESSION_ID.name());
    } else if (recType == RecordTypes.QueryStart
        || recType == RecordTypes.QueryEnd) {
      String key = values.get(Keys.QUERY_ID.name());
      QueryInfo ji;
      if (jobInfoMap.containsKey(key)) {
        ji = jobInfoMap.get(key);

        ji.hm.putAll(values);

      } else {
        ji = new QueryInfo();
        ji.hm = new HashMap<String, String>();
        ji.hm.putAll(values);

        jobInfoMap.put(key, ji);

      }
    } else if (recType == RecordTypes.TaskStart
        || recType == RecordTypes.TaskEnd
        || recType == RecordTypes.TaskProgress) {

      String jobid = values.get(Keys.QUERY_ID.name());
      String taskid = values.get(Keys.TASK_ID.name());
      String key = jobid + ":" + taskid;
      TaskInfo ti;
      if (taskInfoMap.containsKey(key)) {
        ti = taskInfoMap.get(key);
        ti.hm.putAll(values);
      } else {
        ti = new TaskInfo();
        ti.hm = new HashMap<String, String>();
        ti.hm.putAll(values);
        taskInfoMap.put(key, ti);

      }

    }

  }

}
