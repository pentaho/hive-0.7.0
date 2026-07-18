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

import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * LoadTableDesc.
 *
 */
public class LoadTableDesc extends org.apache.hadoop.hive.ql.plan.LoadDesc
    implements Serializable {
  private static final long serialVersionUID = 1L;
  private boolean replace;
  private String tmpDir;
  private DynamicPartitionCtx dpCtx;
  private boolean holdDDLTime;

  // TODO: the below seems like they should just be combined into partitionDesc
  private org.apache.hadoop.hive.ql.plan.TableDesc table;
  private Map<String, String> partitionSpec; // NOTE: this partitionSpec has to be ordered map

  public LoadTableDesc() {
    this.holdDDLTime = false;
  }

  public LoadTableDesc(final String sourceDir, final String tmpDir,
      final org.apache.hadoop.hive.ql.plan.TableDesc table,
      final Map<String, String> partitionSpec, final boolean replace) {
    super(sourceDir);
    init(sourceDir, tmpDir, table, partitionSpec, replace);
  }

  public LoadTableDesc(final String sourceDir, final String tmpDir,
      final org.apache.hadoop.hive.ql.plan.TableDesc table,
      final Map<String, String> partitionSpec) {
    this(sourceDir, tmpDir, table, partitionSpec, true);
  }

  public LoadTableDesc(final String sourceDir, final String tmpDir,
      final org.apache.hadoop.hive.ql.plan.TableDesc table,
      final DynamicPartitionCtx dpCtx) {
    super(sourceDir);
    this.dpCtx = dpCtx;
    if (dpCtx != null && dpCtx.getPartSpec() != null && partitionSpec == null) {
      init(sourceDir, tmpDir, table, dpCtx.getPartSpec(), true);
    } else {
      init(sourceDir, tmpDir, table, new LinkedHashMap<String, String>(), true);
    }
  }

  private void init(final String sourceDir, final String tmpDir,
      final org.apache.hadoop.hive.ql.plan.TableDesc table,
      final Map<String, String> partitionSpec, final boolean replace) {
    this.tmpDir = tmpDir;
    this.table = table;
    this.partitionSpec = partitionSpec;
    this.replace = replace;
    this.holdDDLTime = false;
  }

  public void setHoldDDLTime(boolean ddlTime) {
    holdDDLTime = ddlTime;
  }

  public boolean getHoldDDLTime() {
    return holdDDLTime;
  }

  @Explain(displayName = "tmp directory", normalExplain = false)
  public String getTmpDir() {
    return tmpDir;
  }

  public void setTmpDir(final String tmp) {
    tmpDir = tmp;
  }

  @Explain(displayName = "table")
  public TableDesc getTable() {
    return table;
  }

  public void setTable(final org.apache.hadoop.hive.ql.plan.TableDesc table) {
    this.table = table;
  }

  @Explain(displayName = "partition")
  public Map<String, String> getPartitionSpec() {
    return partitionSpec;
  }

  public void setPartitionSpec(final Map<String, String> partitionSpec) {
    this.partitionSpec = partitionSpec;
  }

  @Explain(displayName = "replace")
  public boolean getReplace() {
    return replace;
  }

  public void setReplace(boolean replace) {
    this.replace = replace;
  }

  public DynamicPartitionCtx getDPCtx() {
    return dpCtx;
  }

  public void setDPCtx(final DynamicPartitionCtx dpCtx) {
    this.dpCtx = dpCtx;
  }
}
