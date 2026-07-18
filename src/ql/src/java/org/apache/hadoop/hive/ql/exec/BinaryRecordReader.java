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
import org.apache.hadoop.io.BytesWritable;
import org.apache.hadoop.io.Writable;

/**
 * Read from a binary stream and treat each 1000 bytes (configurable via 
 * hive.binary.record.max.length) as a record.  The last record before the 
 * end of stream can have less than 1000 bytes. 
 */
public class BinaryRecordReader implements RecordReader {

  private InputStream in;
  private BytesWritable bytes;
  private int maxRecordLength;

  public void initialize(InputStream in, Configuration conf, Properties tbl) throws IOException {
    this.in = in;
    maxRecordLength = conf.getInt("hive.binary.record.max.length", 1000);
  }

  public Writable createRow() throws IOException {
    bytes = new BytesWritable();
    bytes.setCapacity(maxRecordLength);
    return bytes;
  }

  public int next(Writable row) throws IOException {
    int recordLength = in.read(bytes.get(), 0, maxRecordLength);
    if (recordLength >= 0) {
      bytes.setSize(recordLength);
    }
    return recordLength;
  }

  public void close() throws IOException {
    if (in != null) {
      in.close();
    }
  }
}
