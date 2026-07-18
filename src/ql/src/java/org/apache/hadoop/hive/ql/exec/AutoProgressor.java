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


package org.apache.hadoop.hive.ql.exec;

import java.util.Timer;
import java.util.TimerTask;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.hadoop.mapred.Reporter;

/**
 * AutoProgressor periodically sends updates to the job tracker so that it
 * doesn't consider this task attempt dead if there is a long period of
 * inactivity. This can be configured with a timeout so that it doesn't run
 * indefinitely.
 */
public class AutoProgressor {
  protected Log LOG = LogFactory.getLog(this.getClass().getName());

  // Timer that reports every 5 minutes to the jobtracker. This ensures that
  // even if the operator returning rows for greater than that
  // duration, a progress report is sent to the tracker so that the tracker
  // does not think that the job is dead.
  Timer rpTimer = null;
  // Timer that tops rpTimer after a long timeout, e.g. 1 hr
  Timer srpTimer = null;
  // Name of the class to report for
  String logClassName = null;
  int notificationInterval;
  int timeout;
  Reporter reporter;

  class ReporterTask extends TimerTask {

    /**
     * Reporter to report progress to the jobtracker.
     */
    private Reporter rp;

    /**
     * Constructor.
     */
    public ReporterTask(Reporter rp) {
      if (rp != null) {
        this.rp = rp;
      }
    }

    @Override
    public void run() {
      if (rp != null) {
        LOG
            .info("ReporterTask calling reporter.progress() for "
            + logClassName);
        rp.progress();
      }
    }
  }

  class StopReporterTimerTask extends TimerTask {

    /**
     * Task to stop the reporter timer once we hit the timeout
     */
    private final ReporterTask rt;

    public StopReporterTimerTask(ReporterTask rp) {
      this.rt = rp;
    }

    @Override
    public void run() {
      if (rt != null) {
        LOG.info("Stopping reporter timer for " + logClassName);
        rt.cancel();
      }
    }
  }

  /**
   *
   * @param logClassName
   * @param reporter
   * @param notificationInterval - interval for reporter updates (in ms)
   */
  AutoProgressor(String logClassName, Reporter reporter,
      int notificationInterval) {
    this.logClassName = logClassName;
    this.reporter = reporter;
    this.notificationInterval = notificationInterval;
    this.timeout = 0;
  }

  /**
   *
   * @param logClassName
   * @param reporter
   * @param notificationInterval - interval for reporter updates (in ms)
   * @param timeout - when the autoprogressor should stop reporting (in ms)
   */
  AutoProgressor(String logClassName, Reporter reporter,
      int notificationInterval, int timeout) {
    this.logClassName = logClassName;
    this.reporter = reporter;
    this.notificationInterval = notificationInterval;
    this.timeout = timeout;
  }

  public void go() {
    LOG.info("Running ReporterTask every " + notificationInterval
        + " miliseconds.");
    rpTimer = new Timer(true);


    ReporterTask rt = new ReporterTask(reporter);
    rpTimer.scheduleAtFixedRate(rt, 0, notificationInterval);

    if (timeout > 0) {
      srpTimer = new Timer(true);
      StopReporterTimerTask srt = new StopReporterTimerTask(rt);
      srpTimer.schedule(srt, timeout);
    }
  }
}
