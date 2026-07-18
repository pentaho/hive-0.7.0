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


package org.apache.hadoop.hive.serde2.lazybinary;

import org.apache.hadoop.hive.serde2.lazy.ByteArrayRef;
import org.apache.hadoop.hive.serde2.objectinspector.primitive.WritableStringObjectInspector;
import org.apache.hadoop.io.Text;

/**
 * The serialization of LazyBinaryString is very simple: start A end bytes[] ->
 * |---------------------------------|
 * 
 * Section A is just an array of bytes which are exactly the Text contained in
 * this object.
 * 
 */
public class LazyBinaryString extends
    LazyBinaryPrimitive<WritableStringObjectInspector, Text> {

  LazyBinaryString(WritableStringObjectInspector OI) {
    super(OI);
    data = new Text();
  }

  public LazyBinaryString(LazyBinaryString copy) {
    super(copy);
    data = new Text(copy.data);
  }

  @Override
  public void init(ByteArrayRef bytes, int start, int length) {
    assert (length > -1);
    data.set(bytes.getData(), start, length);
  }
}
