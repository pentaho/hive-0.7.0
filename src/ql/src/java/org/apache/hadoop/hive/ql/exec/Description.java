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

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Description.
 *
 */
@Retention(RetentionPolicy.RUNTIME)
public @interface Description {
  String value() default "_FUNC_ is undocumented";

  String extended() default "";

  String name() default "";
}
