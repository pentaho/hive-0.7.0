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


package org.apache.hadoop.hive.ql.plan;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.hadoop.fs.FileStatus;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.hive.conf.HiveConf;
import org.apache.hadoop.hive.ql.exec.Task;

/**
 * ConditionalResolverSkewJoin.
 *
 */
public class ConditionalResolverSkewJoin implements ConditionalResolver, Serializable {
  private static final long serialVersionUID = 1L;

  /**
   * ConditionalResolverSkewJoinCtx.
   *
   */
  public static class ConditionalResolverSkewJoinCtx implements Serializable {
    private static final long serialVersionUID = 1L;
    // we store big keys in one table into one dir, and same keys in other
    // tables into corresponding different dirs (one dir per table).
    // this map stores mapping from "big key dir" to its corresponding mapjoin
    // task.
    HashMap<String, Task<? extends Serializable>> dirToTaskMap;

    /**
     * For serialization use only.
     */
    public ConditionalResolverSkewJoinCtx() {
    }

    public ConditionalResolverSkewJoinCtx(
        HashMap<String, Task<? extends Serializable>> dirToTaskMap) {
      super();
      this.dirToTaskMap = dirToTaskMap;
    }

    public HashMap<String, Task<? extends Serializable>> getDirToTaskMap() {
      return dirToTaskMap;
    }

    public void setDirToTaskMap(
        HashMap<String, Task<? extends Serializable>> dirToTaskMap) {
      this.dirToTaskMap = dirToTaskMap;
    }
  }

  public ConditionalResolverSkewJoin() {
  }

  @Override
  public List<Task<? extends Serializable>> getTasks(HiveConf conf,
      Object objCtx) {
    ConditionalResolverSkewJoinCtx ctx = (ConditionalResolverSkewJoinCtx) objCtx;
    List<Task<? extends Serializable>> resTsks = new ArrayList<Task<? extends Serializable>>();

    Map<String, Task<? extends Serializable>> dirToTaskMap = ctx
        .getDirToTaskMap();
    Iterator<Entry<String, Task<? extends Serializable>>> bigKeysPathsIter = dirToTaskMap
        .entrySet().iterator();
    try {
      while (bigKeysPathsIter.hasNext()) {
        Entry<String, Task<? extends Serializable>> entry = bigKeysPathsIter
            .next();
        String path = entry.getKey();
        Path dirPath = new Path(path);
        FileSystem inpFs = dirPath.getFileSystem(conf);
        FileStatus[] fstatus = inpFs.listStatus(dirPath);
        if (fstatus.length > 0) {
          Task <? extends Serializable> task = entry.getValue();
          List<Task <? extends Serializable>> parentOps = task.getParentTasks();
          if(parentOps!=null){
            for(Task <? extends Serializable> parentOp: parentOps){
              //right now only one parent
              resTsks.add(parentOp);
            }
          }else{
            resTsks.add(task);
          }
        }
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
    return resTsks;
  }

}
