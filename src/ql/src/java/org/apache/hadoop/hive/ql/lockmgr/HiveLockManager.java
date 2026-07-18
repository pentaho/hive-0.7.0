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


package org.apache.hadoop.hive.ql.lockmgr;

import java.util.List;

public interface HiveLockManager {

  public void setContext(HiveLockManagerCtx ctx) throws LockException;

  /**
   * @param key        object to be locked
   * @param mode       mode of the lock (SHARED/EXCLUSIVE)
   * @param keepAlive  if the lock needs to be persisted after the statement
   * @param sleepTime
   * @param numRetries
   */
  public HiveLock lock(HiveLockObject key, HiveLockMode mode,
      boolean keepAlive, int numRetries, int sleepTime) throws LockException;
  public List<HiveLock> lock(List<HiveLockObj> objs,
      boolean keepAlive, int numRetries, int sleepTime) throws LockException;
  public void unlock(HiveLock hiveLock) throws LockException;
  public void releaseLocks(List<HiveLock> hiveLocks);

  public List<HiveLock> getLocks(boolean verifyTablePartitions, boolean fetchData) throws LockException;
  public List<HiveLock> getLocks(HiveLockObject key, boolean verifyTablePartitions, boolean fetchData) throws LockException;
  public void close() throws LockException;
}
