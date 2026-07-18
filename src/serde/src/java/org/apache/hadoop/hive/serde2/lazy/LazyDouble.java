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

import java.nio.charset.CharacterCodingException;

import org.apache.hadoop.hive.serde2.io.DoubleWritable;
import org.apache.hadoop.hive.serde2.lazy.objectinspector.primitive.LazyDoubleObjectInspector;
import org.apache.hadoop.io.Text;

/**
 * LazyObject for storing a value of Double.
 * 
 */
public class LazyDouble extends
    LazyPrimitive<LazyDoubleObjectInspector, DoubleWritable> {

  public LazyDouble(LazyDoubleObjectInspector oi) {
    super(oi);
    data = new DoubleWritable();
  }

  public LazyDouble(LazyDouble copy) {
    super(copy);
    data = new DoubleWritable(copy.data.get());
  }

  @Override
  public void init(ByteArrayRef bytes, int start, int length) {
    try {
      data.set(Double.parseDouble(Text.decode(bytes.getData(), start, length)));
      isNull = false;
    } catch (NumberFormatException e) {
      isNull = true;
    } catch (CharacterCodingException e) {
      isNull = true;
    }
  }

}
