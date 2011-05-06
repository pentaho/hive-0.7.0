/**
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.apache.hadoop.hive.jdbc;

import java.io.InputStream;
import java.io.Reader;
import java.math.BigDecimal;
import java.net.URL;
import java.sql.Array;
import java.sql.Blob;
import java.sql.Clob;
import java.sql.Connection;
import java.sql.Date;
import java.sql.NClob;
import java.sql.ParameterMetaData;
import java.sql.PreparedStatement;
import java.sql.Ref;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.RowId;
import java.sql.SQLException;
import java.sql.SQLWarning;
import java.sql.SQLXML;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;

import org.apache.hadoop.hive.service.HiveInterface;
import org.apache.hadoop.hive.service.HiveServerException;

/**
 * HivePreparedStatement.
 *
 */
public class HivePreparedStatement implements PreparedStatement, HivePreparedStatementInterface {
  private String sql;
  private final JdbcSessionState session;
  private HiveInterface client;
  /**
   * We need to keep a reference to the result set to support the following:
   * <code>
   * statement.execute(String sql);
   * statement.getResultSet();
   * </code>.
   */
  private ResultSet resultSet = null;
  /**
   * The maximum number of rows this statement should return (0 => all rows).
   */
  private int maxRows = 0;

  /**
   * Add SQLWarnings to the warningChain if needed.
   */
  private SQLWarning warningChain = null;

  /**
   * Keep state so we can fail certain calls made after close().
   */
  private boolean isClosed = false;
  
  /**
   * 
   */
  ArrayList<HiveParameterValue> parameters = null;
  
  /**
   *
   */
  public HivePreparedStatement(JdbcSessionState session, HiveInterface client,
      String sql) {
    this.session = session;
    this.client = client;
    this.sql = sql.toLowerCase(Locale.ROOT).trim();
    parseParameters();
  }

  /*
   * (non-Javadoc)
   *
   * @see java.sql.PreparedStatement#addBatch()
   */

  public void addBatch() throws SQLException {
    // TODO Auto-generated method stub
    throw new SQLException("Method not supported");
  }

  /*
   * (non-Javadoc)
   *
   * @see java.sql.PreparedStatement#clearParameters()
   */

  public void clearParameters() throws SQLException {
    // TODO Auto-generated method stub
    // throw new SQLException("Method not supported");
  }

  /**
   *  Invokes executeQuery(sql) using the sql provided to the constructor.
   *
   *  @return boolean Returns true if a resultSet is created, false if not.
   *                  Note: If the result set is empty a true is returned.
   *
   *  @throws
   */

  public boolean execute() throws SQLException {
    ResultSet rs = null;
    if (parameters!=null && parameters.size() > 0) {
      rs = executeImmediate(constructSQL());
    }
    else {
      rs = executeImmediate(sql);
    }
  
    // TODO: this should really check if there are results, but there's no easy
    // way to do that without calling rs.next();
    return rs != null;
  }

  /**
   *  Invokes executeQuery(sql) using the sql provided to the constructor.
   *
   *  @return ResultSet
   *  @throws
   */

  public ResultSet executeQuery() throws SQLException {
    
    ResultSet rs = null;
    if (parameters!=null && parameters.size() > 0) {
      rs = executeImmediate(constructSQL());
    }
    else {
      rs = executeImmediate(sql);
    }
    
    return rs;
  }

  /*
   * (non-Javadoc)
   * 
   * @see java.sql.PreparedStatement#executeUpdate()
   */
  public int executeUpdate() throws SQLException {
    
    ResultSet resultSet = executeImmediate(constructSQL());
    
    int rowsUpdated = 0; // how do I get this value?

    return rowsUpdated;
  }

  /**
   *  Executes the SQL statement.
   *
   *  @param sql The sql, as a string, to execute
   *  @return ResultSet
   *  @throws SQLException if the prepared statement is closed or there is a database error.
   *                       caught Exceptions are thrown as SQLExceptions with the description
   *                       "08S01".
   */

  protected ResultSet executeImmediate(String sql) throws SQLException {
    if (isClosed) {
      throw new SQLException("Can't execute after statement has been closed");
    }

    try {
      clearWarnings();
      resultSet = null;
      client.execute(sql);
    } catch (HiveServerException e) {
      throw new SQLException(e.getMessage(), e.getSQLState(), e.getErrorCode());
    } catch (Exception ex) {
      throw new SQLException(ex.toString(), "08S01");
    }
    resultSet = new HiveQueryResultSet(client, this, maxRows);
    return resultSet;
  }



