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

import java.util.List;
import java.util.Map;

/**
 * Class for testing TestReflectionObjectInspector.
 */
public class MyStruct {
  public int myInt;
  public Integer myInteger;
  public String myString;
  // Note: this is a recursive struct
  public MyStruct myStruct;
  public List<String> myListString;
  public Map<String, String> myMapStringString;
}
