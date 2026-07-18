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


package org.apache.hadoop.hive.ql.udf;

import org.apache.hadoop.hive.ql.exec.UDF;

/**
 * UDFToDate.
 *
 */
public class UDFToDate extends UDF {

  public UDFToDate() {
  }

  public java.sql.Date evaluate(String i) {
    if (i == null) {
      return null;
    } else {
      try {
        // Supported format: "YYYY-MM-DD"
        return java.sql.Date.valueOf(i);
      } catch (IllegalArgumentException e) {
        // We return NULL when the string is in a wrong format, which is
        // conservative.
        return null;
      }
    }
  }

  public java.sql.Date evaluate(Void i) {
    return null;
  }

  public java.sql.Date evaluate(Byte i) {
    if (i == null) {
      return null;
    } else {
      return new java.sql.Date(i.longValue());
    }
  }

  public java.sql.Date evaluate(Short i) {
    if (i == null) {
      return null;
    } else {
      return new java.sql.Date(i.longValue());
    }
  }

  public java.sql.Date evaluate(Integer i) {
    if (i == null) {
      return null;
    } else {
      return new java.sql.Date(i.longValue());
    }
  }

  public java.sql.Date evaluate(Long i) {
    if (i == null) {
      return null;
    } else {
      return new java.sql.Date(i.longValue());
    }
  }

  public java.sql.Date evaluate(Float i) {
    if (i == null) {
      return null;
    } else {
      return new java.sql.Date(i.longValue());
    }
  }

  public java.sql.Date evaluate(Double i) {
    if (i == null) {
      return null;
    } else {
      return new java.sql.Date(i.longValue());
    }
  }

}
