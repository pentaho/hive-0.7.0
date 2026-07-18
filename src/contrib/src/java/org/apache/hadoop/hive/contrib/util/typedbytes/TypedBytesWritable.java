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

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import org.apache.hadoop.io.BytesWritable;

/**
 * Writable for typed bytes.
 */
public class TypedBytesWritable extends BytesWritable {

  /** Create a TypedBytesWritable. */
  public TypedBytesWritable() {
    super();
  }

  /** Create a TypedBytesWritable with a given byte array as initial value. */
  public TypedBytesWritable(byte[] bytes) {
    super(bytes);
  }

  /** Set the typed bytes from a given Java object. */
  public void setValue(Object obj) {
    try {
      ByteArrayOutputStream baos = new ByteArrayOutputStream();
      TypedBytesOutput tbo = TypedBytesOutput.get(new DataOutputStream(baos));
      tbo.write(obj);
      byte[] bytes = baos.toByteArray();
      set(bytes, 0, bytes.length);
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  /** Get the typed bytes as a Java object. */
  public Object getValue() {
    try {
      ByteArrayInputStream bais = new ByteArrayInputStream(get());
      TypedBytesInput tbi = TypedBytesInput.get(new DataInputStream(bais));
      Object obj = tbi.read();
      return obj;
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  /** Get the type code embedded in the first byte. */
  public Type getType() {
    byte[] bytes = get();
    if (bytes == null || bytes.length == 0) {
      return null;
    }
    for (Type type : Type.values()) {
      if (type.code == bytes[0]) {
        return type;
      }
    }
    return null;
  }

  /** Generate a suitable string representation. */
  @Override
  public String toString() {
    return getValue().toString();
  }

}
