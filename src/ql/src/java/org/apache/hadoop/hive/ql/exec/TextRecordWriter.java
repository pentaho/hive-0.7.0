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
import java.io.OutputStream;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.io.Writable;

/**
 * TextRecordWriter.
 *
 */
public class TextRecordWriter implements RecordWriter {

  private OutputStream out;

  public void initialize(OutputStream out, Configuration conf)
      throws IOException {
    this.out = out;
  }

  public void write(Writable row) throws IOException {
    Text text = (Text) row;
    out.write(text.getBytes(), 0, text.getLength());
    out.write(Utilities.newLineCode);
  }

  public void close() throws IOException {
    out.flush();
    out.close();
  }
}
