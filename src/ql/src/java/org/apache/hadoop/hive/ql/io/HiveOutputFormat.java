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

import org.apache.hadoop.fs.Path;
import org.apache.hadoop.hive.ql.exec.FileSinkOperator.RecordWriter;
import org.apache.hadoop.io.Writable;
import org.apache.hadoop.io.WritableComparable;
import org.apache.hadoop.mapred.JobConf;
import org.apache.hadoop.util.Progressable;

/**
 * <code>HiveOutputFormat</code> describes the output-specification for Hive's
 * operators. It has a method
 * {@link #getHiveRecordWriter(JobConf, Path, Class, boolean, Properties, Progressable)}
 * , with various parameters used to create the final out file and get some
 * specific settings.
 * 
 * @see org.apache.hadoop.mapred.OutputFormat
 * @see RecordWriter
 * @see JobConf
 */
public interface HiveOutputFormat<K extends WritableComparable, V extends Writable> {

  /**
   * create the final out file and get some specific settings.
   * 
   * @param jc
   *          the job configuration file
   * @param finalOutPath
   *          the final output file to be created
   * @param valueClass
   *          the value class used for create
   * @param isCompressed
   *          whether the content is compressed or not
   * @param tableProperties
   *          the table properties of this file's corresponding table
   * @param progress
   *          progress used for status report
   * @return the RecordWriter for the output file
   */
  RecordWriter getHiveRecordWriter(JobConf jc, Path finalOutPath,
      final Class<? extends Writable> valueClass, boolean isCompressed,
      Properties tableProperties, Progressable progress) throws IOException;

}
