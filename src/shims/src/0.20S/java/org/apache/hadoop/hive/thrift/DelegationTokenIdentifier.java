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

import org.apache.hadoop.io.Text;
import org.apache.hadoop.security.token.delegation.AbstractDelegationTokenIdentifier;

/**
 * A delegation token identifier that is specific to Hive.
 */
public class DelegationTokenIdentifier
    extends AbstractDelegationTokenIdentifier {
  public static final Text HIVE_DELEGATION_KIND = new Text("HIVE_DELEGATION_TOKEN");

  /**
   * Create an empty delegation token identifier for reading into.
   */
  public DelegationTokenIdentifier() {
  }

  /**
   * Create a new delegation token identifier
   * @param owner the effective username of the token owner
   * @param renewer the username of the renewer
   * @param realUser the real username of the token owner
   */
  public DelegationTokenIdentifier(Text owner, Text renewer, Text realUser) {
    super(owner, renewer, realUser);
  }

  @Override
  public Text getKind() {
    return HIVE_DELEGATION_KIND;
  }

}
