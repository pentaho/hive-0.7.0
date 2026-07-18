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


package org.apache.hadoop.hive.common.io;

import java.io.ByteArrayOutputStream;
import java.io.DataInput;
import java.io.IOException;
import java.io.OutputStream;

/**
 * A thread-not-safe version of ByteArrayOutputStream, which removes all
 * synchronized modifiers.
 */
public class NonSyncByteArrayOutputStream extends ByteArrayOutputStream {
  public NonSyncByteArrayOutputStream(int size) {
    super(size);
  }

  public NonSyncByteArrayOutputStream() {
    super();
  }

  public byte[] getData() {
    return buf;
  }

  public int getLength() {
    return count;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void reset() {
    count = 0;
  }

  public void write(DataInput in, int length) throws IOException {
    enLargeBuffer(length);
    in.readFully(buf, count, length);
    count += length;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void write(int b) {
    enLargeBuffer(1);
    buf[count] = (byte) b;
    count += 1;
  }

  private int enLargeBuffer(int increment) {
    int temp = count + increment;
    int newLen = temp;
    if (temp > buf.length) {
      if ((buf.length << 1) > temp) {
        newLen = buf.length << 1;
      }
      byte newbuf[] = new byte[newLen];
      System.arraycopy(buf, 0, newbuf, 0, count);
      buf = newbuf;
    }
    return newLen;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void write(byte b[], int off, int len) {
    if ((off < 0) || (off > b.length) || (len < 0) || ((off + len) > b.length)
        || ((off + len) < 0)) {
      throw new IndexOutOfBoundsException();
    } else if (len == 0) {
      return;
    }
    enLargeBuffer(len);
    System.arraycopy(b, off, buf, count, len);
    count += len;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void writeTo(OutputStream out) throws IOException {
    out.write(buf, 0, count);
  }
}
