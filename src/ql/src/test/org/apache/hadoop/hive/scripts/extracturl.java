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


package org.apache.hadoop.hive.scripts;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * extracturl.
 *
 */
public final class extracturl {

  protected static final Pattern pattern = Pattern.compile(
      "<a href=\"http://([\\w\\d]+\\.html)\">link</a>",
      Pattern.CASE_INSENSITIVE);
  static InputStreamReader converter = new InputStreamReader(System.in);
  static BufferedReader in = new BufferedReader(converter);

  public static void main(String[] args) {
    String input;
    try {
      while ((input = in.readLine()) != null) {
        Matcher m = pattern.matcher(input);

        while (m.find()) {
          String url = input.substring(m.start(1), m.end(1));
          System.out.println(url + "\t" + "1");
        }
      }
    } catch (Exception e) {
      e.printStackTrace();
      System.exit(1);
    }
  }

  private extracturl() {
    // prevent instantiation
  }
}