  /**
   *  Returns the result set meta data.  If a result set was not created by running an execute or executeQuery then a null is returned.
   *
   *  @return null is returned if the result set is null
   *  @throws
   */

  public ResultSetMetaData getMetaData() throws SQLException {
    return resultSet == null ? null : resultSet.getMetaData();
  }

  /*
   * (non-Javadoc)
   *
   * @see java.sql.PreparedStatement#getParameterMetaData()
   */

  public ParameterMetaData getParameterMetaData() throws SQLException {
    // TODO Auto-generated method stub
    throw new SQLException("Method not supported");
  }

  /*
   * (non-Javadoc)
   *
   * @see java.sql.PreparedStatement#setArray(int, java.sql.Array)
   */

  public void setArray(int i, Array x) throws SQLException {
    // TODO Auto-generated method stub
    throw new SQLException("Method not supported");
  }

  /*
   * (non-Javadoc)
   *
   * @see java.sql.PreparedStatement#setAsciiStream(int, java.io.InputStream)
   */

  public void setAsciiStream(int parameterIndex, InputStream x) throws SQLException {
    // TODO Auto-generated method stub
    throw new SQLException("Method not supported");
  }

  /*
   * (non-Javadoc)
   *
   * @see java.sql.PreparedStatement#setAsciiStream(int, java.io.InputStream,
   * int)
   */

  public void setAsciiStream(int parameterIndex, InputStream x, int length) throws SQLException {
    // TODO Auto-generated method stub
    throw new SQLException("Method not supported");
  }

  /*
   * (non-Javadoc)
   *
   * @see java.sql.PreparedStatement#setAsciiStream(int, java.io.InputStream,
   * long)
   */

  public void setAsciiStream(int parameterIndex, InputStream x, long length) throws SQLException {
    // TODO Auto-generated method stub
    throw new SQLException("Method not supported");
  }

  /*
   * (non-Javadoc)
   *
   * @see java.sql.PreparedStatement#setBigDecimal(int, java.math.BigDecimal)
   */

  public void setBigDecimal(int parameterIndex, BigDecimal x) throws SQLException {
    // TODO Auto-generated method stub
    throw new SQLException("Method not supported");
  }

  /*
   * (non-Javadoc)
   *
   * @see java.sql.PreparedStatement#setBinaryStream(int, java.io.InputStream)
   */

  public void setBinaryStream(int parameterIndex, InputStream x) throws SQLException {
    // TODO Auto-generated method stub
    throw new SQLException("Method not supported");
  }

  /*
   * (non-Javadoc)
   *
   * @see java.sql.PreparedStatement#setBinaryStream(int, java.io.InputStream,
   * int)
   */

  public void setBinaryStream(int parameterIndex, InputStream x, int length) throws SQLException {
    // TODO Auto-generated method stub
    throw new SQLException("Method not supported");
  }

  /*
   * (non-Javadoc)
   *
   * @see java.sql.PreparedStatement#setBinaryStream(int, java.io.InputStream,
   * long)
   */

  public void setBinaryStream(int parameterIndex, InputStream x, long length) throws SQLException {
    // TODO Auto-generated method stub
    throw new SQLException("Method not supported");
  }

  /*
   * (non-Javadoc)
   *
   * @see java.sql.PreparedStatement#setBlob(int, java.sql.Blob)
   */

  public void setBlob(int i, Blob x) throws SQLException {
    // TODO Auto-generated method stub
    throw new SQLException("Method not supported");
  }

  /*
   * (non-Javadoc)
   *
   * @see java.sql.PreparedStatement#setBlob(int, java.io.InputStream)
   */

  public void setBlob(int parameterIndex, InputStream inputStream) throws SQLException {
    // TODO Auto-generated method stub
    throw new SQLException("Method not supported");
  }

  /*
   * (non-Javadoc)
   *
   * @see java.sql.PreparedStatement#setBlob(int, java.io.InputStream, long)
   */

  public void setBlob(int parameterIndex, InputStream inputStream, long length)
          throws SQLException {
    // TODO Auto-generated method stub
    throw new SQLException("Method not supported");
  }

  /*
   * (non-Javadoc)
   *
   * @see java.sql.PreparedStatement#setBoolean(int, boolean)
   */

  public void setBoolean(int parameterIndex, boolean x) throws SQLException {
    // TODO Auto-generated method stub
    throw new SQLException("Method not supported");
  }

  /*
   * (non-Javadoc)
   *
   * @see java.sql.PreparedStatement#setByte(int, byte)
   */

