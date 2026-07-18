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

import org.apache.hadoop.hive.ql.parse.SemanticException;
import org.apache.hadoop.hive.serde2.typeinfo.TypeInfo;

/**
 * An abstract class to help facilitate existing implementations of
 * <tt>GenericUDAFResolver</tt> to migrate towards the newly introduced
 * interface {@link GenericUDAFResolver2}. This class provides a default
 * implementation of this new API and in turn calls
 * the existing API {@link GenericUDAFResolver#getEvaluator(TypeInfo[])} by
 * ignoring the extra parameter information available via the
 * <tt>GenericUDAFParameterInfo</tt> interface.
 *
 */
public abstract class AbstractGenericUDAFResolver
    implements GenericUDAFResolver2
{

  @SuppressWarnings("deprecation")
  @Override
  public GenericUDAFEvaluator getEvaluator(GenericUDAFParameterInfo info)
    throws SemanticException {

    if (info.isAllColumns()) {
      throw new SemanticException(
          "The specified syntax for UDAF invocation is invalid.");
    }

    return getEvaluator(info.getParameters());
  }
}
