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

import org.apache.hadoop.hive.serde2.lazy.objectinspector.primitive.LazyFloatObjectInspector;
import org.apache.hadoop.io.FloatWritable;
import org.apache.hadoop.io.Text;

/**
 * LazyObject for storing a value of Double.
 * 
 */
public class LazyFloat extends
    LazyPrimitive<LazyFloatObjectInspector, FloatWritable> {

  public LazyFloat(LazyFloatObjectInspector oi) {
    super(oi);
    data = new FloatWritable();
  }

  public LazyFloat(LazyFloat copy) {
    super(copy);
    data = new FloatWritable(copy.data.get());
  }

  @Override
  public void init(ByteArrayRef bytes, int start, int length) {
    try {
      data.set(Float.parseFloat(Text.decode(bytes.getData(), start, length)));
      isNull = false;
    } catch (NumberFormatException e) {
      isNull = true;
    } catch (CharacterCodingException e) {
      isNull = true;
    }
  }

}
