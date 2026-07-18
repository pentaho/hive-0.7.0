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


package org.apache.hadoop.hive.serde2;

import java.util.ArrayList;

/**
 * ColumnSet.
 *
 */
public class ColumnSet {
  public ArrayList<String> col;

  public ColumnSet() {
  }

  public ColumnSet(ArrayList<String> col) {
    this();
    this.col = col;
  }

  @Override
  public String toString() {
    return col.toString();
  }

}
