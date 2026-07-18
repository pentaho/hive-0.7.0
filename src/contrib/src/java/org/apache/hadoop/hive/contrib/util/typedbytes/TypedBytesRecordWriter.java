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


package org.apache.hadoop.hive.contrib.util.typedbytes;

import java.io.IOException;
import java.io.OutputStream;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hive.ql.exec.RecordWriter;
import org.apache.hadoop.io.BytesWritable;
import org.apache.hadoop.io.Writable;

/**
 * TypedBytesRecordWriter.
 *
 */
public class TypedBytesRecordWriter implements RecordWriter {

  private OutputStream out;

  public void initialize(OutputStream out, Configuration conf)
      throws IOException {
    this.out = out;
  }

  public void write(Writable row) throws IOException {
    BytesWritable brow = (BytesWritable) row;
    out.write(brow.get(), 0, brow.getSize());
  }

  public void close() throws IOException {
    out.flush();
    out.close();
  }
}
