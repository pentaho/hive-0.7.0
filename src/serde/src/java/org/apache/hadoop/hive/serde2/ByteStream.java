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


package org.apache.hadoop.hive.serde2;

import org.apache.hadoop.hive.common.io.NonSyncByteArrayInputStream;
import org.apache.hadoop.hive.common.io.NonSyncByteArrayOutputStream;

/**
 * Extensions to bytearrayinput/output streams.
 * 
 */
public class ByteStream {
  /**
   * Input.
   *
   */
  public static class Input extends NonSyncByteArrayInputStream {
    public byte[] getData() {
      return buf;
    }

    public int getCount() {
      return count;
    }

    public void reset(byte[] argBuf, int argCount) {
      buf = argBuf;
      mark = pos = 0;
      count = argCount;
    }

    public Input() {
      super(new byte[1]);
    }

    public Input(byte[] buf) {
      super(buf);
    }

    public Input(byte[] buf, int offset, int length) {
      super(buf, offset, length);
    }
  }

  /**
   * Output.
   *
   */
  public static class Output extends NonSyncByteArrayOutputStream {
    @Override
    public byte[] getData() {
      return buf;
    }

    public int getCount() {
      return count;
    }

    public Output() {
      super();
    }

    public Output(int size) {
      super(size);
    }
  }
}
