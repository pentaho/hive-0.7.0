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


package org.apache.hadoop.hive.serde2.lazy;

/**
 * ByteArrayRef stores a reference to a byte array.
 * 
 * The LazyObject hierarchy uses a reference to a single ByteArrayRef, so that
 * it's much faster to switch to the next row and release the reference to the
 * old row (so that the system can do garbage collection if needed).
 */
public class ByteArrayRef {

  /**
   * Stores the actual data.
   */
  byte[] data;

  public byte[] getData() {
    return data;
  }

  public void setData(byte[] data) {
    this.data = data;
  }

}
