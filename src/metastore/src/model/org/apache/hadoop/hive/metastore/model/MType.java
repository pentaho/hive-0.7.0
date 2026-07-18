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


/**
 * 
 */

package org.apache.hadoop.hive.metastore.model;

import java.util.List;

/**
 * Represents a Hive type
 *
 */
public class MType {
  private String name;
  private String type1;
  private String type2;
  private List<MFieldSchema> fields;
  
  /**
   * @param name
   * @param type1
   * @param type2
   * @param fields
   */
  public MType(String name, String type1, String type2, List<MFieldSchema> fields) {
    this.name = name;
    this.type1 = type1;
    this.type2 = type2;
    this.fields = fields;
  }

  public MType() {}
  
  /**
   * @return the name
   */
  public String getName() {
    return name;
  }
  /**
   * @param name the name to set
   */
  public void setName(String name) {
    this.name = name;
  }
  /**
   * @return the type1
   */
  public String getType1() {
    return type1;
  }

  /**
   * @param type1 the type1 to set
   */
  public void setType1(String type1) {
    this.type1 = type1;
  }

  /**
   * @return the type2
   */
  public String getType2() {
    return type2;
  }

  /**
   * @param type2 the type2 to set
   */
  public void setType2(String type2) {
    this.type2 = type2;
  }

  /**
   * @return the fields
   */
  public List<MFieldSchema> getFields() {
    return fields;
  }
  /**
   * @param fields the fields to set
   */
  public void setFields(List<MFieldSchema> fields) {
    this.fields = fields;
  }
  
  
}
