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

public interface HivePreparedStatementInterface {
  
  public String constructSQL() throws SQLException;
    
  public ArrayList<HiveParameterValue> getParameters();

}
