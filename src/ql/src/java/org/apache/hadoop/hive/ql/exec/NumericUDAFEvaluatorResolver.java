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

import java.util.ArrayList;
import java.util.List;

import org.apache.hadoop.hive.serde2.typeinfo.TypeInfo;
import org.apache.hadoop.hive.serde2.typeinfo.TypeInfoFactory;

/**
 * Resolver for Numeric UDAFs like sum and avg. If the input argument is string
 * or date, the resolver returns the evaluator whose iterate function operates
 * on doubles.
 */
public class NumericUDAFEvaluatorResolver extends DefaultUDAFEvaluatorResolver {

  /**
   * Constructor.
   */
  public NumericUDAFEvaluatorResolver(Class<? extends UDAF> udafClass) {
    super(udafClass);
  }

  /*
   * (non-Javadoc)
   * 
   * @see
   * org.apache.hadoop.hive.ql.exec.UDAFMethodResolver#getEvaluatorClass(java
   * .util.List)
   */
  @Override
  public Class<? extends UDAFEvaluator> getEvaluatorClass(
      List<TypeInfo> argTypeInfos) throws UDFArgumentException {
    // Go through the argClasses and for any string, void or date time, start
    // looking for doubles
    ArrayList<TypeInfo> args = new ArrayList<TypeInfo>();
    for (TypeInfo arg : argTypeInfos) {
      if (arg.equals(TypeInfoFactory.voidTypeInfo)
          || arg.equals(TypeInfoFactory.stringTypeInfo)) {
        args.add(TypeInfoFactory.doubleTypeInfo);
      } else {
        args.add(arg);
      }
    }

    return super.getEvaluatorClass(args);
  }
}
