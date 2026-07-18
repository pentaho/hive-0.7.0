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


package org.apache.hadoop.hive.hbase;

import java.io.IOException;
import java.util.Properties;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.client.HTable;
import org.apache.hadoop.hbase.client.Put;
import org.apache.hadoop.hbase.io.ImmutableBytesWritable;
import org.apache.hadoop.hbase.mapreduce.TableOutputFormat;
import org.apache.hadoop.hive.conf.HiveConf;
import org.apache.hadoop.hive.ql.exec.FileSinkOperator.RecordWriter;
import org.apache.hadoop.hive.ql.io.HiveOutputFormat;
import org.apache.hadoop.io.Writable;
import org.apache.hadoop.mapred.JobConf;
import org.apache.hadoop.mapred.OutputFormat;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.JobContext;
import org.apache.hadoop.util.Progressable;

/**
 * HiveHBaseTableOutputFormat implements HiveOutputFormat for HBase tables.
 * We also need to implement the @deprecated org.apache.hadoop.mapred.OutFormat<?,?>
 * class to keep it compliant with Hive interfaces.
 */
public class HiveHBaseTableOutputFormat extends
    TableOutputFormat<ImmutableBytesWritable> implements
    HiveOutputFormat<ImmutableBytesWritable, Put>,
    OutputFormat<ImmutableBytesWritable, Put> {

  static final Log LOG = LogFactory.getLog(HiveHBaseTableOutputFormat.class);
  public static final String HBASE_WAL_ENABLED = "hive.hbase.wal.enabled";

  /**
   * Update the out table, and output an empty key as the key.
   *
   * @param jc the job configuration file
   * @param finalOutPath the final output table name
   * @param valueClass the value class
   * @param isCompressed whether the content is compressed or not
   * @param tableProperties the table info of the corresponding table
   * @param progress progress used for status report
   * @return the RecordWriter for the output file
   */
  @Override
  public RecordWriter getHiveRecordWriter(
      JobConf jc,
      Path finalOutPath,
      Class<? extends Writable> valueClass,
      boolean isCompressed,
      Properties tableProperties,
      final Progressable progressable) throws IOException {

    String hbaseTableName = jc.get(HBaseSerDe.HBASE_TABLE_NAME);
    jc.set(TableOutputFormat.OUTPUT_TABLE, hbaseTableName);
    final boolean walEnabled = HiveConf.getBoolVar(
        jc, HiveConf.ConfVars.HIVE_HBASE_WAL_ENABLED);
    final HTable table = new HTable(new HBaseConfiguration(jc), hbaseTableName);
    table.setAutoFlush(false);

    return new RecordWriter() {

      @Override
      public void close(boolean abort) throws IOException {
        if (!abort) {
          table.flushCommits();
        }
      }

      @Override
      public void write(Writable w) throws IOException {
        Put put = (Put) w;
        put.setWriteToWAL(walEnabled);
        table.put(put);
      }
    };
  }

  @Override
  public void checkOutputSpecs(FileSystem fs, JobConf jc) throws IOException {

    String hbaseTableName = jc.get(HBaseSerDe.HBASE_TABLE_NAME);
    jc.set(TableOutputFormat.OUTPUT_TABLE, hbaseTableName);
    Job job = new Job(jc);
    JobContext jobContext =
      new JobContext(job.getConfiguration(), job.getJobID());

    try {
      checkOutputSpecs(jobContext);
    } catch (InterruptedException e) {
      throw new IOException(e);
    }
  }

  @Override
  public
  org.apache.hadoop.mapred.RecordWriter<ImmutableBytesWritable, Put>
  getRecordWriter(
      FileSystem fileSystem,
      JobConf jobConf,
      String name,
      Progressable progressable) throws IOException {

    throw new RuntimeException("Error: Hive should not invoke this method.");
  }
}
