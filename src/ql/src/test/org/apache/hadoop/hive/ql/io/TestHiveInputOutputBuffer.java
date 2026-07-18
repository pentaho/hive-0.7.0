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

import junit.framework.TestCase;

/**
 * TestHiveInputOutputBuffer.
 *
 */
public class TestHiveInputOutputBuffer extends TestCase {

  public void testReadAndWrite() throws IOException {
    String testString = "test_hive_input_output_number_0";
    byte[] string_bytes = testString.getBytes();
    NonSyncDataInputBuffer inBuffer = new NonSyncDataInputBuffer();
    NonSyncDataOutputBuffer outBuffer = new NonSyncDataOutputBuffer();
    outBuffer.write(string_bytes);
    inBuffer.reset(outBuffer.getData(), 0, outBuffer.getLength());
    byte[] readBytes = new byte[string_bytes.length];
    inBuffer.read(readBytes);
    String readString = new String(readBytes);
    assertEquals("Field testReadAndWrite()", readString, testString);
  }

}
