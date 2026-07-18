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


package org.apache.hadoop.hive.serde2.binarysortable;

import java.util.Arrays;

/**
 * This class is much more efficient than ByteArrayOutputStream because none of
 * the methods are synchronized.
 */
public class OutputByteBuffer {

  byte[] data = new byte[128];
  int length;

  /**
   * Reset the byte buffer.
   */
  public void reset() {
    length = 0;
  }

  public final void write(byte b) {
    write(b, false);
  }

  /**
   * Write one byte to the byte buffer. Final method to help inlining.
   * 
   * @param invert
   *          whether we want to invert all the bits.
   */
  public final void write(byte b, boolean invert) {
    if (length == data.length) {
      data = Arrays.copyOf(data, data.length * 2);
    }
    if (invert) {
      data[length++] = (byte) (0xff ^ b);
    } else {
      data[length++] = b;
    }
  }

  /**
   * Returns the underlying byte array.
   */
  public final byte[] getData() {
    return data;
  }

  /**
   * Returns the current length.
   */
  public final int getLength() {
    return length;
  }

  /**
   * Return the bytes in hex format.
   */
  public String dumpHex() {
    StringBuilder sb = new StringBuilder();
    for (int i = 0; i < length; i++) {
      byte b = data[i];
      int v = (b < 0 ? 256 + b : b);
      sb.append(String.format("x%02x", v));
    }
    return sb.toString();
  }

}
