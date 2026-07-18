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
    name = "xpath_boolean",
    value = "_FUNC_(xml, xpath) - Evaluates a boolean xpath expression",
    extended = "Example:\n"
    + "  > SELECT _FUNC_('<a><b>1</b></a>','a/b') FROM src LIMIT 1;\n"
    + "  true\n"
    + "  > SELECT _FUNC_('<a><b>1</b></a>','a/b = 2') FROM src LIMIT 1;\n"
    + "  false")
public class UDFXPathBoolean extends UDF {

  private final UDFXPathUtil xpath = new UDFXPathUtil () ;

  public boolean evaluate(String xml, String path) {
    return xpath.evalBoolean(xml, path).booleanValue();
  }
}
