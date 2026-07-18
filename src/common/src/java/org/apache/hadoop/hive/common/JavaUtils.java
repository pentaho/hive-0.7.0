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


package org.apache.hadoop.hive.common;

/**
 * Collection of Java class loading/reflection related utilities common across
 * Hive.
 */
public final class JavaUtils {

  /**
   * Standard way of getting classloader in Hive code (outside of Hadoop).
   * 
   * Uses the context loader to get access to classpaths to auxiliary and jars
   * added with 'add jar' command. Falls back to current classloader.
   * 
   * In Hadoop-related code, we use Configuration.getClassLoader().
   */
  public static ClassLoader getClassLoader() {
    ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
    if (classLoader == null) {
      classLoader = JavaUtils.class.getClassLoader();
    }
    return classLoader;
  }

  private JavaUtils() {
    // prevent instantiation
  }
}
