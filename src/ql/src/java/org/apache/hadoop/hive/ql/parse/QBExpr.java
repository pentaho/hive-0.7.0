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

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Implementation of the query block expression.
 * 
 **/

public class QBExpr {

  private static final Log LOG = LogFactory.getLog("hive.ql.parse.QBExpr");

  /**
   * Opcode.
   *
   */
  public static enum Opcode {
    NULLOP, UNION, INTERSECT, DIFF
  };

  private Opcode opcode;
  private QBExpr qbexpr1;
  private QBExpr qbexpr2;
  private QB qb;
  private String alias;

  public String getAlias() {
    return alias;
  }

  public void setAlias(String alias) {
    this.alias = alias;
  }

  public QBExpr(String alias) {
    this.alias = alias;
  }

  public QBExpr(QB qb) {
    opcode = Opcode.NULLOP;
    this.qb = qb;
  }

  public QBExpr(Opcode opcode, QBExpr qbexpr1, QBExpr qbexpr2) {
    this.opcode = opcode;
    this.qbexpr1 = qbexpr1;
    this.qbexpr2 = qbexpr2;
  }

  public void setQB(QB qb) {
    this.qb = qb;
  }

  public void setOpcode(Opcode opcode) {
    this.opcode = opcode;
  }

  public void setQBExpr1(QBExpr qbexpr) {
    qbexpr1 = qbexpr;
  }

  public void setQBExpr2(QBExpr qbexpr) {
    qbexpr2 = qbexpr;
  }

  public QB getQB() {
    return qb;
  }

  public Opcode getOpcode() {
    return opcode;
  }

  public QBExpr getQBExpr1() {
    return qbexpr1;
  }

  public QBExpr getQBExpr2() {
    return qbexpr2;
  }

  public void print(String msg) {
    if (opcode == Opcode.NULLOP) {
      LOG.info(msg + "start qb = " + qb);
      qb.print(msg + " ");
      LOG.info(msg + "end qb = " + qb);
    } else {
      LOG.info(msg + "start qbexpr1 = " + qbexpr1);
      qbexpr1.print(msg + " ");
      LOG.info(msg + "end qbexpr1 = " + qbexpr1);
      LOG.info(msg + "start qbexpr2 = " + qbexpr2);
      qbexpr2.print(msg + " ");
      LOG.info(msg + "end qbexpr2 = " + qbexpr2);
    }
  }

}
