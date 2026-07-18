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

import java.io.Serializable;

/**
 * Terminal Operator Base Class.
 **/
public abstract class TerminalOperator<T extends Serializable> extends
    Operator<T> implements Serializable {
  private static final long serialVersionUID = 1L;

}