  public void setByte(int parameterIndex, byte x) throws SQLException {
    // TODO Auto-generated method stub
    throw new SQLException("Method not supported");
  }

  /*
   * (non-Javadoc)
   *
   * @see java.sql.PreparedStatement#setBytes(int, byte[])
   */

  public void setBytes(int parameterIndex, byte[] x) throws SQLException {
    // TODO Auto-generated method stub
    throw new SQLException("Method not supported");
  }

  /*
   * (non-Javadoc)
   *
   * @see java.sql.PreparedStatement#setCharacterStream(int, java.io.Reader)
   */

  public void setCharacterStream(int parameterIndex, Reader reader) throws SQLException {
    // TODO Auto-generated method stub
    throw new SQLException("Method not supported");
  }

  /*
   * (non-Javadoc)
   *
   * @see java.sql.PreparedStatement#setCharacterStream(int, java.io.Reader,
   * int)
   */

  public void setCharacterStream(int parameterIndex, Reader reader, int length)
      throws SQLException {
    // TODO Auto-generated method stub
    throw new SQLException("Method not supported");
  }

  /*
   * (non-Javadoc)
   *
   * @see java.sql.PreparedStatement#setCharacterStream(int, java.io.Reader,
   * long)
   */

  public void setCharacterStream(int parameterIndex, Reader reader, long length)
      throws SQLException {
    // TODO Auto-generated method stub
    throw new SQLException("Method not supported");
  }

  /*
   * (non-Javadoc)
   *
   * @see java.sql.PreparedStatement#setClob(int, java.sql.Clob)
   */

  public void setClob(int i, Clob x) throws SQLException {
    // TODO Auto-generated method stub
    throw new SQLException("Method not supported");
  }

  /*
   * (non-Javadoc)
   *
   * @see java.sql.PreparedStatement#setClob(int, java.io.Reader)
   */

  public void setClob(int parameterIndex, Reader reader) throws SQLException {
    // TODO Auto-generated method stub
    throw new SQLException("Method not supported");
  }

  /*
   * (non-Javadoc)
   *
   * @see java.sql.PreparedStatement#setClob(int, java.io.Reader, long)
   */

  public void setClob(int parameterIndex, Reader reader, long length) throws SQLException {
    // TODO Auto-generated method stub
    throw new SQLException("Method not supported");
  }

  /*
   * (non-Javadoc)
   *
   * @see java.sql.PreparedStatement#setDate(int, java.sql.Date)
   */

  public void setDate(int parameterIndex, Date x) throws SQLException {
    // TODO Auto-generated method stub
    throw new SQLException("Method not supported");
  }

  /*
   * (non-Javadoc)
   *
   * @see java.sql.PreparedStatement#setDate(int, java.sql.Date,
   * java.util.Calendar)
   */

  public void setDate(int parameterIndex, Date x, Calendar cal) throws SQLException {
    // TODO Auto-generated method stub
    throw new SQLException("Method not supported");
  }

  /*
   * (non-Javadoc)
   *
   * @see java.sql.PreparedStatement#setDouble(int, double)
   */

  public void setDouble(int parameterIndex, double x) throws SQLException {
    setHiveParameter(parameters.get(parameterIndex-1), double.class.getName(), java.sql.Types.DOUBLE, x);
    

  }

  /*
   * (non-Javadoc)
   *
   * @see java.sql.PreparedStatement#setFloat(int, float)
   */

  public void setFloat(int parameterIndex, float x) throws SQLException {
    setHiveParameter(parameters.get(parameterIndex-1), float.class.getName(), java.sql.Types.FLOAT, x);
  }

  /*
   * (non-Javadoc)
   *
   * @see java.sql.PreparedStatement#setInt(int, int)
   */

  public void setInt(int parameterIndex, int x) throws SQLException {
    setHiveParameter(parameters.get(parameterIndex-1), int.class.getName(), java.sql.Types.INTEGER, x);
  }

  /*
   * (non-Javadoc)
   *
   * @see java.sql.PreparedStatement#setLong(int, long)
   */

  public void setLong(int parameterIndex, long x) throws SQLException {
    setHiveParameter(parameters.get(parameterIndex-1), long.class.getName(), java.sql.Types.INTEGER, x);
  }

  /*
   * (non-Javadoc)
   *
   * @see java.sql.PreparedStatement#setNCharacterStream(int, java.io.Reader)
   */

  public void setNCharacterStream(int parameterIndex, Reader value) throws SQLException {
    // TODO Auto-generated method stub
    throw new SQLException("Method not supported");
  }

