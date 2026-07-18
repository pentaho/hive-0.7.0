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

import org.apache.hadoop.hive.contrib.mr.GenericMR;
import org.apache.hadoop.hive.contrib.mr.Mapper;
import org.apache.hadoop.hive.contrib.mr.Output;

/**
 * Example Mapper (Identity).
 */
public final class IdentityMapper {

  public static void main(final String[] args) throws Exception {
    new GenericMR().map(System.in, System.out, new Mapper() {
      @Override
      public void map(final String[] record, final Output output) throws Exception {
        output.collect(record);
      }
    });
  }

  private IdentityMapper() {
    // prevent instantiation
  }
}
