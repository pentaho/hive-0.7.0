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

import java.util.List;

import org.apache.hadoop.hive.ql.exec.UDF;

/**
 * UDFExampleArraySum.
 *
 */
public class UDFExampleArraySum extends UDF {

  public Double evaluate(List<Double> a) {
    if (a == null) {
      return null;
    }
    double total = 0;
    for (int i = 0; i < a.size(); i++) {
      Double e = a.get(i);
      if (e != null) {
        total += e;
      }
    }
    return total;
  }

}
