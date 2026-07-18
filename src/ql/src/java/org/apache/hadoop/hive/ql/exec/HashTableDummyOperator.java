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


package org.apache.hadoop.hive.ql.exec;

import java.io.Serializable;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hive.ql.metadata.HiveException;
import org.apache.hadoop.hive.ql.plan.HashTableDummyDesc;
import org.apache.hadoop.hive.ql.plan.TableDesc;
import org.apache.hadoop.hive.ql.plan.api.OperatorType;
import org.apache.hadoop.hive.serde2.Deserializer;

public class HashTableDummyOperator extends Operator<HashTableDummyDesc> implements Serializable {
  private static final long serialVersionUID = 1L;

  @Override
  protected void initializeOp(Configuration hconf) throws HiveException {
    TableDesc tbl = this.getConf().getTbl();
    try {
      Deserializer serde = tbl.getDeserializerClass().newInstance();
      serde.initialize(hconf, tbl.getProperties());
      this.outputObjInspector = serde.getObjectInspector();
      initializeChildren(hconf);
    } catch (Exception e) {
      LOG.error("Generating output obj inspector from dummy object error");
      e.printStackTrace();
    }
  }

  @Override
  public void processOp(Object row, int tag) throws HiveException {
    throw new HiveException();
  }

  @Override
  public void closeOp(boolean abort) throws HiveException {
  }

  @Override
  public String getName() {
    return "HASHTABLEDUMMY";
  }

  @Override
  public OperatorType getType() {
    return OperatorType.HASHTABLEDUMMY;
  }

}
