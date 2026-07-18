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


package org.apache.hadoop.hive.contrib.udf.example;

import org.apache.hadoop.hive.ql.exec.UDF;

/**
 * UDFExampleAdd.
 *
 */
public class UDFExampleAdd extends UDF {

  public Integer evaluate(Integer... a) {
    int total = 0;
    for (Integer element : a) {
      if (element != null) {
        total += element;
      }
    }
    return total;
  }

  public Double evaluate(Double... a) {
    double total = 0;
    for (Double element : a) {
      if (element != null) {
        total += element;
      }
    }
    return total;
  }

}