  /*
   * (non-Javadoc)
   *
   * @see java.sql.PreparedStatement#setNCharacterStream(int, java.io.Reader,
   * long)
   */

  public void setNCharacterStream(int parameterIndex, Reader value, long length)
      throws SQLException {
    // TODO Auto-generated method stub
    throw new SQLException("Method not supported");
  }

  /*
   * (non-Javadoc)
   *
   * @see java.sql.PreparedStatement#setNClob(int, java.sql.NClob)
   */

  public void setNClob(int parameterIndex, NClob value) throws SQLException {
    // TODO Auto-generated method stub
    throw new SQLException("Method not supported");
  }

  /*
   * (non-Javadoc)
   *
   * @see java.sql.PreparedStatement#setNClob(int, java.io.Reader)
   */

  public void setNClob(int parameterIndex, Reader reader) throws SQLException {
    // TODO Auto-generated method stub
    throw new SQLException("Method not supported");
  }

  /*
   * (non-Javadoc)
   *
   * @see java.sql.PreparedStatement#setNClob(int, java.io.Reader, long)
   */

  public void setNClob(int parameterIndex, Reader reader, long length) throws SQLException {
    // TODO Auto-generated method stub
    throw new SQLException("Method not supported");
  }

  /*
   * (non-Javadoc)
   *
   * @see java.sql.PreparedStatement#setNString(int, java.lang.String)
   */

  public void setNString(int parameterIndex, String value) throws SQLException {
    // TODO Auto-generated method stub
    throw new SQLException("Method not supported");
  }

  /*
   * (non-Javadoc)
   *
   * @see java.sql.PreparedStatement#setNull(int, int)
   */

  public void setNull(int parameterIndex, int sqlType) throws SQLException {
    HiveParameterValue hiveParameterValue =  parameters.get(parameterIndex-1);
    hiveParameterValue.setSqlType(sqlType);
    hiveParameterValue.setValue(null);
  }

  /*
   * (non-Javadoc)
   *
   * @see java.sql.PreparedStatement#setNull(int, int, java.lang.String)
   */

  public void setNull(int paramIndex, int sqlType, String typeName)
      throws SQLException {
    
    HiveParameterValue hiveParameterValue =  parameters.get(paramIndex-1);
    hiveParameterValue.setSqlType(sqlType);
    hiveParameterValue.setJavaType(typeName);
    hiveParameterValue.setValue(null);
  }

  /*
   * (non-Javadoc)
   *
   * @see java.sql.PreparedStatement#setObject(int, java.lang.Object)
   */

  public void setObject(int parameterIndex, Object x) throws SQLException {
    baseSetObject(parameterIndex, x, null, null);
  }

  /*
   * (non-Javadoc)
   *
   * @see java.sql.PreparedStatement#setObject(int, java.lang.Object, int)
   */

  public void setObject(int parameterIndex, Object x, int targetSqlType)
      throws SQLException {
    baseSetObject(parameterIndex, x, targetSqlType, null);
  }

  /*
   * (non-Javadoc)
   *
   * @see java.sql.PreparedStatement#setObject(int, java.lang.Object, int, int)
   */

  public void setObject(int parameterIndex, Object x, int targetSqlType,
      int scale) throws SQLException {
    baseSetObject(parameterIndex, x, targetSqlType, scale);
  }
  
  protected void baseSetObject(Integer parameterIndex, Object x, Integer targetSqlType,
      Integer scale) throws SQLException {
    
    int sqlType = targetSqlType == null ? java.sql.Types.OTHER : targetSqlType;
    int typeMatches = 0;
    
    if(x instanceof Short) {
      typeMatches++;
      sqlType = java.sql.Types.TINYINT;
    }
    if(x instanceof Integer) {
      typeMatches++;
      sqlType = java.sql.Types.INTEGER;
    }
    if(x instanceof Long) {
      typeMatches++;
      sqlType = java.sql.Types.INTEGER;
    }
    if(x instanceof Double) {
      typeMatches++;
      sqlType = java.sql.Types.DOUBLE;
    }
    if(x instanceof Float) {
      typeMatches++;
      sqlType = java.sql.Types.FLOAT;
    }
    if(x instanceof String) {
      typeMatches++;
      if(scale != null) {
        sqlType = java.sql.Types.CHAR;
      } else {
        sqlType = java.sql.Types.VARCHAR;
      }
    }
    // TODO: Add more mappings
    //  Ref, Blob, Clob, NClob, Struct, java.net.URL, RowId, SQLXML, Array, SQLData
    setHiveParameter(parameters.get(parameterIndex-1), x != null ? x.getClass().getName() : java.lang.Object.class.getName(), sqlType, x);
  }

