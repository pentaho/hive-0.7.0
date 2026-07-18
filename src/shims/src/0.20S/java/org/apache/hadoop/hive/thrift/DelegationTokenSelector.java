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


package org.apache.hadoop.hive.thrift;

import org.apache.hadoop.security.token.delegation.AbstractDelegationTokenSelector;

/**
 * A delegation token that is specialized for Hive
 */

public class DelegationTokenSelector
    extends AbstractDelegationTokenSelector<DelegationTokenIdentifier>{

  public DelegationTokenSelector() {
    super(DelegationTokenIdentifier.HIVE_DELEGATION_KIND);
  }
}
