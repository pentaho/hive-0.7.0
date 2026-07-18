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

/**
 * Check for validity of the input files.
 */
public interface InputFormatChecker {

  /**
   * This method is used to validate the input files.
   * 
   */
  boolean validateInput(FileSystem fs, HiveConf conf,
      ArrayList<FileStatus> files) throws IOException;

}
