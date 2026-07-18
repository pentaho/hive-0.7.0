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

import java.util.ArrayList;
import java.util.Collections;
import java.util.Map;

import org.apache.hadoop.hive.ql.exec.UDF;

/**
 * UDFExampleMapConcat.
 *
 */
public class UDFExampleMapConcat extends UDF {

  public String evaluate(Map<String, String> a) {
    if (a == null) {
      return null;
    }
    ArrayList<String> r = new ArrayList<String>(a.size());
    for (Map.Entry<String, String> entry : a.entrySet()) {
      r.add("(" + entry.getKey() + ":" + entry.getValue() + ")");
    }
    Collections.sort(r);

    StringBuilder sb = new StringBuilder();
    for (int i = 0; i < r.size(); i++) {
      sb.append(r.get(i));
    }
    return sb.toString();
  }

}
