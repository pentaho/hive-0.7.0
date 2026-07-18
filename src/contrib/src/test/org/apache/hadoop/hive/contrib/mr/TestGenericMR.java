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


package org.apache.hadoop.hive.contrib.mr;

import java.io.StringReader;
import java.io.StringWriter;
import java.util.Iterator;
import java.util.NoSuchElementException;

import junit.framework.TestCase;

/**
 * TestGenericMR.
 *
 */
public final class TestGenericMR extends TestCase {
  public void testReduceTooFar() throws Exception {
    try {
      new GenericMR().reduce(new StringReader("a\tb\tc"), new StringWriter(),
          new Reducer() {
        public void reduce(String key, Iterator<String[]> records,
            Output output) throws Exception {
          while (true) {
            records.next();
          }
        }
      });
    } catch (final NoSuchElementException nsee) {
      // expected
      return;
    }

    fail("Expected NoSuchElementException");
  }

  public void testEmptyMap() throws Exception {
    final StringWriter out = new StringWriter();

    new GenericMR().map(new StringReader(""), out, identityMapper());

    assertEquals(0, out.toString().length());
  }

  public void testIdentityMap() throws Exception {
    final String in = "a\tb\nc\td";
    final StringWriter out = new StringWriter();

    new GenericMR().map(new StringReader(in), out, identityMapper());

    assertEquals(in + "\n", out.toString());
  }

  public void testKVSplitMap() throws Exception {
    final String in = "k1=v1,k2=v2\nk1=v2,k2=v3";
    final String expected = "k1\tv1\nk2\tv2\nk1\tv2\nk2\tv3\n";
    final StringWriter out = new StringWriter();

    new GenericMR().map(new StringReader(in), out, new Mapper() {
      public void map(String[] record, Output output) throws Exception {
        for (final String kvs : record[0].split(",")) {
          final String[] kv = kvs.split("=");
          output.collect(new String[] {kv[0], kv[1]});
        }
      }
    });

    assertEquals(expected, out.toString());
  }

  public void testIdentityReduce() throws Exception {
    final String in = "a\tb\nc\td";
    final StringWriter out = new StringWriter();

    new GenericMR().reduce(new StringReader(in), out, identityReducer());

    assertEquals(in + "\n", out.toString());
  }

  public void testWordCountReduce() throws Exception {
    final String in = "hello\t1\nhello\t2\nokay\t4\nokay\t6\nokay\t2";
    final StringWriter out = new StringWriter();

    new GenericMR().reduce(new StringReader(in), out, new Reducer() {
      @Override
      public void reduce(String key, Iterator<String[]> records, Output output)
          throws Exception {
        int count = 0;

        while (records.hasNext()) {
          count += Integer.parseInt(records.next()[1]);
        }

        output.collect(new String[] {key, String.valueOf(count)});
      }
    });

    final String expected = "hello\t3\nokay\t12\n";

    assertEquals(expected, out.toString());
  }

  private Mapper identityMapper() {
    return new Mapper() {
      @Override
      public void map(String[] record, Output output) throws Exception {
        output.collect(record);
      }
    };
  }

  private Reducer identityReducer() {
    return new Reducer() {
      @Override
      public void reduce(String key, Iterator<String[]> records, Output output)
          throws Exception {
        while (records.hasNext()) {
          output.collect(records.next());
        }
      }
    };
  }
}
