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

import org.apache.hadoop.hive.serde2.lazybinary.objectinspector.LazyBinaryListObjectInspector;
import org.apache.hadoop.hive.serde2.lazybinary.objectinspector.LazyBinaryMapObjectInspector;
import org.apache.hadoop.hive.serde2.lazybinary.objectinspector.LazyBinaryStructObjectInspector;
import org.apache.hadoop.hive.serde2.objectinspector.ObjectInspector;
import org.apache.hadoop.hive.serde2.objectinspector.PrimitiveObjectInspector;
import org.apache.hadoop.hive.serde2.objectinspector.PrimitiveObjectInspector.PrimitiveCategory;
import org.apache.hadoop.hive.serde2.objectinspector.primitive.WritableBooleanObjectInspector;
import org.apache.hadoop.hive.serde2.objectinspector.primitive.WritableByteObjectInspector;
import org.apache.hadoop.hive.serde2.objectinspector.primitive.WritableDoubleObjectInspector;
import org.apache.hadoop.hive.serde2.objectinspector.primitive.WritableFloatObjectInspector;
import org.apache.hadoop.hive.serde2.objectinspector.primitive.WritableIntObjectInspector;
import org.apache.hadoop.hive.serde2.objectinspector.primitive.WritableLongObjectInspector;
import org.apache.hadoop.hive.serde2.objectinspector.primitive.WritableShortObjectInspector;
import org.apache.hadoop.hive.serde2.objectinspector.primitive.WritableStringObjectInspector;
import org.apache.hadoop.hive.serde2.objectinspector.primitive.WritableVoidObjectInspector;

/**
 * LazyBinaryFactory.
 *
 */
public final class LazyBinaryFactory {

  /**
   * Create a lazy binary primitive class given the type name.
   */
  public static LazyBinaryPrimitive<?, ?> createLazyBinaryPrimitiveClass(
      PrimitiveObjectInspector oi) {
    PrimitiveCategory p = oi.getPrimitiveCategory();
    switch (p) {
    case BOOLEAN:
      return new LazyBinaryBoolean((WritableBooleanObjectInspector) oi);
    case BYTE:
      return new LazyBinaryByte((WritableByteObjectInspector) oi);
    case SHORT:
      return new LazyBinaryShort((WritableShortObjectInspector) oi);
    case INT:
      return new LazyBinaryInteger((WritableIntObjectInspector) oi);
    case LONG:
      return new LazyBinaryLong((WritableLongObjectInspector) oi);
    case FLOAT:
      return new LazyBinaryFloat((WritableFloatObjectInspector) oi);
    case DOUBLE:
      return new LazyBinaryDouble((WritableDoubleObjectInspector) oi);
    case STRING:
      return new LazyBinaryString((WritableStringObjectInspector) oi);
    case VOID: // for NULL
      return new LazyBinaryVoid((WritableVoidObjectInspector) oi);
    default:
      throw new RuntimeException("Internal error: no LazyBinaryObject for " + p);
    }
  }

  /**
   * Create a hierarchical LazyBinaryObject based on the given typeInfo.
   */
  public static LazyBinaryObject createLazyBinaryObject(ObjectInspector oi) {
    ObjectInspector.Category c = oi.getCategory();
    switch (c) {
    case PRIMITIVE:
      return createLazyBinaryPrimitiveClass((PrimitiveObjectInspector) oi);
    case MAP:
      return new LazyBinaryMap((LazyBinaryMapObjectInspector) oi);
    case LIST:
      return new LazyBinaryArray((LazyBinaryListObjectInspector) oi);
    case STRUCT:
      return new LazyBinaryStruct((LazyBinaryStructObjectInspector) oi);
    }

    throw new RuntimeException("Hive LazyBinarySerDe Internal error.");
  }

  private LazyBinaryFactory() {
    // prevent instantiation
  }
}
