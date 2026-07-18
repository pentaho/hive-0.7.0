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
import org.apache.hadoop.io.Text;
import org.apache.hadoop.io.Writable;
import org.apache.hadoop.mapred.LineRecordReader.LineReader;

/**
 * TextRecordReader.
 *
 */
public class TextRecordReader implements RecordReader {

  private LineReader lineReader;
  private InputStream in;
  private Text row;

  public void initialize(InputStream in, Configuration conf, Properties tbl)
      throws IOException {
    lineReader = new LineReader(in, conf);
    this.in = in;
  }

  public Writable createRow() throws IOException {
    row = new Text();
    return row;
  }

  public int next(Writable row) throws IOException {
    if (lineReader == null) {
      return -1;
    }

    return lineReader.readLine((Text) row);
  }

  public void close() throws IOException {
    if (in != null) {
      in.close();
    }
  }
}
