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


package org.apache.hadoop.hive.ql.io;

import java.io.Serializable;

/**
 * Simple test object.
 */
public class JavaTestObjFlatFileInputFormat implements Serializable {
  public String s;
  public int num;

  public JavaTestObjFlatFileInputFormat(String s, int num) {
    this.s = s;
    this.num = num;
  }

  public JavaTestObjFlatFileInputFormat() {
  }
}
