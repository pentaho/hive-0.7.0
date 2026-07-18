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


package org.apache.hadoop.hive.ql.stats;

import org.apache.hadoop.conf.Configuration;

/**
 * An interface for any possible implementation for publishing statics.
 */

public interface StatsPublisher {

  /**
   * This method does the necessary one-time initializations, possibly creating the tables and
   * database (if not exist).
   * This method is usually called in the Hive client side rather than by the mappers/reducers
   * so that it is initialized only once.
   * @param hconf HiveConf that contains the configurations parameters used to connect to
   * intermediate stats database.
   * @return true if initialization is successful, false otherwise.
   */
  public boolean init(Configuration hconf);

  /**
   * This method connects to the intermediate statistics database.
   * @param hconf HiveConf that contains the connection parameters.
   * @return true if connection is successful, false otherwise.
   */
  public boolean connect(Configuration hconf);

  /**
   * This method publishes a given statistic into a disk storage, possibly HBase or MySQL.
   *
   * fileID   : a string identification the statistics to be published by all mappers/reducers
   *            and then gathered. The statID is unique per output partition per task, e.g.,:
   *                 the output directory name (uniq per FileSinkOperator) +
   *                 the partition specs (only for dynamic partitions) +
   *                 taskID (last component of task file)
   *
   * statType : a string noting the key to be published. Ex: "numRows".
   *
   * value    : an integer noting the value of the published key.
   */
  public boolean publishStat(String fileID, String statType, String value);

  /**
   * This method closes the connection to the temporary storage.
   */
  public boolean closeConnection();

}
