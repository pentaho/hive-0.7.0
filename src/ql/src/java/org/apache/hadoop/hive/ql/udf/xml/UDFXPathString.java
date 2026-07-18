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


package org.apache.hadoop.hive.ql.udf.xml;

import org.apache.hadoop.hive.ql.exec.Description;
import org.apache.hadoop.hive.ql.exec.UDF;
import org.apache.hadoop.io.Text;

@Description(name = "xpath_string",
    value = "_FUNC_(xml, xpath) - Returns the text contents of the first xml node that matches the xpath expression",
    extended = "Example:\n"
    + "  > SELECT _FUNC_('<a><b>b</b><c>cc</c></a>','a/c') FROM src LIMIT 1;\n"
    + "  'cc'\n"
    + "  > SELECT _FUNC_('<a><b>b1</b><b>b2</b></a>','a/b') FROM src LIMIT 1;\n"
    + "  'b1'\n"
    + "  > SELECT _FUNC_('<a><b>b1</b><b>b2</b></a>','a/b[2]') FROM src LIMIT 1;\n"
    + "  'b2'\n"
    + "  > SELECT _FUNC_('<a><b>b1</b><b>b2</b></a>','a') FROM src LIMIT 1;\n"
    + "  'b1b2'"
    )
public class UDFXPathString extends UDF {

  private final UDFXPathUtil xpath = new UDFXPathUtil();
  private final Text result = new Text();

  public Text evaluate(String xml, String path) {
    String s = xpath.evalString(xml, path);
    if (s == null) {
      return null;
    }

    result.set(s);
    return result;
  }
}
