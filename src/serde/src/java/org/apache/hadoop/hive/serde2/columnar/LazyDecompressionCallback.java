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


package org.apache.hadoop.hive.serde2.columnar;

import java.io.IOException;

/**
 * Used to call back lazy decompression process.
 * 
 * @see #BytesRefWritable
 */
public interface LazyDecompressionCallback {

  byte[] decompress() throws IOException;

}
