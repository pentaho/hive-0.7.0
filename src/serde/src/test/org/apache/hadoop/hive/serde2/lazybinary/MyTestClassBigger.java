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

import java.util.List;
import java.util.Map;

import org.apache.hadoop.hive.serde2.binarysortable.MyTestInnerStruct;

/**
 * MyTestClassBigger.
 *
 */
public class MyTestClassBigger {
  Byte myByte;
  Short myShort;
  Integer myInt;
  Long myLong;
  Float myFloat;
  Double myDouble;
  String myString;
  MyTestInnerStruct myStruct;
  List<Integer> myList;
  Map<String, List<MyTestInnerStruct>> myMap;

  public MyTestClassBigger() {
  }

  public MyTestClassBigger(Byte b, Short s, Integer i, Long l, Float f,
      Double d, String st, MyTestInnerStruct is, List<Integer> li,
      Map<String, List<MyTestInnerStruct>> mp) {
    myByte = b;
    myShort = s;
    myInt = i;
    myLong = l;
    myFloat = f;
    myDouble = d;
    myString = st;
    myStruct = is;
    myList = li;
    myMap = mp;
  }
}
