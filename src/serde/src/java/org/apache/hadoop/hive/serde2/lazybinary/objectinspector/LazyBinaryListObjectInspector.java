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


package org.apache.hadoop.hive.serde2.lazybinary.objectinspector;

import java.util.List;

import org.apache.hadoop.hive.serde2.lazybinary.LazyBinaryArray;
import org.apache.hadoop.hive.serde2.objectinspector.ObjectInspector;
import org.apache.hadoop.hive.serde2.objectinspector.StandardListObjectInspector;

/**
 * ObjectInspector for LazyBinaryList.
 */
public class LazyBinaryListObjectInspector extends StandardListObjectInspector {

  protected LazyBinaryListObjectInspector(
      ObjectInspector listElementObjectInspector) {
    super(listElementObjectInspector);
  }

  @Override
  public List<?> getList(Object data) {
    if (data == null) {
      return null;
    }
    LazyBinaryArray array = (LazyBinaryArray) data;
    return array.getList();
  }

  @Override
  public Object getListElement(Object data, int index) {
    if (data == null) {
      return null;
    }
    LazyBinaryArray array = (LazyBinaryArray) data;
    return array.getListElementObject(index);
  }

  @Override
  public int getListLength(Object data) {
    if (data == null) {
      return -1;
    }
    LazyBinaryArray array = (LazyBinaryArray) data;
    return array.getListLength();
  }
}
