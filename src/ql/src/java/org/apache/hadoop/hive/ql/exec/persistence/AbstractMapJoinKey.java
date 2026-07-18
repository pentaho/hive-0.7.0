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


package org.apache.hadoop.hive.ql.exec.persistence;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

/**
 * Map Join Object used for both key.
 */
public abstract class AbstractMapJoinKey implements Externalizable {

  protected static int metadataTag = -1;

  public AbstractMapJoinKey() {
  }

  @Override
  public abstract boolean equals(Object o);

  @Override
  public abstract int hashCode();

  public abstract void readExternal(ObjectInput in) throws IOException, ClassNotFoundException;

  public abstract void writeExternal(ObjectOutput out) throws IOException;

  public abstract boolean hasAnyNulls();

}
