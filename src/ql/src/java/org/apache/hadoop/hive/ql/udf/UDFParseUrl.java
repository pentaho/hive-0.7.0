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

import java.net.URL;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.hadoop.hive.ql.exec.Description;
import org.apache.hadoop.hive.ql.exec.UDF;

/**
 * UDF to extract specfic parts from URL For example,
 * parse_url('http://facebook.com/path/p1.php?query=1', 'HOST') will return
 * 'facebook.com' For example,
 * parse_url('http://facebook.com/path/p1.php?query=1', 'PATH') will return
 * '/path/p1.php' parse_url('http://facebook.com/path/p1.php?query=1', 'QUERY')
 * will return 'query=1'
 * parse_url('http://facebook.com/path/p1.php?query=1#Ref', 'REF') will return
 * 'Ref' parse_url('http://facebook.com/path/p1.php?query=1#Ref', 'PROTOCOL')
 * will return 'http' Possible values are
 * HOST,PATH,QUERY,REF,PROTOCOL,AUTHORITY,FILE,USERINFO Also you can get a value
 * of particular key in QUERY, using syntax QUERY:<KEY_NAME> eg: QUERY:k1.
 */
@Description(name = "parse_url",
    value = "_FUNC_(url, partToExtract[, key]) - extracts a part from a URL",
    extended = "Parts: HOST, PATH, QUERY, REF, PROTOCOL, AUTHORITY, FILE, "
    + "USERINFO\nkey specifies which query to extract\n"
    + "Example:\n"
    + "  > SELECT _FUNC_('http://facebook.com/path/p1.php?query=1', "
    + "'HOST') FROM src LIMIT 1;\n"
    + "  'facebook.com'\n"
    + "  > SELECT _FUNC_('http://facebook.com/path/p1.php?query=1', "
    + "'QUERY') FROM src LIMIT 1;\n"
    + "  'query=1'\n"
    + "  > SELECT _FUNC_('http://facebook.com/path/p1.php?query=1', "
    + "'QUERY', 'query') FROM src LIMIT 1;\n" + "  '1'")
public class UDFParseUrl extends UDF {
  private String lastUrlStr = null;
  private URL url = null;
  private Pattern p = null;
  private String lastKey = null;

  public UDFParseUrl() {
  }

  public String evaluate(String urlStr, String partToExtract) {
    if (urlStr == null || partToExtract == null) {
      return null;
    }

    if (lastUrlStr == null || !urlStr.equals(lastUrlStr)) {
      try {
        url = new URL(urlStr);
      } catch (Exception e) {
        return null;
      }
    }
    lastUrlStr = urlStr;

    if (partToExtract.equals("HOST")) {
      return url.getHost();
    }
    if (partToExtract.equals("PATH")) {
      return url.getPath();
    }
    if (partToExtract.equals("QUERY")) {
      return url.getQuery();
    }
    if (partToExtract.equals("REF")) {
      return url.getRef();
    }
    if (partToExtract.equals("PROTOCOL")) {
      return url.getProtocol();
    }
    if (partToExtract.equals("FILE")) {
      return url.getFile();
    }
    if (partToExtract.equals("AUTHORITY")) {
      return url.getAuthority();
    }
    if (partToExtract.equals("USERINFO")) {
      return url.getUserInfo();
    }

    return null;
  }

  public String evaluate(String urlStr, String partToExtract, String key) {
    if (!partToExtract.equals("QUERY")) {
      return null;
    }

    String query = this.evaluate(urlStr, partToExtract);
    if (query == null) {
      return null;
    }

    if (!key.equals(lastKey)) {
      p = Pattern.compile("(&|^)" + key + "=([^&]*)");
    }

    lastKey = key;
    Matcher m = p.matcher(query);
    if (m.find()) {
      return m.group(2);
    }
    return null;
  }
}
