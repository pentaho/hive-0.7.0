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


package org.apache.hadoop.hive.ql.exec;

import java.lang.reflect.Method;
import java.util.List;

import org.apache.hadoop.hive.serde2.typeinfo.TypeInfo;

/**
 * Exception thrown by the UDF and UDAF method resolvers in case a unique method
 * is not found.
 * 
 */
public class AmbiguousMethodException extends UDFArgumentException {

  private static final long serialVersionUID = 1L;

  /**
   * Constructor.
   * 
   * @param funcClass
   *          The UDF or UDAF class.
   * @param argTypeInfos
   *          The list of argument types that lead to an ambiguity.
   * @param methods
   *          All potential matches.
   */
  public AmbiguousMethodException(Class<?> funcClass,
      List<TypeInfo> argTypeInfos, List<Method> methods) {
    super("Ambiguous method for " + funcClass + " with "
        + argTypeInfos.toString().replace('[', '(').replace(']', ')'), 
        funcClass, argTypeInfos, methods);
  }
}
