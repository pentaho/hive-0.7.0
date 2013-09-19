/*!
* Copyright 2010 - 2013 Pentaho Corporation.  All rights reserved.
*
* Licensed under the Apache License, Version 2.0 (the "License");
* you may not use this file except in compliance with the License.
* You may obtain a copy of the License at
*
* http://www.apache.org/licenses/LICENSE-2.0
*
* Unless required by applicable law or agreed to in writing, software
* distributed under the License is distributed on an "AS IS" BASIS,
* WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
* See the License for the specific language governing permissions and
* limitations under the License.
*
*/

package org.apache.hadoop.hive.serde2.lazybinary;

import org.apache.hadoop.hive.serde2.binarysortable.MyTestInnerStruct;

public class MyTestClassSmaller {
  Byte myByte;
  Short myShort;
  Integer myInt;
  Long myLong;
  Float myFloat;
  Double myDouble;
  String myString;
  MyTestInnerStruct myStruct;

  public MyTestClassSmaller() {
  }

  public MyTestClassSmaller(Byte b, Short s, Integer i, Long l, Float f,
      Double d, String st, MyTestInnerStruct is) {
    myByte = b;
    myShort = s;
    myInt = i;
    myLong = l;
    myFloat = f;
    myDouble = d;
    myString = st;
    myStruct = is;
  }
}
