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
 * A UDF for testing, which throws RuntimeException if  the length of a string.
 */
public class UDFTestErrorOnFalse extends UDF {

  public int evaluate(Boolean b) {
    if (b) {
      return 1;
    } else {
      throw new RuntimeException("UDFTestErrorOnFalse got b=false");
    }
  }
}
