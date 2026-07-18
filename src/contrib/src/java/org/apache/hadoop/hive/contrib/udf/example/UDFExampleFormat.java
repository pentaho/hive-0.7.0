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
 * UDFExampleFormat.
 *
 */
public class UDFExampleFormat extends UDF {

  public String evaluate(String format, Object... args) {
    return String.format(format, args);
  }

}
