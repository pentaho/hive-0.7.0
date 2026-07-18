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

import org.apache.hadoop.hive.ql.exec.ComparisonOpMethodResolver;
import org.apache.hadoop.hive.ql.exec.UDF;
import org.apache.hadoop.hive.serde2.io.DoubleWritable;
import org.apache.hadoop.io.BooleanWritable;

/**
 * UDFBaseCompare.
 *
 */
public abstract class UDFBaseCompare extends UDF {

  /**
   * This constructor sets the resolver to be used for comparison operators. See
   * {@link org.apache.hadoop.hive.ql.exec.UDFMethodResolver}
   */
  public UDFBaseCompare() {
    super(null);
    setResolver(new ComparisonOpMethodResolver(this.getClass()));
  }

  public abstract BooleanWritable evaluate(DoubleWritable a, DoubleWritable b);
}
