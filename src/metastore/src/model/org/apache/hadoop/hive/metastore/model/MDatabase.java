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

import java.util.Map;

/**
 * Storage Class representing the Hive MDatabase in a rdbms
 *
 */
public class MDatabase {
  private String name;
  private String locationUri;
  private String description;
  private Map<String, String> parameters;

  /**
   * Default construction to keep jpox/jdo happy
   */
  public MDatabase() {}

  /**
   * To create a database object
   * @param name of the database
   * @param locationUri Location of the database in the warehouse
   * @param description Comment describing the database
   */
  public MDatabase(String name, String locationUri, String description,
      Map<String, String> parameters) {
    this.name = name;
    this.locationUri = locationUri;
    this.description = description;
    this.parameters = parameters;
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
   * @return the location_uri
   */
  public String getLocationUri() {
    return locationUri;
  }

  /**
   * @param locationUri the locationUri to set
   */
  public void setLocationUri(String locationUri) {
    this.locationUri = locationUri;
  }

  /**
   * @return the description
   */
  public String getDescription() {
    return description;
  }

  /**
   * @param description the description to set
   */
  public void setDescription(String description) {
    this.description = description;
  }

  /**
   * @return the parameters mapping.
   */
  public Map<String, String> getParameters() {
    return parameters;
  }

  /**
   * @param parameters the parameters mapping.
   */
  public void setParameters(Map<String, String> parameters) {
    this.parameters = parameters;
  }
}
