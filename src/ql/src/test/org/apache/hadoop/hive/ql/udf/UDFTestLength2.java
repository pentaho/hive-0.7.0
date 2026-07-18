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

import org.apache.hadoop.hive.ql.exec.UDF;

/**
 * A UDF for testing, which evaluates the length of a string. This UDF uses Java
 * Primitive classes for parameters.
 */
public class UDFTestLength2 extends UDF {

  public Integer evaluate(String s) {
    if (s == null) {
      return null;
    }
    return Integer.valueOf(s.length());
  }
}
