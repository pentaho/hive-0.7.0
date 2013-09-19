/*!
* Copyright 2010 - 2013 Pentaho Corporation.  All rights reserved.
*
* Licensed under the Apache License, Version 2.0 (the "License");
* you may not use this file except in compliance with the License.
* You may obtain a copy of the License at
*
* http://www.apache.org/licenses/LICENSE-2.0
*
* Unless required by applicable law or agreed to in writing, software
* distributed under the License is distributed on an "AS IS" BASIS,
* WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
* See the License for the specific language governing permissions and
* limitations under the License.
*
*/

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