  /*
   * (non-Javadoc)
   *
   * @see java.sql.PreparedStatement#setRef(int, java.sql.Ref)
   */

  public void setRef(int i, Ref x) throws SQLException {
    // TODO Auto-generated method stub
    throw new SQLException("Method not supported");
  }

  /*
   * (non-Javadoc)
   *
   * @see java.sql.PreparedStatement#setRowId(int, java.sql.RowId)
   */

  public void setRowId(int parameterIndex, RowId x) throws SQLException {
    // TODO Auto-generated method stub
    throw new SQLException("Method not supported");
  }

  /*
   * (non-Javadoc)
   *
   * @see java.sql.PreparedStatement#setSQLXML(int, java.sql.SQLXML)
   */

  public void setSQLXML(int parameterIndex, SQLXML xmlObject) throws SQLException {
    // TODO Auto-generated method stub
    throw new SQLException("Method not supported");
  }

  /*
   * (non-Javadoc)
   *
   * @see java.sql.PreparedStatement#setShort(int, short)
   */

  public void setShort(int parameterIndex, short x) throws SQLException {
    setHiveParameter(parameters.get(parameterIndex-1), "short", java.sql.Types.TINYINT,  x);
  }

  /*
   * (non-Javadoc)
   *
   * @see java.sql.PreparedStatement#setString(int, java.lang.String)
   */

  public void setString(int parameterIndex, String x) throws SQLException {
    setHiveParameter(parameters.get(parameterIndex-1), "String", java.sql.Types.VARCHAR, x);
  }

  /*
   * (non-Javadoc)
   *
   * @see java.sql.PreparedStatement#setTime(int, java.sql.Time)
   */

  public void setTime(int parameterIndex, Time x) throws SQLException {
    // TODO Auto-generated method stub
    throw new SQLException("Method not supported");
  }

  /*
   * (non-Javadoc)
   *
   * @see java.sql.PreparedStatement#setTime(int, java.sql.Time,
   * java.util.Calendar)
   */

  public void setTime(int parameterIndex, Time x, Calendar cal) throws SQLException {
    // TODO Auto-generated method stub
    throw new SQLException("Method not supported");
  }

  /*
   * (non-Javadoc)
   *
   * @see java.sql.PreparedStatement#setTimestamp(int, java.sql.Timestamp)
   */

  public void setTimestamp(int parameterIndex, Timestamp x) throws SQLException {
    // TODO Auto-generated method stub
    throw new SQLException("Method not supported");
  }

  /*
   * (non-Javadoc)
   *
   * @see java.sql.PreparedStatement#setTimestamp(int, java.sql.Timestamp,
   * java.util.Calendar)
   */

  public void setTimestamp(int parameterIndex, Timestamp x, Calendar cal)
      throws SQLException {
    // TODO Auto-generated method stub
    throw new SQLException("Method not supported");
  }

  /*
   * (non-Javadoc)
   *
   * @see java.sql.PreparedStatement#setURL(int, java.net.URL)
   */

  public void setURL(int parameterIndex, URL x) throws SQLException {
    // TODO Auto-generated method stub
    throw new SQLException("Method not supported");
  }

  /*
   * (non-Javadoc)
   *
   * @see java.sql.PreparedStatement#setUnicodeStream(int, java.io.InputStream,
   * int)
   */

  public void setUnicodeStream(int parameterIndex, InputStream x, int length)
      throws SQLException {
    // TODO Auto-generated method stub
    throw new SQLException("Method not supported");
  }

  /*
   * (non-Javadoc)
   *
   * @see java.sql.Statement#addBatch(java.lang.String)
   */

  public void addBatch(String sql) throws SQLException {
    // TODO Auto-generated method stub
    throw new SQLException("Method not supported");
  }

  /*
   * (non-Javadoc)
   *
   * @see java.sql.Statement#cancel()
   */

  public void cancel() throws SQLException {
    // TODO Auto-generated method stub
    throw new SQLException("Method not supported");
  }

  /*
   * (non-Javadoc)
   *
   * @see java.sql.Statement#clearBatch()
   */

  public void clearBatch() throws SQLException {
    // TODO Auto-generated method stub
    throw new SQLException("Method not supported");
  }

  /*
   * (non-Javadoc)
   * 
   * @see java.sql.Statement#clearWarnings()
   */

  public void clearWarnings() throws SQLException {
    warningChain = null;
  }

  /**
   *  Closes the prepared statement.
   *
   *  @throws
   */

