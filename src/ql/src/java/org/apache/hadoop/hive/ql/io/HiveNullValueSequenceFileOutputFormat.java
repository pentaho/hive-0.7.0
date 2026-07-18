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
import java.util.Properties;

import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.hive.ql.exec.Utilities;
import org.apache.hadoop.hive.ql.exec.FileSinkOperator.RecordWriter;
import org.apache.hadoop.io.BytesWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.SequenceFile;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.io.Writable;
import org.apache.hadoop.io.WritableComparable;
import org.apache.hadoop.mapred.JobConf;
import org.apache.hadoop.mapred.SequenceFileOutputFormat;
import org.apache.hadoop.util.Progressable;

/**
 * A {@link HiveOutputFormat} that writes {@link SequenceFile}s with the
 * content saved in the keys, and null in the values.
 */
public class HiveNullValueSequenceFileOutputFormat
  extends SequenceFileOutputFormat
  implements HiveOutputFormat<WritableComparable, Writable> {

  private static final Writable NULL_WRITABLE = NullWritable.get();

  private HiveKey keyWritable;
  private boolean keyIsText;

  @Override
  public RecordWriter getHiveRecordWriter(JobConf jc, Path finalOutPath,
      Class<? extends Writable> valueClass, boolean isCompressed,
      Properties tableProperties, Progressable progress) throws IOException {

    FileSystem fs = finalOutPath.getFileSystem(jc);
    final SequenceFile.Writer outStream = Utilities.createSequenceWriter(jc,
        fs, finalOutPath, HiveKey.class, NullWritable.class, isCompressed);

    keyWritable = new HiveKey();
    keyIsText = valueClass.equals(Text.class);
    return new RecordWriter() {
      public void write(Writable r) throws IOException {
        if (keyIsText) {
          Text text = (Text) r;
          keyWritable.set(text.getBytes(), 0, text.getLength());
        } else {
          BytesWritable bw = (BytesWritable) r;
          // Once we drop support for old Hadoop versions, change these
          // to getBytes() and getLength() to fix the deprecation warnings.
          // Not worth a shim.
          keyWritable.set(bw.get(), 0, bw.getSize());
        }
        keyWritable.setHashCode(r.hashCode());
        outStream.append(keyWritable, NULL_WRITABLE);
      }

      public void close(boolean abort) throws IOException {
        outStream.close();
      }
    };
  }

}
