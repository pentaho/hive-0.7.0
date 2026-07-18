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


package org.apache.hadoop.hive.contrib.mr.example;

import java.util.Iterator;

import org.apache.hadoop.hive.contrib.mr.GenericMR;
import org.apache.hadoop.hive.contrib.mr.Output;
import org.apache.hadoop.hive.contrib.mr.Reducer;

/**
 * Example Reducer (WordCount).
 */
public final class WordCountReduce {

  private WordCountReduce() {
    // prevent instantiation
  }

  public static void main(final String[] args) throws Exception {
    new GenericMR().reduce(System.in, System.out, new Reducer() {
      public void reduce(String key, Iterator<String[]> records, Output output)
          throws Exception {
        int count = 0;

        while (records.hasNext()) {
          // note we use col[1] -- the key is provided again as col[0]
          count += Integer.parseInt(records.next()[1]);
        }

        output.collect(new String[] {key, String.valueOf(count)});
      }
    });
  }
}