  public void close() throws SQLException {
    client = null;
    if (resultSet!=null) {
      resultSet.close();
      resultSet = null;
    }
    isClosed = true;
  }

  /*
   * (non-Javadoc)
   * 
   * @see java.sql.Statement#execute(java.lang.String)
   */

  public boolean execute(String sql) throws SQLException {
    this.sql = sql;
    ResultSet rs = executeQuery(sql);

    // TODO: this should really check if there are results, but there's no easy
    // way to do that without calling rs.next();
    return rs != null;
  }

  /*
   * (non-Javadoc)
   * 
   * @see java.sql.Statement#execute(java.lang.String, int)
   */

  public boolean execute(String sql, int autoGeneratedKeys) throws SQLException {
    // TODO Auto-generated method stub
    throw new SQLException("Method not supported");
  }

  /*
   * (non-Javadoc)
   *
   * @see java.sql.Statement#execute(java.lang.String, int[])
   */

  public boolean execute(String sql, int[] columnIndexes) throws SQLException {
    // TODO Auto-generated method stub
    throw new SQLException("Method not supported");
  }

  /*
   * (non-Javadoc)
   *
   * @see java.sql.Statement#execute(java.lang.String, java.lang.String[])
   */

  public boolean execute(String sql, String[] columnNames) throws SQLException {
    // TODO Auto-generated method stub
    throw new SQLException("Method not supported");
  }

  /*
   * (non-Javadoc)
   *
   * @see java.sql.Statement#executeBatch()
   */

  public int[] executeBatch() throws SQLException {
    // TODO Auto-generated method stub
    throw new SQLException("Method not supported");
  }

  /**
   *  Invokes executeQuery(sql) using the passed sql and returns the result set.
   *
   *  @param sql The sql, as a string, to execute
   *  @return boolean Returns true if a resultSet is created, false if not.
   *                  Note: If the result set is empty a true is returned.
   *  @throws SQLException if the prepared statement is closed or there is a database error.
   *                       caught Exceptions are thrown as SQLExceptioms with the description
   *                       "08S01".
   */

  public ResultSet executeQuery(String sql) throws SQLException {
    if (isClosed) {
      throw new SQLException("Can't execute after statement has been closed");
    }

    try {
      resultSet = null;
      client.execute(sql);
    } catch (HiveServerException e) {
      throw new SQLException(e.getMessage(), e.getSQLState(), e.getErrorCode());
    } catch (Exception ex) {
      throw new SQLException(ex.toString(), "08S01");
    }
    resultSet = new HiveQueryResultSet(client, maxRows);
    return resultSet;
  }

  /*
   * (non-Javadoc)
   *
   * @see java.sql.Statement#executeUpdate(java.lang.String)
   */

  public int executeUpdate(String sql) throws SQLException {
    // TODO Auto-generated method stub
    throw new SQLException("Method not supported");
  }

  /*
   * (non-Javadoc)
   *
   * @see java.sql.Statement#executeUpdate(java.lang.String, int)
   */

  public int executeUpdate(String sql, int autoGeneratedKeys) throws SQLException {
    // TODO Auto-generated method stub
    throw new SQLException("Method not supported");
  }

  /*
   * (non-Javadoc)
   *
   * @see java.sql.Statement#executeUpdate(java.lang.String, int[])
   */

  public int executeUpdate(String sql, int[] columnIndexes) throws SQLException {
    // TODO Auto-generated method stub
    throw new SQLException("Method not supported");
  }

  /*
   * (non-Javadoc)
   *
   * @see java.sql.Statement#executeUpdate(java.lang.String, java.lang.String[])
   */

  public int executeUpdate(String sql, String[] columnNames) throws SQLException {
    // TODO Auto-generated method stub
    throw new SQLException("Method not supported");
  }

  /*
   * (non-Javadoc)
   *
   * @see java.sql.Statement#getConnection()
   */

  public Connection getConnection() throws SQLException {
    // TODO Auto-generated method stub
    throw new SQLException("Method not supported");
  }

  /*
   * (non-Javadoc)
   *
   * @see java.sql.Statement#getFetchDirection()
   */

  public int getFetchDirection() throws SQLException {
    // TODO Auto-generated method stub
    throw new SQLException("Method not supported");
  }

  /*
   * (non-Javadoc)
   *
   * @see java.sql.Statement#getFetchSize()
   */

  public int getFetchSize() throws SQLException {
    // TODO Auto-generated method stub
    throw new SQLException("Method not supported");
  }

