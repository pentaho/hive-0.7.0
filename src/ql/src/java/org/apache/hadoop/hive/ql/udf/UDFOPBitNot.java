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


package org.apache.hadoop.hive.ql.udf;

import org.apache.hadoop.hive.ql.exec.Description;
import org.apache.hadoop.hive.serde2.io.ByteWritable;
import org.apache.hadoop.hive.serde2.io.ShortWritable;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;

/**
 * UDFOPBitNot.
 *
 */
@Description(name = "~", value = "_FUNC_ n - Bitwise not", extended = "Example:\n"
    + "  > SELECT _FUNC_ 0 FROM src LIMIT 1;\n" + "  -1")
public class UDFOPBitNot extends UDFBaseBitOP {

  public UDFOPBitNot() {
  }

  public ByteWritable evaluate(ByteWritable a) {
    if (a == null) {
      return null;
    }
    byteWritable.set((byte) (~a.get()));
    return byteWritable;
  }

  public ShortWritable evaluate(ShortWritable a) {
    if (a == null) {
      return null;
    }
    shortWritable.set((short) (~a.get()));
    return shortWritable;
  }

  public IntWritable evaluate(IntWritable a) {
    if (a == null) {
      return null;
    }
    intWritable.set(~a.get());
    return intWritable;
  }

  public LongWritable evaluate(LongWritable a) {
    if (a == null) {
      return null;
    }
    longWritable.set(~a.get());
    return longWritable;
  }

}
