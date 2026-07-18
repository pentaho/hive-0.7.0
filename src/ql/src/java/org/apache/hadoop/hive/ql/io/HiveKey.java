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

import org.apache.hadoop.io.BytesWritable;
import org.apache.hadoop.io.WritableComparator;

/**
 * HiveKey is a simple wrapper on Text which allows us to set the hashCode
 * easily. hashCode is used for hadoop partitioner.
 */
public class HiveKey extends BytesWritable {

  private static final int LENGTH_BYTES = 4;

  boolean hashCodeValid;

  public HiveKey() {
    hashCodeValid = false;
  }

  protected int myHashCode;

  public void setHashCode(int myHashCode) {
    hashCodeValid = true;
    this.myHashCode = myHashCode;
  }

  @Override
  public int hashCode() {
    if (!hashCodeValid) {
      throw new RuntimeException("Cannot get hashCode() from deserialized "
          + HiveKey.class);
    }
    return myHashCode;
  }

  /** A Comparator optimized for HiveKey. */
  public static class Comparator extends WritableComparator {
    public Comparator() {
      super(HiveKey.class);
    }

    /**
     * Compare the buffers in serialized form.
     */
    @Override
    public int compare(byte[] b1, int s1, int l1, byte[] b2, int s2, int l2) {
      return compareBytes(b1, s1 + LENGTH_BYTES, l1 - LENGTH_BYTES, b2, s2
          + LENGTH_BYTES, l2 - LENGTH_BYTES);
    }
  }

  static {
    WritableComparator.define(HiveKey.class, new Comparator());
  }
}