  /*
   * (non-Javadoc)
   *
   * @see java.sql.Statement#getGeneratedKeys()
   */

  public ResultSet getGeneratedKeys() throws SQLException {
    // TODO Auto-generated method stub
    throw new SQLException("Method not supported");
  }

  /*
   * (non-Javadoc)
   *
   * @see java.sql.Statement#getMaxFieldSize()
   */

  public int getMaxFieldSize() throws SQLException {
    // TODO Auto-generated method stub
    throw new SQLException("Method not supported");
  }

  /**
   * Retrurns the value of maxRows.
   *
   * @return
   */

  public int getMaxRows() throws SQLException {
    return maxRows;
  }

  /*
   * (non-Javadoc)
   *
   * @see java.sql.Statement#getMoreResults()
   */

  public boolean getMoreResults() throws SQLException {
    // TODO Auto-generated method stub
    throw new SQLException("Method not supported");
  }

  /*
   * (non-Javadoc)
   *
   * @see java.sql.Statement#getMoreResults(int)
   */

  public boolean getMoreResults(int current) throws SQLException {
    // TODO Auto-generated method stub
    throw new SQLException("Method not supported");
  }

  /*
   * (non-Javadoc)
   *
   * @see java.sql.Statement#getQueryTimeout()
   */

  public int getQueryTimeout() throws SQLException {
    // TODO Auto-generated method stub
    throw new SQLException("Method not supported");
  }

  /**
   *  Returns the resultSet.
   *
   *  @return
   */

  public ResultSet getResultSet() throws SQLException {
    return resultSet;
  }

  /*
   * (non-Javadoc)
   *
   * @see java.sql.Statement#getResultSetConcurrency()
   */

  public int getResultSetConcurrency() throws SQLException {
    // TODO Auto-generated method stub
    throw new SQLException("Method not supported");
  }

  /*
   * (non-Javadoc)
   *
   * @see java.sql.Statement#getResultSetHoldability()
   */

  public int getResultSetHoldability() throws SQLException {
    // TODO Auto-generated method stub
    throw new SQLException("Method not supported");
  }

  /*
   * (non-Javadoc)
   *
   * @see java.sql.Statement#getResultSetType()
   */

  public int getResultSetType() throws SQLException {
    // TODO Auto-generated method stub
    throw new SQLException("Method not supported");
  }

  /*
   * (non-Javadoc)
   *
   * @see java.sql.Statement#getUpdateCount()
   */

  public int getUpdateCount() throws SQLException {
    // throw new SQLException("Method not supported");
    // Updated to Pentaho's code:
    return 0;
  }

  /**
   * Returns the SQL warning chain
   *
   * @return
   */

  public SQLWarning getWarnings() throws SQLException {
    return warningChain;
  }

  /**
   * Returns a boolean value that idndicates of the statement is closed.
   *
   * @return
   */

  public boolean isClosed() throws SQLException {
    return isClosed;
  }

  /*
   * (non-Javadoc)
   *
   * @see java.sql.Statement#isPoolable()
   */

  public boolean isPoolable() throws SQLException {
    // TODO Auto-generated method stub
    throw new SQLException("Method not supported");
  }

  /*
   * (non-Javadoc)
   *
   * @see java.sql.Statement#setCursorName(java.lang.String)
   */

  public void setCursorName(String name) throws SQLException {
    // TODO Auto-generated method stub
    throw new SQLException("Method not supported");
  }

  /*
   * (non-Javadoc)
   *
   * @see java.sql.Statement#setEscapeProcessing(boolean)
   */

  public void setEscapeProcessing(boolean enable) throws SQLException {
    // TODO Auto-generated method stub
    throw new SQLException("Method not supported");
  }

  /*
   * (non-Javadoc)
   *
   * @see java.sql.Statement#setFetchDirection(int)
   */

  public void setFetchDirection(int direction) throws SQLException {
    // TODO Auto-generated method stub
    throw new SQLException("Method not supported");
  }

  /*
   * (non-Javadoc)
   *
   * @see java.sql.Statement#setFetchSize(int)
   */

  public void setFetchSize(int rows) throws SQLException {
    // TODO Auto-generated method stub
    throw new SQLException("Method not supported");
  }

  /*
   * (non-Javadoc)
   *
   * @see java.sql.Statement#setMaxFieldSize(int)
   */

  public void setMaxFieldSize(int max) throws SQLException {
    // TODO Auto-generated method stub
    throw new SQLException("Method not supported");
  }

  /**
   *  Sets the limit for the maximum number of rows that any ResultSet can obtain.
   *
   *  @param max - the maximum number of rows that a result set can have.
   *  @throws
   */

