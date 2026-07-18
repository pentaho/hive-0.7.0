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
import org.apache.hadoop.io.SequenceFile;

/**
 * SequenceFileInputFormatChecker.
 *
 */
public class SequenceFileInputFormatChecker implements InputFormatChecker {

  @Override
  public boolean validateInput(FileSystem fs, HiveConf conf,
      ArrayList<FileStatus> files) throws IOException {
    if (files.size() <= 0) {
      return false;
    }
    for (int fileId = 0; fileId < files.size(); fileId++) {
      try {
        SequenceFile.Reader reader = new SequenceFile.Reader(fs, files.get(
            fileId).getPath(), conf);
        reader.close();
      } catch (IOException e) {
        return false;
      }
    }
    return true;
  }

}
