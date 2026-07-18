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


package org.apache.hadoop.hive.ql.plan;

import java.io.Serializable;
import java.util.List;

/**
 * ConditionalWork.
 *
 */
@Explain(displayName = "Conditional Operator")
public class ConditionalWork implements Serializable {
  private static final long serialVersionUID = 1L;
  List<? extends Serializable> listWorks;

  public ConditionalWork() {
  }

  public ConditionalWork(final List<? extends Serializable> listWorks) {
    this.listWorks = listWorks;
  }

  /**
   * @return the listWorks
   */
  public List<? extends Serializable> getListWorks() {
    return listWorks;
  }

  /**
   * @param listWorks
   *          the listWorks to set
   */
  public void setListWorks(List<? extends Serializable> listWorks) {
    this.listWorks = listWorks;
  }
}
