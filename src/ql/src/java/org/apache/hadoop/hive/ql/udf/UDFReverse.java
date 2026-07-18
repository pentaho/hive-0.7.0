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


package org.apache.hadoop.hive.ql.udf;

import org.apache.hadoop.hive.ql.exec.Description;
import org.apache.hadoop.hive.ql.exec.UDF;
import org.apache.hadoop.hive.ql.udf.generic.GenericUDFUtils;
import org.apache.hadoop.io.Text;

/**
 * UDFReverse.
 *
 */
@Description(name = "reverse", value = "_FUNC_(str) - reverse str", extended = "Example:\n"
    + "  > SELECT _FUNC_('Facebook') FROM src LIMIT 1;\n" + "  'koobecaF'")
public class UDFReverse extends UDF {
  private final Text result = new Text();

  /**
   * Reverse a portion of an array in-place.
   * 
   * @param arr
   *          The array where the data will be reversed.
   * @param first
   *          The beginning of the portion (inclusive).
   * @param last
   *          The end of the portion (inclusive).
   */
  private void reverse(byte[] arr, int first, int last) {
    for (int i = 0; i < (last - first + 1) / 2; i++) {
      byte temp = arr[last - i];
      arr[last - i] = arr[first + i];
      arr[first + i] = temp;
    }
  }

  public Text evaluate(Text s) {
    if (s == null) {
      return null;
    }

    // set() will only allocate memory if the buffer of result is smaller than
    // s.getLength() and will never resize the buffer down.
    result.set(s);

    // Now do an in-place reversal in result.getBytes(). First, reverse every
    // character, then reverse the whole string.
    byte[] data = result.getBytes();
    int prev = 0; // The index where the current char starts
    for (int i = 1; i < result.getLength(); i++) {
      if (GenericUDFUtils.isUtfStartByte(data[i])) {
        reverse(data, prev, i - 1);
        prev = i;
      }
    }
    reverse(data, prev, result.getLength() - 1);
    reverse(data, 0, result.getLength() - 1);

    return result;
  }
}
