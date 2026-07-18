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

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Properties;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hive.serde2.objectinspector.ObjectInspector;
import org.apache.hadoop.hive.serde2.objectinspector.ObjectInspectorFactory;
import org.apache.hadoop.io.BytesWritable;
import org.apache.hadoop.io.Writable;
import org.apache.hadoop.util.ReflectionUtils;

/**
 * TypedSerDe.
 *
 */
public abstract class TypedSerDe implements SerDe {

  protected Type objectType;
  protected Class<?> objectClass;

  public TypedSerDe(Type objectType) throws SerDeException {
    this.objectType = objectType;
    if (objectType instanceof Class) {
      objectClass = (Class<?>) objectType;
    } else if (objectType instanceof ParameterizedType) {
      objectClass = (Class<?>) (((ParameterizedType) objectType).getRawType());
    } else {
      throw new SerDeException("Cannot create TypedSerDe with type "
          + objectType);
    }
  }

  protected Object deserializeCache;

  public Object deserialize(Writable blob) throws SerDeException {
    if (deserializeCache == null) {
      return ReflectionUtils.newInstance(objectClass, null);
    } else {
      assert (deserializeCache.getClass().equals(objectClass));
      return deserializeCache;
    }
  }

  public ObjectInspector getObjectInspector() throws SerDeException {
    return ObjectInspectorFactory.getReflectionObjectInspector(objectType,
        getObjectInspectorOptions());
  }

  protected ObjectInspectorFactory.ObjectInspectorOptions getObjectInspectorOptions() {
    return ObjectInspectorFactory.ObjectInspectorOptions.JAVA;
  }

  public void initialize(Configuration job, Properties tbl) throws SerDeException {
    // do nothing
  }

  public Class<? extends Writable> getSerializedClass() {
    return BytesWritable.class;
  }

  public Writable serialize(Object obj, ObjectInspector objInspector) throws SerDeException {
    throw new RuntimeException("not supported");
  }

}
