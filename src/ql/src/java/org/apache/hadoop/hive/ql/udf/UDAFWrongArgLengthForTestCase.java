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


package org.apache.hadoop.hive.ql.udf;

import org.apache.hadoop.hive.ql.exec.UDAF;
import org.apache.hadoop.hive.ql.exec.UDAFEvaluator;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;

/**
 * UDAFWrongArgLengthForTestCase.
 *
 */
public class UDAFWrongArgLengthForTestCase extends UDAF {

  /**
   * UDAFWrongArgLengthForTestCaseEvaluator.
   *
   */
  public static class UDAFWrongArgLengthForTestCaseEvaluator implements
      UDAFEvaluator {

    private long mCount;

    public UDAFWrongArgLengthForTestCaseEvaluator() {
      super();
      init();
    }

    public void init() {
      mCount = 0;
    }

    Text emptyText = new Text();

    public boolean iterate(Object o) {
      if (o != null && !emptyText.equals(o)) {
        mCount++;
      }
      return true;
    }

    public LongWritable terminatePartial() {
      return new LongWritable(mCount);
    }

    public boolean merge() {
      return true;
    }

    public LongWritable terminate() {
      return new LongWritable(mCount);
    }
  }

}
