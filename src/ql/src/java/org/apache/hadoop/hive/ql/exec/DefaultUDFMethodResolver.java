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
 * The default UDF Method resolver. This resolver is used for resolving the UDF
 * method that is to be used for evaluation given the list of the argument
 * types. The getEvalMethod goes through all the evaluate methods and returns
 * the one that matches the argument signature or is the closest match. Closest
 * match is defined as the one that requires the least number of arguments to be
 * converted. In case more than one matches are found, the method throws an
 * ambiguous method exception.
 */
public class DefaultUDFMethodResolver implements UDFMethodResolver {

  /**
   * The class of the UDF.
   */
  private final Class<? extends UDF> udfClass;

  /**
   * Constructor. This constructor sets the resolver to be used for comparison
   * operators. See {@link UDFMethodResolver}
   */
  public DefaultUDFMethodResolver(Class<? extends UDF> udfClass) {
    this.udfClass = udfClass;
  }

  /**
   * Gets the evaluate method for the UDF given the parameter types.
   * 
   * @param argClasses
   *          The list of the argument types that need to matched with the
   *          evaluate function signature.
   */
  @Override
  public Method getEvalMethod(List<TypeInfo> argClasses) throws UDFArgumentException {
    return FunctionRegistry.getMethodInternal(udfClass, "evaluate", false,
        argClasses);
  }
}
