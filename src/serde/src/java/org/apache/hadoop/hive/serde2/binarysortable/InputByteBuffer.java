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

import java.io.EOFException;
import java.io.IOException;

/**
 * This class is much more efficient than ByteArrayInputStream because none of
 * the methods are synchronized.
 */
public class InputByteBuffer {

  byte[] data;
  int start;
  int end;

  /**
   * Reset the byte buffer to the given byte range.
   */
  public void reset(byte[] data, int start, int end) {
    this.data = data;
    this.start = start;
    this.end = end;
  }

  public final byte read() throws IOException {
    return read(false);
  }

  /**
   * Read one byte from the byte buffer. Final method to help inlining.
   * 
   * @param invert
   *          whether we want to invert all the bits.
   */
  public final byte read(boolean invert) throws IOException {
    if (start >= end) {
      throw new EOFException();
    }
    if (invert) {
      return (byte) (0xff ^ data[start++]);
    } else {
      return data[start++];
    }
  }

  /**
   * Return the current position. Final method to help inlining.
   */
  public final int tell() {
    return start;
  }

  /**
   * Set the current position. Final method to help inlining.
   */
  public final void seek(int position) {
    start = position;
  }

  public final int getEnd() {
    return end;
  }

  /**
   * Returns the underlying byte array.
   */
  public final byte[] getData() {
    return data;
  }

  /**
   * Return the bytes in hex format.
   */
  public String dumpHex() {
    StringBuilder sb = new StringBuilder();
    for (int i = start; i < end; i++) {
      byte b = data[i];
      int v = (b < 0 ? 256 + b : b);
      sb.append(String.format("x%02x", v));
    }
    return sb.toString();
  }
}
