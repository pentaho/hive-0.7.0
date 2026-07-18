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
import java.util.ArrayList;

import org.apache.hadoop.fs.FileStatus;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.hive.conf.HiveConf;
import org.apache.hadoop.hive.serde2.columnar.BytesRefArrayWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.SequenceFile;
import org.apache.hadoop.mapred.FileInputFormat;
import org.apache.hadoop.mapred.FileSplit;
import org.apache.hadoop.mapred.InputSplit;
import org.apache.hadoop.mapred.JobConf;
import org.apache.hadoop.mapred.RecordReader;
import org.apache.hadoop.mapred.Reporter;

/**
 * RCFileInputFormat.
 *
 * @param <K>
 * @param <V>
 */
public class RCFileInputFormat<K extends LongWritable, V extends BytesRefArrayWritable>
    extends FileInputFormat<K, V> implements InputFormatChecker {

  public RCFileInputFormat() {
    setMinSplitSize(SequenceFile.SYNC_INTERVAL);
  }

  @Override
  @SuppressWarnings("unchecked")
  public RecordReader<K, V> getRecordReader(InputSplit split, JobConf job,
      Reporter reporter) throws IOException {

    reporter.setStatus(split.toString());

    return new RCFileRecordReader(job, (FileSplit) split);
  }

  @Override
  public boolean validateInput(FileSystem fs, HiveConf conf,
      ArrayList<FileStatus> files) throws IOException {
    if (files.size() <= 0) {
      return false;
    }
    for (int fileId = 0; fileId < files.size(); fileId++) {
      try {
        RCFile.Reader reader = new RCFile.Reader(fs, files.get(fileId)
            .getPath(), conf);
        reader.close();
      } catch (IOException e) {
        return false;
      }
    }
    return true;
  }
}
