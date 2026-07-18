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


package org.apache.hadoop.hive.ql.udf.generic;

import org.apache.hadoop.hive.serde2.typeinfo.TypeInfo;

/**
 * A simple implementation of <tt>GenericUDAFParameterInfo</tt>.
 *
 */
public class SimpleGenericUDAFParameterInfo implements GenericUDAFParameterInfo
{

  private final TypeInfo[] parameters;
  private final boolean distinct;
  private final boolean allColumns;

  public SimpleGenericUDAFParameterInfo(TypeInfo[] params, boolean distinct,
      boolean allColumns) {
    this.parameters = params;
    this.distinct = distinct;
    this.allColumns = allColumns;
  }

  @Override
  public TypeInfo[] getParameters() {
    return parameters;
  }

  @Override
  public boolean isDistinct() {
    return distinct;
  }

  @Override
  public boolean isAllColumns() {
    return allColumns;
  }
}
