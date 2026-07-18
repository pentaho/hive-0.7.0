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
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.hadoop.hive.serde2.typeinfo.TypeInfo;
import org.apache.hadoop.hive.serde2.typeinfo.TypeInfoFactory;
import org.apache.hadoop.hive.serde2.typeinfo.TypeInfoUtils;

/**
 * The class implements the method resolution for operators like (> < <= >= =
 * <>). The resolution logic is as follows: 1. If one of the parameters is null,
 * then it resolves to evaluate(Double, Double) 2. If both of the parameters are
 * of type T, then it resolves to evaluate(T, T) 3. If 1 and 2 fails then it
 * resolves to evaluate(Double, Double).
 */
public class ComparisonOpMethodResolver implements UDFMethodResolver {

  /**
   * The udfclass for which resolution is needed.
   */
  private final Class<? extends UDF> udfClass;

  /**
   * Constuctor.
   */
  public ComparisonOpMethodResolver(Class<? extends UDF> udfClass) {
    this.udfClass = udfClass;
  }

  /*
   * (non-Javadoc)
   *
   * @see
   * org.apache.hadoop.hive.ql.exec.UDFMethodResolver#getEvalMethod(java.util
   * .List)
   */
  @Override
  public Method getEvalMethod(List<TypeInfo> argTypeInfos) throws UDFArgumentException {
    assert (argTypeInfos.size() == 2);

    List<TypeInfo> pTypeInfos = null;
    if (argTypeInfos.get(0).equals(TypeInfoFactory.voidTypeInfo)
        || argTypeInfos.get(1).equals(TypeInfoFactory.voidTypeInfo)) {
      pTypeInfos = new ArrayList<TypeInfo>();
      pTypeInfos.add(TypeInfoFactory.doubleTypeInfo);
      pTypeInfos.add(TypeInfoFactory.doubleTypeInfo);
    } else if (argTypeInfos.get(0).equals(TypeInfoFactory.booleanTypeInfo) &&
               argTypeInfos.get(1).equals(TypeInfoFactory.booleanTypeInfo)) {
      pTypeInfos = new ArrayList<TypeInfo>();
      pTypeInfos.add(TypeInfoFactory.intTypeInfo);
      pTypeInfos.add(TypeInfoFactory.intTypeInfo);
    } else if (argTypeInfos.get(0) == argTypeInfos.get(1)) {
      pTypeInfos = argTypeInfos;
    } else {
      pTypeInfos = new ArrayList<TypeInfo>();
      pTypeInfos.add(TypeInfoFactory.doubleTypeInfo);
      pTypeInfos.add(TypeInfoFactory.doubleTypeInfo);
    }

    Method udfMethod = null;

    List<Method> evaluateMethods = new ArrayList<Method>();

    for (Method m : Arrays.asList(udfClass.getMethods())) {
      if (m.getName().equals("evaluate")) {

        evaluateMethods.add(m);
        List<TypeInfo> acceptedTypeInfos = TypeInfoUtils.getParameterTypeInfos(
            m, pTypeInfos.size());
        if (acceptedTypeInfos == null) {
          // null means the method does not accept number of arguments passed.
          continue;
        }

        boolean match = (acceptedTypeInfos.size() == pTypeInfos.size());

        for (int i = 0; i < pTypeInfos.size() && match; i++) {
          TypeInfo accepted = acceptedTypeInfos.get(i);
          if (accepted != pTypeInfos.get(i)) {
            match = false;
          }
        }

        if (match) {
          if (udfMethod != null) {
            throw new AmbiguousMethodException(udfClass, argTypeInfos,
                Arrays.asList(new Method[]{udfMethod, m}));
          } else {
            udfMethod = m;
          }
        }
      }
    }

    if (udfMethod == null) {
      throw new NoMatchingMethodException(udfClass, argTypeInfos, evaluateMethods);
    }

    return udfMethod;
  }

}
