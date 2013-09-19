/*!
* Copyright 2010 - 2013 Pentaho Corporation.  All rights reserved.
*
* Licensed under the Apache License, Version 2.0 (the "License");
* you may not use this file except in compliance with the License.
* You may obtain a copy of the License at
*
* http://www.apache.org/licenses/LICENSE-2.0
*
* Unless required by applicable law or agreed to in writing, software
* distributed under the License is distributed on an "AS IS" BASIS,
* WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
* See the License for the specific language governing permissions and
* limitations under the License.
*
*/

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
