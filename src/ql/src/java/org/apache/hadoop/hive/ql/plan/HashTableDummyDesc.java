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
/**
 * HashTable Dummy Descriptor implementation.
 *
 */
@Explain(displayName = "HashTable Dummy Operator")
public class HashTableDummyDesc implements Serializable {
  private TableDesc tbl;

  public TableDesc getTbl() {
    return tbl;
  }

  public void setTbl(TableDesc tbl) {
    this.tbl = tbl;
  }

}
