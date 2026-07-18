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


package org.apache.hadoop.hive.shims;

import java.io.IOException;

import org.apache.hadoop.fs.BlockLocation;
import org.apache.hadoop.fs.ContentSummary;
import org.apache.hadoop.fs.FileStatus;
import org.apache.hadoop.fs.HarFileSystem;
import org.apache.hadoop.fs.Path;

/**
 * HiveHarFileSystem - fixes issues with Hadoop's HarFileSystem
 *
 */
public class HiveHarFileSystem extends HarFileSystem {

  @Override
  public BlockLocation[] getFileBlockLocations(FileStatus file, long start,
      long len) throws IOException {

    // In some places (e.g. FileInputFormat) this BlockLocation is used to
    // figure out sizes/offsets and so a completely blank one will not work.
    String [] hosts = {"DUMMY_HOST"};
    return new BlockLocation[]{new BlockLocation(null, hosts, 0, file.getLen())};
  }

  @Override
  public ContentSummary getContentSummary(Path f) throws IOException {
    // HarFileSystem has a bug where this method does not work properly
    // if the underlying FS is HDFS. See MAPREDUCE-1877 for more
    // information. This method is from FileSystem.
    FileStatus status = getFileStatus(f);
    if (!status.isDir()) {
      // f is a file
      return new ContentSummary(status.getLen(), 1, 0);
    }
    // f is a directory
    long[] summary = {0, 0, 1};
    for(FileStatus s : listStatus(f)) {
      ContentSummary c = s.isDir() ? getContentSummary(s.getPath()) :
                                     new ContentSummary(s.getLen(), 1, 0);
      summary[0] += c.getLength();
      summary[1] += c.getFileCount();
      summary[2] += c.getDirectoryCount();
    }
    return new ContentSummary(summary[0], summary[1], summary[2]);
  }
}
