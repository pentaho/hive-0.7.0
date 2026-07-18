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


package org.apache.hadoop.hive.serde2;

import java.lang.reflect.Type;

import org.apache.hadoop.io.BytesWritable;
import org.apache.hadoop.io.Writable;

/**
 * ByteStreamTypedSerDe.
 *
 */
public abstract class ByteStreamTypedSerDe extends TypedSerDe {

  protected ByteStream.Input bis;
  protected ByteStream.Output bos;

  public ByteStreamTypedSerDe(Type objectType) throws SerDeException {
    super(objectType);
    bos = new ByteStream.Output();
    bis = new ByteStream.Input();
  }

  @Override
  public Object deserialize(Writable field) throws SerDeException {
    Object retObj = super.deserialize(field);
    BytesWritable b = (BytesWritable) field;
    bis.reset(b.get(), b.getSize());
    return (retObj);
  }

}
