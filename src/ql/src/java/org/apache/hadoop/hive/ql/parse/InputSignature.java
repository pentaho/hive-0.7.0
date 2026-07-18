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


package org.apache.hadoop.hive.ql.parse;

import java.util.ArrayList;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.hadoop.hive.serde2.typeinfo.TypeInfo;
import org.apache.hadoop.hive.serde2.typeinfo.TypeInfoFactory;

/**
 * The input signature of a function or operator. The signature basically
 * consists of name, list of parameter types.
 * 
 **/

public class InputSignature {
  private final String name;
  private final ArrayList<TypeInfo> typeArray;

  @SuppressWarnings("unused")
  private static final Log LOG = LogFactory.getLog(InputSignature.class
      .getName());

  public InputSignature(String name) {
    this.name = name;
    typeArray = new ArrayList<TypeInfo>();
  }

  public InputSignature(String name, TypeInfo... classList) {
    this(name);

    if (classList.length != 0) {
      for (TypeInfo cl : classList) {
        typeArray.add(cl);
      }
    }
  }

  public InputSignature(String name, Class<?>... classList) {
    this(name);

    if (classList.length != 0) {
      for (Class<?> cl : classList) {
        typeArray.add(TypeInfoFactory
            .getPrimitiveTypeInfoFromPrimitiveWritable(cl));
      }
    }
  }

  public void add(TypeInfo paramType) {
    typeArray.add(paramType);
  }

  public String getName() {
    return name.toUpperCase();
  }

  public ArrayList<TypeInfo> getTypeArray() {
    return typeArray;
  }

  @Override
  public boolean equals(Object obj) {
    if (obj == null) {
      return false;
    }

    InputSignature other = null;
    try {
      other = (InputSignature) obj;
    } catch (ClassCastException cce) {
      return false;
    }

    return name.equalsIgnoreCase(other.getName())
        && (other.typeArray.equals(typeArray));
  }

  @Override
  public int hashCode() {
    return toString().hashCode();
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append(getName());
    sb.append("(");
    boolean isfirst = true;
    for (TypeInfo cls : getTypeArray()) {
      if (!isfirst) {
        sb.append(",");
      }
      sb.append(cls.toString());
      isfirst = false;
    }

    sb.append(")");
    return sb.toString();
  }
}
