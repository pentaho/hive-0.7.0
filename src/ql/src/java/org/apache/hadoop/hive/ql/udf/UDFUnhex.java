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
import org.apache.hadoop.io.Text;

/**
 * UDFUnhex.
 *
 */
@Description(name = "unhex",
    value = "_FUNC_(str) - Converts hexadecimal argument to string",
    extended = "Performs the inverse operation of HEX(str). That is, it interprets\n"
    + "each pair of hexadecimal digits in the argument as a number and\n"
    + "converts it to the character represented by the number. The\n"
    + "resulting characters are returned as a binary string.\n\n"
    + "Example:\n"
    + "> SELECT UNHEX('4D7953514C') from src limit 1;\n"
    + "'MySQL'\n"
    + "> SELECT UNHEX(HEX('string')) from src limit 1;\n"
    + "'string'\n"
    + "> SELECT HEX(UNHEX('1267')) from src limit 1;\n"
    + "'1267'\n\n"
    + "The characters in the argument string must be legal hexadecimal\n"
    + "digits: '0' .. '9', 'A' .. 'F', 'a' .. 'f'. If UNHEX() encounters\n"
    + "any nonhexadecimal digits in the argument, it returns NULL. Also,\n"
    + "if there are an odd number of characters a leading 0 is appended.")
public class UDFUnhex extends UDF {

  /**
   * Convert every two hex digits in s into.
   * 
   */
  public Text evaluate(Text s) {
    if (s == null) {
      return null;
    }

    // append a leading 0 if needed
    String str;
    if (s.getLength() % 2 == 1) {
      str = "0" + s.toString();
    } else {
      str = s.toString();
    }

    byte[] result = new byte[str.length() / 2];
    for (int i = 0; i < str.length(); i += 2) {
      try {
        result[i / 2] = ((byte) Integer.parseInt(str.substring(i, i + 2), 16));
      } catch (NumberFormatException e) {
        // invalid character present, return null
        return null;
      }
    }

    return new Text(result);
  }
}
