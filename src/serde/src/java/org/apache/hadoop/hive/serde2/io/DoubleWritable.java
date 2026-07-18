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


/**
 * This file is back-ported from hadoop-0.19, to make sure hive can run 
 * with hadoop-0.17.
 */

package org.apache.hadoop.hive.serde2.io;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

import org.apache.hadoop.io.WritableComparable;
import org.apache.hadoop.io.WritableComparator;

/**
 * Writable for Double values.
 */
public class DoubleWritable implements WritableComparable {

  private double value = 0.0;

  public DoubleWritable() {

  }

  public DoubleWritable(double value) {
    set(value);
  }

  public void readFields(DataInput in) throws IOException {
    value = in.readDouble();
  }

  public void write(DataOutput out) throws IOException {
    out.writeDouble(value);
  }

  public void set(double value) {
    this.value = value;
  }

  public double get() {
    return value;
  }

  /**
   * Returns true iff <code>o</code> is a DoubleWritable with the same value.
   */
  @Override
  public boolean equals(Object o) {
    if (!(o instanceof DoubleWritable)) {
      return false;
    }
    DoubleWritable other = (DoubleWritable) o;
    return value == other.value;
  }

  @Override
  public int hashCode() {
    long v = Double.doubleToLongBits(value);
    return (int) (v ^ (v >>> 32));
  }

  public int compareTo(Object o) {
    DoubleWritable other = (DoubleWritable) o;
    return (value < other.value ? -1 : (value == other.value ? 0 : 1));
  }

  @Override
  public String toString() {
    return Double.toString(value);
  }

  /** A Comparator optimized for DoubleWritable. */
  public static class Comparator extends WritableComparator {
    public Comparator() {
      super(DoubleWritable.class);
    }

    @Override
    public int compare(byte[] b1, int s1, int l1, byte[] b2, int s2, int l2) {
      double thisValue = readDouble(b1, s1);
      double thatValue = readDouble(b2, s2);
      return (thisValue < thatValue ? -1 : (thisValue == thatValue ? 0 : 1));
    }
  }

  static { // register this comparator
    WritableComparator.define(DoubleWritable.class, new Comparator());
  }

}
