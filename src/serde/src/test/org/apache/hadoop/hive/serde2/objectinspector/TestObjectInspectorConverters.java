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


package org.apache.hadoop.hive.serde2.objectinspector;

import junit.framework.TestCase;

import org.apache.hadoop.hive.serde2.io.ByteWritable;
import org.apache.hadoop.hive.serde2.io.DoubleWritable;
import org.apache.hadoop.hive.serde2.io.ShortWritable;
import org.apache.hadoop.hive.serde2.objectinspector.ObjectInspectorConverters.Converter;
import org.apache.hadoop.hive.serde2.objectinspector.primitive.PrimitiveObjectInspectorFactory;
import org.apache.hadoop.io.BooleanWritable;
import org.apache.hadoop.io.FloatWritable;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;

/**
 * TestObjectInspectorConverters.
 *
 */
public class TestObjectInspectorConverters extends TestCase {

  public void testObjectInspectorConverters() throws Throwable {
    try {
      // Boolean
      Converter booleanConverter = ObjectInspectorConverters.getConverter(
          PrimitiveObjectInspectorFactory.javaIntObjectInspector,
          PrimitiveObjectInspectorFactory.writableBooleanObjectInspector);
      assertEquals("BooleanConverter", new BooleanWritable(false),
          booleanConverter.convert(Integer.valueOf(0)));
      assertEquals("BooleanConverter", new BooleanWritable(true),
          booleanConverter.convert(Integer.valueOf(1)));

      // Byte
      Converter byteConverter = ObjectInspectorConverters.getConverter(
          PrimitiveObjectInspectorFactory.javaIntObjectInspector,
          PrimitiveObjectInspectorFactory.writableByteObjectInspector);
      assertEquals("ByteConverter", new ByteWritable((byte) 0), byteConverter
          .convert(Integer.valueOf(0)));
      assertEquals("ByteConverter", new ByteWritable((byte) 1), byteConverter
          .convert(Integer.valueOf(1)));

      // Short
      Converter shortConverter = ObjectInspectorConverters.getConverter(
          PrimitiveObjectInspectorFactory.javaIntObjectInspector,
          PrimitiveObjectInspectorFactory.writableShortObjectInspector);
      assertEquals("ShortConverter", new ShortWritable((short) 0),
          shortConverter.convert(Integer.valueOf(0)));
      assertEquals("ShortConverter", new ShortWritable((short) 1),
          shortConverter.convert(Integer.valueOf(1)));

      // Int
      Converter intConverter = ObjectInspectorConverters.getConverter(
          PrimitiveObjectInspectorFactory.javaIntObjectInspector,
          PrimitiveObjectInspectorFactory.writableIntObjectInspector);
      assertEquals("IntConverter", new IntWritable(0), intConverter
          .convert(Integer.valueOf(0)));
      assertEquals("IntConverter", new IntWritable(1), intConverter
          .convert(Integer.valueOf(1)));

      // Long
      Converter longConverter = ObjectInspectorConverters.getConverter(
          PrimitiveObjectInspectorFactory.javaIntObjectInspector,
          PrimitiveObjectInspectorFactory.writableLongObjectInspector);
      assertEquals("LongConverter", new LongWritable(0), longConverter
          .convert(Integer.valueOf(0)));
      assertEquals("LongConverter", new LongWritable(1), longConverter
          .convert(Integer.valueOf(1)));

      // Float
      Converter floatConverter = ObjectInspectorConverters.getConverter(
          PrimitiveObjectInspectorFactory.javaIntObjectInspector,
          PrimitiveObjectInspectorFactory.writableFloatObjectInspector);
      assertEquals("LongConverter", new FloatWritable(0), floatConverter
          .convert(Integer.valueOf(0)));
      assertEquals("LongConverter", new FloatWritable(1), floatConverter
          .convert(Integer.valueOf(1)));

      // Double
      Converter doubleConverter = ObjectInspectorConverters.getConverter(
          PrimitiveObjectInspectorFactory.javaIntObjectInspector,
          PrimitiveObjectInspectorFactory.writableDoubleObjectInspector);
      assertEquals("DoubleConverter", new DoubleWritable(0), doubleConverter
          .convert(Integer.valueOf(0)));
      assertEquals("DoubleConverter", new DoubleWritable(1), doubleConverter
          .convert(Integer.valueOf(1)));

      // Text
      Converter textConverter = ObjectInspectorConverters.getConverter(
          PrimitiveObjectInspectorFactory.javaIntObjectInspector,
          PrimitiveObjectInspectorFactory.writableStringObjectInspector);
      assertEquals("TextConverter", new Text("0"), textConverter
          .convert(Integer.valueOf(0)));
      assertEquals("TextConverter", new Text("1"), textConverter
          .convert(Integer.valueOf(1)));

    } catch (Throwable e) {
      e.printStackTrace();
      throw e;
    }

  }
}