  public void setMaxRows(int max) throws SQLException {
    if (max < 0) {
      throw new SQLException("max must be >= 0");
    }
    maxRows = max;
  }

  /*
   * (non-Javadoc)
   *
   * @see java.sql.Statement#setPoolable(boolean)
   */

  public void setPoolable(boolean poolable) throws SQLException {
    // TODO Auto-generated method stub
    throw new SQLException("Method not supported");
  }

  /*
   * (non-Javadoc)
   *
   * @see java.sql.Statement#setQueryTimeout(int)
   */

  public void setQueryTimeout(int seconds) throws SQLException {
    // TODO Auto-generated method stub
    // throw new SQLException("Method not supported");
  }

  /*
   * (non-Javadoc)
   *
   * @see java.sql.Wrapper#isWrapperFor(java.lang.Class)
   */

  public boolean isWrapperFor(Class<?> iface) throws SQLException {
    // TODO Auto-generated method stub
    throw new SQLException("Method not supported");
  }

  /*
   * (non-Javadoc)
   *
   * @see java.sql.Wrapper#unwrap(java.lang.Class)
   */

  public <T> T unwrap(Class<T> iface) throws SQLException {
    // TODO Auto-generated method stub
    throw new SQLException("Method not supported");
  } 
   
  /**
   * 
   */
  private void parseParameters() {
    String sqlToHack = sql; 
    parameters = new ArrayList<HiveParameterValue>();
    int parameterPos = sqlToHack.indexOf('?');
    while (parameterPos > 0) {
      parameters.add(new HiveParameterValue());
      sqlToHack = sqlToHack.substring(parameterPos+1);
      parameterPos = sqlToHack.indexOf('?');
    }
  }

  @Override
  public ArrayList<HiveParameterValue> getParameters() {
    return this.parameters;
  }
  
  
  /**
   * Sets the javaType and value of the hiveParameter.
   * 
   * @param hiveParameter
   * @param javaType
   * @param value
   */
  private void setHiveParameter(HiveParameterValue hiveParameterValue, String javaType, int sqlType, Object value) {
    hiveParameterValue.setJavaType(javaType);
    hiveParameterValue.setValue(value);
    hiveParameterValue.setSqlType(sqlType);
  }
  
  
  /**
   * Constructs the SQL statement from the parameter list 
   * and the SQL property. 
   * 
   * @param hivePreparedStatement
   * @return String A SQL statement with the parameters repalces by the valuews that were set
   */
  @Override
  public String constructSQL() 
        throws SQLException {

      StringBuilder sqlToConstruct = new StringBuilder();
      String sqlToHack = sql;
      int parameterPosition = 0;
            
      for(HiveParameterValue hiveParameterValue: parameters) {
        
        //  get get the position of the next place holder
        parameterPosition = sqlToHack.indexOf("?");

        //  if we have no more place holders than parameters- which should not happen
        if (parameterPosition <=0 ) {
          throw new SQLException("More parameters have been set than have placeholders.");
        }
        
        //  append the characters leading up to the place holder to the sql we are constructing
        sqlToConstruct.append(sqlToHack.substring(0, parameterPosition-1));        
        
        //  if we need encolusures..
        if (useStringEnclosure(hiveParameterValue.getSqlType())) {
          
          //  append the value enclosed with single quotes to the sql 
          sqlToConstruct.append("'");
          sqlToConstruct.append(hiveParameterValue.getValueAsString());
          sqlToConstruct.append("'");
        }
        else {
          
          //  just append the value to the sql 
          sqlToConstruct.append(hiveParameterValue.getValueAsString());
        }
        
        //  now hack off the before the found place holder so 
        //  we can look for the next one.
        sqlToHack = sqlToHack.substring(parameterPosition+1);
    }
   
    //  append anything left of the hacked up SQL 
    sqlToConstruct.append(sqlToHack);

    //  return the constructed sql
    return sqlToConstruct.toString();
  }

  /**
   * Returns a true of the passed SQLType requires a string enclosure.  Usually this
   * is a single quote:  Example:  select * from tabe where name = 'Joe'.  
   * The name column is a character type and the SQL statement requires that 'Joe'
   * be quoted.
   * 
   * @param SQLType
   * @return boolean
   */
  private boolean useStringEnclosure(int SQLType) {
    
    switch (SQLType) {
      
      case java.sql.Types.CHAR: 
           return true;
      
      case java.sql.Types.VARCHAR: 
           return true;
      
      default: return false;
    }   
  }
  
}