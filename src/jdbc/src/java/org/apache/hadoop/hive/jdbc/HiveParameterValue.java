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

public class HiveParameterValue {
  String javaType;
  int sqlType;
  Object value;
  int scaleOrLength;
  
  /**
   * 
   * @return
   */
  public int getScaleOrLength() {
    return scaleOrLength;
  }

  /**
   * 
   * @param type
   */
  public void setScaleOrLength(int scaleOrLength) {
    this.scaleOrLength = scaleOrLength;
  }

  /**
   * 
   * @param type
   */
  public void setJavaType(String javaType) {
    this.javaType = javaType; 
  }
  
  /**
   * 
   * @return
   */
  public String getjavaType() {
    return javaType;
  }

  /**
   * 
   * @param type
   */
  public void setSqlType(int sqlType) {
    this.sqlType = sqlType; 
  }
  
  /**
   * 
   * @return
   */
  public int getSqlType() {
    return sqlType;
  }
  
  /**
   * 
   * @param value
   */
  public void setValue(Object value) {
    this.value = value;
  }
  
  /**
   * 
   * @return
   */
  public Object getValue() {
    return value;
  }
  
  /**
   * 
   * @return
   */
  public String getValueAsString() {
    return (value==null?"NULL":value.toString());
  }
  
}
