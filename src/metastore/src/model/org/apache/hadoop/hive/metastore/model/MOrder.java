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

public class MOrder {
  private String col;
  private int order;
  
  /**
   * @param col
   * @param order
   */
  public MOrder(String col, int order) {
    this.col = col;
    this.order = order;
  }

  /**
   * @return the col
   */
  public String getCol() {
    return col;
  }

  /**
   * @param col the col to set
   */
  public void setCol(String col) {
    this.col = col;
  }

  /**
   * @return the order
   */
  public int getOrder() {
    return order;
  }

  /**
   * @param order the order to set
   */
  public void setOrder(int order) {
    this.order = order;
  }
  
}
