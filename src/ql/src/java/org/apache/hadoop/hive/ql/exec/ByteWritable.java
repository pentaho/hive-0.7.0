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


package org.apache.hadoop.hive.ql.exec;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

import org.apache.hadoop.io.WritableComparable;
import org.apache.hadoop.io.WritableComparator;

/**
 * ByteWritable.
 *
 */
public class ByteWritable implements WritableComparable {
  private int value;

  public void write(DataOutput out) throws IOException {
    out.writeByte(value);
  }

  public void readFields(DataInput in) throws IOException {
    value = in.readByte();
  }

  public ByteWritable(int b) {
    value = b & 0xff;
  }

  public ByteWritable() {
    value = 0;
  }

  public void set(int b) {
    value = b & 0xff;
  }

  /** Compares two ByteWritables. */
  public int compareTo(Object o) {
    int thisValue = value;
    int thatValue = ((ByteWritable) o).value;
    return (thisValue < thatValue ? -1 : (thisValue == thatValue ? 0 : 1));
  }

  @Override
  public boolean equals(Object o) {
    if (!(o instanceof ByteWritable)) {
      return false;
    }
    ByteWritable that = (ByteWritable) o;
    if (this == that) {
      return true;
    }

    if (value == that.value) {
      return true;
    } else {
      return false;
    }
  }

  @Override
  public int hashCode() {
    return (value);
  }

  /** A Comparator optimized for BytesWritable. */
  public static class Comparator extends WritableComparator {
    public Comparator() {
      super(ByteWritable.class);
    }

    /**
     * Compare the buffers in serialized form.
     */
    @Override
    public int compare(byte[] b1, int s1, int l1, byte[] b2, int s2, int l2) {
      /**
       * ok - we are implementing a dummy byte int a = b1[s1] & 0xff; int b =
       * b2[s1] & 0xff; if(a!=b) return a -b;
       */
      return 0;
    }
  }

  static {
    // register this comparator
    WritableComparator.define(ByteWritable.class, new Comparator());
  }
}
