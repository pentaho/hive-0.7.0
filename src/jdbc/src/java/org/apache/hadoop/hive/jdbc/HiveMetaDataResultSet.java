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


package org.apache.hadoop.hive.jdbc;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public abstract class HiveMetaDataResultSet<M> extends HiveBaseResultSet {
  protected final List<M> data;

  @SuppressWarnings("unchecked")
  public HiveMetaDataResultSet(final List<String> columnNames
          , final List<String> columnTypes
          , final List<M> data) throws SQLException {
    this.data = new ArrayList<M>(data);
    this.columnNames = new ArrayList<String>(columnNames);
    this.columnTypes = new ArrayList<String>(columnTypes);
  }

  @Override
  public void close() throws SQLException {
  }

}
