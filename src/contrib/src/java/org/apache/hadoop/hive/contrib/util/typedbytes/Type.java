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


package org.apache.hadoop.hive.contrib.util.typedbytes;

/**
 * The possible type codes.
 */
public enum Type {

  // codes for supported types (< 50):
  BYTES(0), BYTE(1), BOOL(2), INT(3), LONG(4), FLOAT(5), DOUBLE(6), STRING(7), VECTOR(
      8), LIST(9), MAP(10), SHORT(11), NULL(12),

  // application-specific codes (50-200):
  WRITABLE(50),

  ENDOFRECORD(177),

  // low-level codes (> 200):
  MARKER(255);

  final int code;

  Type(int code) {
    this.code = code;
  }
}
