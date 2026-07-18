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
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import org.apache.hadoop.hive.ql.parse.SemanticException;
import org.apache.hadoop.hive.serde2.objectinspector.ObjectInspectorUtils;
import org.apache.hadoop.hive.serde2.typeinfo.TypeInfo;

/**
 * exception class, thrown when udf argument have something wrong.
 */
public class UDFArgumentException extends SemanticException {

  public UDFArgumentException() {
    super();
  }

  public UDFArgumentException(String message) {
    super(message);
  }

  public UDFArgumentException(Throwable cause) {
    super(cause);
  }

  
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
  public UDFArgumentException(String message,
      Class<?> funcClass,
      List<TypeInfo> argTypeInfos,
      List<Method> methods) {
    super(getMessage(message, funcClass, argTypeInfos, methods));
    this.funcClass = funcClass;
    this.argTypeInfos = argTypeInfos;
    this.methods = methods;
  }
  
  private static String getMessage(String message, 
      Class<?> funcClass,
      List<TypeInfo> argTypeInfos,
      List<Method> methods) {
    StringBuilder sb = new StringBuilder();
    sb.append(message);
    if (methods != null) {
      sb.append(". Possible choices: ");
      for (Method m: methods) {
        Type[] types = m.getGenericParameterTypes();
        sb.append("_FUNC_(");
        List<String> typeNames = new ArrayList<String>(types.length);
        for (int t = 0; t < types.length; t++) {
          if (t > 0) {
            sb.append(", ");
          }
          sb.append(ObjectInspectorUtils.getTypeNameFromJavaClass(types[t]));
        }
        sb.append(")  ");
      }
    }
    return sb.toString();
  }
  
  /**
   * The UDF or UDAF class that has the ambiguity.
   */
  private Class<?> funcClass;

  /**
   * The list of parameter types.
   */
  private List<TypeInfo> argTypeInfos;
  
  /**
   * The list of matched methods.
   */
  private List<Method> methods;

  public Class<?> getFunctionClass() {
    return funcClass;
  }

  public List<TypeInfo> getArgTypeList() {
    return argTypeInfos;
  }
  
  public List<Method> getMethods() {
    return methods;
  }
  
}
