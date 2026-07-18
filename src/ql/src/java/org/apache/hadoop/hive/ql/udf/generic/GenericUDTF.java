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

import org.apache.hadoop.hive.ql.exec.UDFArgumentException;
import org.apache.hadoop.hive.ql.metadata.HiveException;
import org.apache.hadoop.hive.serde2.objectinspector.ObjectInspector;
import org.apache.hadoop.hive.serde2.objectinspector.StructObjectInspector;

/**
 * A Generic User-defined Table Generating Function (UDTF)
 *
 * Generates a variable number of output rows for a single input row. Useful for
 * explode(array)...
 */

public abstract class GenericUDTF {
  Collector collector = null;

  /**
   * Initialize this GenericUDTF. This will be called only once per instance.
   *
   * @param args
   *          An array of ObjectInspectors for the arguments
   * @return A StructObjectInspector for output. The output struct represents a
   *         row of the table where the fields of the stuct are the columns. The
   *         field names are unimportant as they will be overridden by user
   *         supplied column aliases.
   */
  public abstract StructObjectInspector initialize(ObjectInspector[] argOIs)
      throws UDFArgumentException;

  /**
   * Give a set of arguments for the UDTF to process.
   *
   * @param o
   *          object array of arguments
   */
  public abstract void process(Object[] args) throws HiveException;

  /**
   * Called to notify the UDTF that there are no more rows to process.
   * Clean up code or additional forward() calls can be made here.
   */
  public abstract void close() throws HiveException;

  /**
   * Associates a collector with this UDTF. Can't be specified in the
   * constructor as the UDTF may be initialized before the collector has been
   * constructed.
   *
   * @param collector
   */
  public final void setCollector(Collector collector) {
    this.collector = collector;
  }

  /**
   * Passes an output row to the collector.
   *
   * @param o
   * @throws HiveException
   */
  protected final void forward(Object o) throws HiveException {
    collector.collect(o);
  }

}
