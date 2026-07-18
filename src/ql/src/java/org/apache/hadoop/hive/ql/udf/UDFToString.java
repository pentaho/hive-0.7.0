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

import org.apache.hadoop.hive.ql.exec.UDF;
import org.apache.hadoop.hive.serde2.ByteStream;
import org.apache.hadoop.hive.serde2.io.ByteWritable;
import org.apache.hadoop.hive.serde2.io.DoubleWritable;
import org.apache.hadoop.hive.serde2.io.ShortWritable;
import org.apache.hadoop.hive.serde2.lazy.LazyInteger;
import org.apache.hadoop.hive.serde2.lazy.LazyLong;
import org.apache.hadoop.io.BooleanWritable;
import org.apache.hadoop.io.FloatWritable;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;

/**
 * UDFToString.
 *
 */
public class UDFToString extends UDF {
  private Text t = new Text();
  private ByteStream.Output out = new ByteStream.Output();

  public UDFToString() {
  }

  public Text evaluate(NullWritable i) {
    return null;
  }

  private byte[] trueBytes = {'T', 'R', 'U', 'E'};
  private byte[] falseBytes = {'F', 'A', 'L', 'S', 'E'};

  public Text evaluate(BooleanWritable i) {
    if (i == null) {
      return null;
    } else {
      t.clear();
      t.set(i.get() ? trueBytes : falseBytes);
      return t;
    }
  }

  public Text evaluate(ByteWritable i) {
    if (i == null) {
      return null;
    } else {
      out.reset();
      LazyInteger.writeUTF8NoException(out, i.get());
      t.set(out.getData(), 0, out.getCount());
      return t;
    }
  }

  public Text evaluate(ShortWritable i) {
    if (i == null) {
      return null;
    } else {
      out.reset();
      LazyInteger.writeUTF8NoException(out, i.get());
      t.set(out.getData(), 0, out.getCount());
      return t;
    }
  }

  public Text evaluate(IntWritable i) {
    if (i == null) {
      return null;
    } else {
      out.reset();
      LazyInteger.writeUTF8NoException(out, i.get());
      t.set(out.getData(), 0, out.getCount());
      return t;
    }
  }

  public Text evaluate(LongWritable i) {
    if (i == null) {
      return null;
    } else {
      out.reset();
      LazyLong.writeUTF8NoException(out, i.get());
      t.set(out.getData(), 0, out.getCount());
      return t;
    }
  }

  public Text evaluate(FloatWritable i) {
    if (i == null) {
      return null;
    } else {
      t.set(i.toString());
      return t;
    }
  }

  public Text evaluate(DoubleWritable i) {
    if (i == null) {
      return null;
    } else {
      t.set(i.toString());
      return t;
    }
  }

}
