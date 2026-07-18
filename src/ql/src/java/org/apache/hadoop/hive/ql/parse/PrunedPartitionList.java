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

import java.util.Set;

import org.apache.hadoop.hive.ql.metadata.Partition;

/**
 * The list of pruned partitions.
 */
public class PrunedPartitionList {
  // confirmed partitions - satisfy the partition criteria
  private Set<Partition> confirmedPartns;

  // unknown partitions - may/may not satisfy the partition criteria
  private Set<Partition> unknownPartns;

  // denied partitions - do not satisfy the partition criteria
  private final Set<Partition> deniedPartns;

  /**
   * @param confirmedPartns
   *          confirmed paritions
   * @param unknownPartns
   *          unknown partitions
   */
  public PrunedPartitionList(Set<Partition> confirmedPartns,
      Set<Partition> unknownPartns, Set<Partition> deniedPartns) {
    this.confirmedPartns = confirmedPartns;
    this.unknownPartns = unknownPartns;
    this.deniedPartns = deniedPartns;
  }

  /**
   * get confirmed partitions.
   * 
   * @return confirmedPartns confirmed paritions
   */
  public Set<Partition> getConfirmedPartns() {
    return confirmedPartns;
  }

  /**
   * get unknown partitions.
   * 
   * @return unknownPartns unknown paritions
   */
  public Set<Partition> getUnknownPartns() {
    return unknownPartns;
  }

  /**
   * get denied partitions.
   * 
   * @return deniedPartns denied paritions
   */
  public Set<Partition> getDeniedPartns() {
    return deniedPartns;
  }

  /**
   * set confirmed partitions.
   * 
   * @param confirmedPartns
   *          confirmed paritions
   */
  public void setConfirmedPartns(Set<Partition> confirmedPartns) {
    this.confirmedPartns = confirmedPartns;
  }

  /**
   * set unknown partitions.
   * 
   * @param unknownPartns
   *          unknown partitions
   */
  public void setUnknownPartns(Set<Partition> unknownPartns) {
    this.unknownPartns = unknownPartns;
  }
}
