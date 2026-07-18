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


package org.apache.hadoop.hive.ql.metadata;

import java.util.Map;

import org.apache.hadoop.conf.Configurable;
import org.apache.hadoop.hive.metastore.HiveMetaHook;
import org.apache.hadoop.hive.ql.plan.TableDesc;
import org.apache.hadoop.hive.serde2.SerDe;
import org.apache.hadoop.mapred.InputFormat;
import org.apache.hadoop.mapred.OutputFormat;

/**
 * HiveStorageHandler defines a pluggable interface for adding
 * new storage handlers to Hive.  A storage handler consists of
 * a bundle of the following:
 *
 *<ul>
 *<li>input format
 *<li>output format
 *<li>serde
 *<li>metadata hooks for keeping an external catalog in sync
 * with Hive's metastore
 *<li>rules for setting up the configuration properties on
 * map/reduce jobs which access tables stored by this handler
 *</ul>
 *
 * Storage handler classes are plugged in using the STORED BY 'classname'
 * clause in CREATE TABLE.
 */
public interface HiveStorageHandler extends Configurable {
  /**
   * @return Class providing an implementation of {@link InputFormat}
   */
  public Class<? extends InputFormat> getInputFormatClass();

  /**
   * @return Class providing an implementation of {@link OutputFormat}
   */
  public Class<? extends OutputFormat> getOutputFormatClass();

  /**
   * @return Class providing an implementation of {@link SerDe}
   */
  public Class<? extends SerDe> getSerDeClass();

  /**
   * @return metadata hook implementation, or null if this
   * storage handler does not need any metadata notifications
   */
  public HiveMetaHook getMetaHook();

  /**
   * Configures properties for a job based on the definition of the
   * source or target table it accesses.
   *
   * @param tableDesc descriptor for the table being accessed
   *
   * @param jobProperties receives properties copied or transformed
   * from the table properties
   */
  public void configureTableJobProperties(
    TableDesc tableDesc,
    Map<String, String> jobProperties);
}
