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


package org.apache.hadoop.hive.ql.metadata;

/**
 * Hive consists of a fixed, well defined set of Dimensions. Each dimension has
 * a type and id. Dimensions link columns in different tables
 * 
 */
public class Dimension {

  protected Class<?> dimensionType;
  protected String dimensionId;

  public Dimension(Class<?> t, String id) {
    dimensionType = t;
    dimensionId = id;
  }

  public Class<?> getDimensionType() {
    return dimensionType;
  }

  public String getDimensionId() {
    return dimensionId;
  }

  @Override
  public boolean equals(Object o) {
    if (super.equals(o)) {
      return true;
    }
    if (o == null) {
      return false;
    }
    if (o instanceof Dimension) {
      Dimension d = (Dimension) o;
      return (dimensionId.equals(d.dimensionId) && (dimensionType == d.dimensionType));
    }
    return false;
  }

  @Override
  @SuppressWarnings("nls")
  public String toString() {
    return "Type=" + dimensionType.getName() + "," + "Id=" + dimensionId;
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result
        + ((dimensionId == null) ? 0 : dimensionId.hashCode());
    result = prime * result
        + ((dimensionType == null) ? 0 : dimensionType.hashCode());
    return result;
  }

  public int hashCode(Object o) {
    return dimensionType.hashCode() ^ dimensionId.hashCode();
  }
}
