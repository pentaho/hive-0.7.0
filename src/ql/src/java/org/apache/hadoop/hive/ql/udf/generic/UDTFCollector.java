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


package org.apache.hadoop.hive.ql.udf.generic;

import org.apache.hadoop.hive.ql.exec.UDTFOperator;
import org.apache.hadoop.hive.ql.metadata.HiveException;

/**
 * UDTFCollector collects data from a GenericUDTF and passes the data to a
 * UDTFOperator.
 */
public class UDTFCollector implements Collector {

  /*
   * (non-Javadoc)
   * 
   * @see
   * org.apache.hadoop.hive.ql.udf.generic.Collector#collect(java.lang.Object)
   */
  UDTFOperator op = null;

  public UDTFCollector(UDTFOperator op) {
    this.op = op;
  }

  @Override
  public void collect(Object input) throws HiveException {
    op.forwardUDTFOutput(input);
  }

}
