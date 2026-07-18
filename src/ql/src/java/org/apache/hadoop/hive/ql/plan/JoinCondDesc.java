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

/**
 * Join conditions Descriptor implementation.
 * 
 */
public class JoinCondDesc implements Serializable {
  private static final long serialVersionUID = 1L;
  private int left;
  private int right;
  private int type;
  private boolean preserved;

  public JoinCondDesc() {
  }

  public JoinCondDesc(int left, int right, int type) {
    this.left = left;
    this.right = right;
    this.type = type;
  }

  public JoinCondDesc(org.apache.hadoop.hive.ql.parse.JoinCond condn) {
    left = condn.getLeft();
    right = condn.getRight();
    preserved = condn.getPreserved();
    switch (condn.getJoinType()) {
    case INNER:
      type = JoinDesc.INNER_JOIN;
      break;
    case LEFTOUTER:
      type = JoinDesc.LEFT_OUTER_JOIN;
      break;
    case RIGHTOUTER:
      type = JoinDesc.RIGHT_OUTER_JOIN;
      break;
    case FULLOUTER:
      type = JoinDesc.FULL_OUTER_JOIN;
      break;
    case UNIQUE:
      type = JoinDesc.UNIQUE_JOIN;
      break;
    case LEFTSEMI:
      type = JoinDesc.LEFT_SEMI_JOIN;
      break;
    default:
      assert false;
    }
  }

  /**
   * @return true if table is preserved, false otherwise
   */
  public boolean getPreserved() {
    return preserved;
  }

  /**
   * @param preserved
   *          if table is preserved, false otherwise
   */
  public void setPreserved(final boolean preserved) {
    this.preserved = preserved;
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

  public int getType() {
    return type;
  }

  public void setType(final int type) {
    this.type = type;
  }

  @Explain
  public String getJoinCondString() {
    StringBuilder sb = new StringBuilder();

    switch (type) {
    case JoinDesc.INNER_JOIN:
      sb.append("Inner Join ");
      break;
    case JoinDesc.FULL_OUTER_JOIN:
      sb.append("Outer Join ");
      break;
    case JoinDesc.LEFT_OUTER_JOIN:
      sb.append("Left Outer Join");
      break;
    case JoinDesc.RIGHT_OUTER_JOIN:
      sb.append("Right Outer Join");
      break;
    case JoinDesc.UNIQUE_JOIN:
      sb.append("Unique Join");
      break;
    case JoinDesc.LEFT_SEMI_JOIN:
      sb.append("Left Semi Join ");
      break;
    default:
      sb.append("Unknow Join ");
      break;
    }

    sb.append(left);
    sb.append(" to ");
    sb.append(right);

    return sb.toString();
  }
}
