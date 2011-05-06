package org.apache.hadoop.hive.jdbc;

import java.sql.SQLException;
import java.util.ArrayList;

public interface HivePreparedStatementInterface {
  
  public String constructSQL() throws SQLException;
    
  public ArrayList<HiveParameterValue> getParameters();

}
