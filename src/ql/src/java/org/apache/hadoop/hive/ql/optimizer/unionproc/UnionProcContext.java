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


package org.apache.hadoop.hive.ql.optimizer.unionproc;

import java.util.HashMap;
import java.util.Map;

import org.apache.hadoop.hive.ql.exec.UnionOperator;
import org.apache.hadoop.hive.ql.lib.NodeProcessorCtx;

/**
 * UnionProcContext.
 *
 */
public class UnionProcContext implements NodeProcessorCtx {

  /**
   * UnionParseContext.
   *
   */
  public static class UnionParseContext {
    private final transient boolean[] mapOnlySubq;
    private final transient boolean[] rootTask;
    private final transient boolean[] mapJoinSubq;

    private transient int numInputs;
    private transient boolean mapJoinQuery;

    public UnionParseContext(int numInputs) {
      this.numInputs = numInputs;
      mapOnlySubq = new boolean[numInputs];
      rootTask = new boolean[numInputs];
      mapJoinSubq = new boolean[numInputs];
    }

    public boolean getMapOnlySubq(int pos) {
      return mapOnlySubq[pos];
    }

    public void setMapOnlySubq(int pos, boolean mapOnlySubq) {
      this.mapOnlySubq[pos] = mapOnlySubq;
    }

    public boolean getMapJoinSubq(int pos) {
      return mapJoinSubq[pos];
    }

    public void setMapJoinSubq(int pos, boolean mapJoinSubq) {
      this.mapJoinSubq[pos] = mapJoinSubq;
      if (mapJoinSubq) {
        mapJoinQuery = true;
      }
    }

    public boolean getMapJoinQuery() {
      return mapJoinQuery;
    }

    public boolean getRootTask(int pos) {
      return rootTask[pos];
    }

    public void setRootTask(int pos, boolean rootTask) {
      this.rootTask[pos] = rootTask;
    }

    public int getNumInputs() {
      return numInputs;
    }

    public void setNumInputs(int numInputs) {
      this.numInputs = numInputs;
    }
  }

  // the subqueries are map-only jobs
  private boolean mapOnlySubq;

  /**
   * @return the mapOnlySubq
   */
  public boolean isMapOnlySubq() {
    return mapOnlySubq;
  }

  /**
   * @param mapOnlySubq
   *          the mapOnlySubq to set
   */
  public void setMapOnlySubq(boolean mapOnlySubq) {
    this.mapOnlySubq = mapOnlySubq;
  }

  private final Map<UnionOperator, UnionParseContext> uCtxMap;

  public UnionProcContext() {
    uCtxMap = new HashMap<UnionOperator, UnionParseContext>();
    mapOnlySubq = true;
  }

  public void setUnionParseContext(UnionOperator u, UnionParseContext uCtx) {
    uCtxMap.put(u, uCtx);
  }

  public UnionParseContext getUnionParseContext(UnionOperator u) {
    return uCtxMap.get(u);
  }
}
