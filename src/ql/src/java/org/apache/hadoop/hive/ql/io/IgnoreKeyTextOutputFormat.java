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


package org.apache.hadoop.hive.ql.io;

import java.io.IOException;

import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.io.Writable;
import org.apache.hadoop.io.WritableComparable;
import org.apache.hadoop.mapred.JobConf;
import org.apache.hadoop.mapred.RecordWriter;
import org.apache.hadoop.mapred.Reporter;
import org.apache.hadoop.mapred.TextOutputFormat;
import org.apache.hadoop.util.Progressable;

/**
 * This class replaces key with null before feeding the <key, value> to
 * TextOutputFormat.RecordWriter.
 * 
 * @deprecated use {@link HiveIgnoreKeyTextOutputFormat} instead}
 */
@Deprecated
public class IgnoreKeyTextOutputFormat<K extends WritableComparable, V extends Writable>
    extends TextOutputFormat<K, V> {

  protected static class IgnoreKeyWriter<K extends WritableComparable, V extends Writable>
      implements RecordWriter<K, V> {

    private final RecordWriter<K, V> mWriter;

    public IgnoreKeyWriter(RecordWriter<K, V> writer) {
      this.mWriter = writer;
    }

    public synchronized void write(K key, V value) throws IOException {
      this.mWriter.write(null, value);
    }

    public void close(Reporter reporter) throws IOException {
      this.mWriter.close(reporter);
    }
  }

  @Override
  public RecordWriter<K, V> getRecordWriter(FileSystem ignored, JobConf job,
      String name, Progressable progress) throws IOException {

    return new IgnoreKeyWriter<K, V>(super.getRecordWriter(ignored, job, name,
        progress));
  }

}
