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


package org.apache.hadoop.hive.serde2.io;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

import org.apache.hadoop.io.WritableComparable;
import org.apache.hadoop.io.WritableComparator;

/**
 * ShortWritable.
 *
 */
public class ShortWritable implements WritableComparable {
  private short value;

  public void write(DataOutput out) throws IOException {
    out.writeShort(value);
  }

  public void readFields(DataInput in) throws IOException {
    value = in.readShort();
  }

  public ShortWritable(short s) {
    value = s;
  }

  public ShortWritable() {
    value = 0;
  }

  public void set(short value) {
    this.value = value;
  }

  public short get() {
    return value;
  }

  @Override
  public boolean equals(Object o) {
    if (o == null || o.getClass() != ShortWritable.class) {
      return false;
    }
    return get() == ((ShortWritable) o).get();
  }

  @Override
  public int hashCode() {
    return value;
  }

  @Override
  public String toString() {
    return String.valueOf(get());
  }

  public int compareTo(Object o) {
    int thisValue = value;
    int thatValue = ((ShortWritable) o).value;
    return thisValue - thatValue;
  }

  /** A Comparator optimized for BytesWritable. */
  public static class Comparator extends WritableComparator {
    public Comparator() {
      super(ShortWritable.class);
    }

    /**
     * Compare the buffers in serialized form.
     */
    @Override
    public int compare(byte[] b1, int s1, int l1, byte[] b2, int s2, int l2) {
      int a1 = (short) (readUnsignedShort(b1, s1));
      int a2 = (short) (readUnsignedShort(b2, s2));
      return a1 - a2;
    }
  }

  static { // register this comparator
    WritableComparator.define(ShortWritable.class, new Comparator());
  }

}
