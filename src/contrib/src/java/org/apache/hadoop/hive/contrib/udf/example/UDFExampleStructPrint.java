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
 * UDFExampleStructPrint.
 *
 */
public class UDFExampleStructPrint extends UDF {

  public String evaluate(Object a) {
    if (a == null) {
      return null;
    }
    List<Object> s = (List<Object>) a;

    StringBuilder sb = new StringBuilder();
    for (int i = 0; i < s.size(); i++) {
      sb.append("(" + i + ":" + s.get(i) + ")");
    }
    return sb.toString();
  }

}
