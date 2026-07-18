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


package org.apache.hadoop.hive.ql.parse;

/**
 * Join conditions Descriptor implementation.
 * 
 */
public class JoinCond {
  private int left;
  private int right;
  private JoinType joinType;
  private boolean preserved;

  public JoinCond() {
  }

  public JoinCond(int left, int right, JoinType joinType) {
    this.left = left;
    this.right = right;
    this.joinType = joinType;
  }

  /**
   * Constructor for a UNIQUEJOIN cond.
   * 
   * @param p
   *          true if table is preserved, false otherwise
   */
  public JoinCond(boolean p) {
    joinType = org.apache.hadoop.hive.ql.parse.JoinType.UNIQUE;
    preserved = p;
  }

  /**
   * @return the true if table is preserved, false otherwise
   */
  public boolean getPreserved() {
    return preserved;
  }

  public int getLeft() {
    return left;
  }

  public void setLeft(final int left) {
    this.left = left;
  }

  public int getRight() {
    return right;
  }

  public void setRight(final int right) {
    this.right = right;
  }

  public JoinType getJoinType() {
    return joinType;
  }

  public void setJoinType(final JoinType joinType) {
    this.joinType = joinType;
  }

}
