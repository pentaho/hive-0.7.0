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


package org.apache.hadoop.hive.contrib.udaf.example;

import org.apache.hadoop.hive.ql.exec.UDAF;

/**
 * Returns the min N double values.
 */
public class UDAFExampleMinN extends UDAF{

  /**
   * The evaluator for getting min N double values.
   */
  public static class UDAFMinNEvaluator extends UDAFExampleMaxMinNUtil.Evaluator {

    @Override
    protected boolean getAscending() {
      return true;
    }
  }
  
}

