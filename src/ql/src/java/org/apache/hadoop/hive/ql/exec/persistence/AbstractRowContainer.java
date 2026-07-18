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


package org.apache.hadoop.hive.ql.exec.persistence;

import org.apache.hadoop.hive.ql.metadata.HiveException;

public abstract class AbstractRowContainer<Row> {

  public AbstractRowContainer() {

  }

  public abstract void add(Row t) throws HiveException;

  public abstract Row first() throws HiveException;

  public abstract Row next() throws HiveException;

  /**
   * Get the number of elements in the RowContainer.
   *
   * @return number of elements in the RowContainer
   */

  public abstract int size();

  /**
   * Remove all elements in the RowContainer.
   */

  public abstract void clear() throws HiveException;
}
