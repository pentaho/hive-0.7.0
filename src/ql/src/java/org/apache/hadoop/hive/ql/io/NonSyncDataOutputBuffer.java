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

import java.io.DataInput;
import java.io.DataOutputStream;
import java.io.IOException;

import org.apache.hadoop.hive.common.io.NonSyncByteArrayOutputStream;

/**
 * A thread-not-safe version of Hadoop's DataOutputBuffer, which removes all
 * synchronized modifiers.
 */
public class NonSyncDataOutputBuffer extends DataOutputStream {

  private final NonSyncByteArrayOutputStream buffer;

  /** Constructs a new empty buffer. */
  public NonSyncDataOutputBuffer() {
    this(new NonSyncByteArrayOutputStream());
  }

  private NonSyncDataOutputBuffer(NonSyncByteArrayOutputStream buffer) {
    super(buffer);
    this.buffer = buffer;
  }

  /**
   * Returns the current contents of the buffer. Data is only valid to
   * {@link #getLength()}.
   */
  public byte[] getData() {
    return buffer.getData();
  }

  /** Returns the length of the valid data currently in the buffer. */
  public int getLength() {
    return buffer.getLength();
  }

  /** Resets the buffer to empty. */
  public NonSyncDataOutputBuffer reset() {
    written = 0;
    buffer.reset();
    return this;
  }

  /** Writes bytes from a DataInput directly into the buffer. */
  public void write(DataInput in, int length) throws IOException {
    buffer.write(in, length);
  }

  @Override
  public void write(int b) throws IOException {
    buffer.write(b);
    incCount(1);
  }

  @Override
  public void write(byte b[], int off, int len) throws IOException {
    buffer.write(b, off, len);
    incCount(len);
  }

  private void incCount(int value) {
    if (written + value < 0) {
      written = Integer.MAX_VALUE;
    } else {
      written += value;
    }
  }
}
