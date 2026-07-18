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


package org.apache.hadoop.hive.ql.exec;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.io.Writable;

/**
 * RecordReader.
 *
 */
public interface RecordReader {

  void initialize(InputStream in, Configuration conf, Properties tbl) throws IOException;

  Writable createRow() throws IOException;

  /**
   * Returns the number of bytes that we consumed.
   * -1 means end of stream.
   */
  int next(Writable row) throws IOException;

  void close() throws IOException;
}
