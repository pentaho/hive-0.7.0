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


package org.apache.hadoop.hive.metastore.model;

import java.util.Map;

public class MSerDeInfo {
  private String name;
  private String serializationLib;
  private Map<String, String> parameters;

  /**
   * @param name
   * @param serializationLib
   * @param parameters
   */
  public MSerDeInfo(String name, String serializationLib, Map<String, String> parameters) {
    this.name = name;
    this.serializationLib = serializationLib;
    this.parameters = parameters;
  }

  /**
   * @return the serializationLib
   */
  public String getSerializationLib() {
    return serializationLib;
  }

  /**
   * @param serializationLib the serializationLib to set
   */
  public void setSerializationLib(String serializationLib) {
    this.serializationLib = serializationLib;
  }

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
   * @return the parameters
   */
  public Map<String, String> getParameters() {
    return parameters;
  }

  /**
   * @param parameters the parameters to set
   */
  public void setParameters(Map<String, String> parameters) {
    this.parameters = parameters;
  }

}
