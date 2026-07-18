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

import org.apache.hadoop.hive.ql.exec.ExecMapper;
import org.apache.hadoop.io.Writable;
import org.apache.hadoop.io.WritableComparable;
import org.apache.hadoop.mapred.RecordReader;

/**
 * HiveRecordReader is a simple wrapper on RecordReader. It allows us to stop
 * reading the data when some global flag ExecMapper.getDone() is set.
 */
public class HiveRecordReader<K extends WritableComparable, V extends Writable>
    extends HiveContextAwareRecordReader<K, V> {

  private final RecordReader recordReader;

  public HiveRecordReader(RecordReader recordReader) throws IOException {
    this.recordReader = recordReader;
  }

  public void doClose() throws IOException {
    recordReader.close();
  }

  public K createKey() {
    return (K) recordReader.createKey();
  }

  public V createValue() {
    return (V) recordReader.createValue();
  }

  public long getPos() throws IOException {
    return recordReader.getPos();
  }

  public float getProgress() throws IOException {
    return recordReader.getProgress();
  }

  @Override
  public boolean doNext(K key, V value) throws IOException {
    if (ExecMapper.getDone()) {
      return false;
    }
    return recordReader.next(key, value);
  }

}
