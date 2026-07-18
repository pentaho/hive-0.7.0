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

import org.apache.hadoop.hive.ql.metadata.HiveException;
import org.apache.hadoop.hive.serde2.objectinspector.ObjectInspector;

public abstract class KeyWrapper {
  abstract void getNewKey(Object row, ObjectInspector rowInspector) throws HiveException;
  abstract void setHashKey();
  abstract KeyWrapper copyKey();
  abstract void copyKey(KeyWrapper oldWrapper);
  abstract Object[] getKeyArray();
}
