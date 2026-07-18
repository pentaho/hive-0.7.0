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

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * A Hive Table Partition: is a fundamental storage unit within a Table. Currently, Hive does not support
 * hierarchical partitions - For eg: if partition ds=1, hr=1 exists, there is no way to access ds=1
 *
 * Hierarchical partitions are needed in some cases, for eg. locking. For now, create a dummy partition to
 * satisfy this
 */
public class DummyPartition extends Partition {

  @SuppressWarnings("nls")
  static final private Log LOG = LogFactory
      .getLog("hive.ql.metadata.DummyPartition");

  private String name;
  public DummyPartition() {
  }

  public DummyPartition(Table tbl, String name) throws HiveException {
    setTable(tbl);
    this.name = name;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getCompleteName() {
    return getName();
  }
}
