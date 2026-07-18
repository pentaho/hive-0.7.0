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
 * UDFOPBitAnd.
 *
 */
@Description(name = "&", value = "a _FUNC_ b - Bitwise and", extended = "Example:\n"
    + "  > SELECT 3 _FUNC_ 5 FROM src LIMIT 1;\n" + "  1")
public class UDFOPBitAnd extends UDFBaseBitOP {

  public UDFOPBitAnd() {
  }

  public ByteWritable evaluate(ByteWritable a, ByteWritable b) {
    if (a == null || b == null) {
      return null;
    }
    byteWritable.set((byte) (a.get() & b.get()));
    return byteWritable;
  }

  public ShortWritable evaluate(ShortWritable a, ShortWritable b) {
    if (a == null || b == null) {
      return null;
    }
    shortWritable.set((short) (a.get() & b.get()));
    return shortWritable;
  }

  public IntWritable evaluate(IntWritable a, IntWritable b) {
    if (a == null || b == null) {
      return null;
    }
    intWritable.set(a.get() & b.get());
    return intWritable;
  }

  public LongWritable evaluate(LongWritable a, LongWritable b) {
    if (a == null || b == null) {
      return null;
    }
    longWritable.set(a.get() & b.get());
    return longWritable;
  }

}
