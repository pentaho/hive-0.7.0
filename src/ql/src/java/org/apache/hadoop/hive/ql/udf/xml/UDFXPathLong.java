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

@Description(
    name = "xpath_long",
    value = "_FUNC_(xml, xpath) - Returns a long value that matches the xpath expression",
    extended = "Example:\n"
    + "  > SELECT _FUNC_('<a><b>1</b><b>2</b></a>','sum(a/b)') FROM src LIMIT 1;\n"
    + "  3")
public class UDFXPathLong extends UDF {

  private final UDFXPathUtil xpath = new UDFXPathUtil();

  public long evaluate(String xml, String path) {
    return xpath.evalNumber(xml, path).longValue();
  }
}
