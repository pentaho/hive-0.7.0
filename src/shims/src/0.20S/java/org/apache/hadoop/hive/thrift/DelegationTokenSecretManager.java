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

import java.io.IOException;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.security.UserGroupInformation;
import org.apache.hadoop.security.token.Token;
import org.apache.hadoop.security.token.delegation.AbstractDelegationTokenSecretManager;

/**
 * A Hive specific delegation token secret manager.
 * The secret manager is responsible for generating and accepting the password
 * for each token.
 */
public class DelegationTokenSecretManager
    extends AbstractDelegationTokenSecretManager<DelegationTokenIdentifier> {

  /**
   * Create a secret manager
   * @param delegationKeyUpdateInterval the number of seconds for rolling new
   *        secret keys.
   * @param delegationTokenMaxLifetime the maximum lifetime of the delegation
   *        tokens
   * @param delegationTokenRenewInterval how often the tokens must be renewed
   * @param delegationTokenRemoverScanInterval how often the tokens are scanned
   *        for expired tokens
   */
  public DelegationTokenSecretManager(long delegationKeyUpdateInterval,
                                      long delegationTokenMaxLifetime,
                                      long delegationTokenRenewInterval,
                                      long delegationTokenRemoverScanInterval) {
    super(delegationKeyUpdateInterval, delegationTokenMaxLifetime,
          delegationTokenRenewInterval, delegationTokenRemoverScanInterval);
  }

  @Override
  public DelegationTokenIdentifier createIdentifier() {
    return new DelegationTokenIdentifier();
  }

  public synchronized void cancelDelegationToken(String tokenStrForm) throws IOException {
    Token<DelegationTokenIdentifier> t= new Token<DelegationTokenIdentifier>();
    t.decodeFromUrlString(tokenStrForm);
    String user = UserGroupInformation.getCurrentUser().getUserName();
    cancelToken(t, user);
  }

  public synchronized long renewDelegationToken(String tokenStrForm) throws IOException {
    Token<DelegationTokenIdentifier> t= new Token<DelegationTokenIdentifier>();
    t.decodeFromUrlString(tokenStrForm);
    String user = UserGroupInformation.getCurrentUser().getUserName();
    return renewToken(t, user);
  }

  public synchronized String getDelegationToken(String renewer, String tokenSignature) throws IOException {
    UserGroupInformation ugi = UserGroupInformation.getCurrentUser();
    Text owner = new Text(ugi.getUserName());
    Text realUser = null;
    if (ugi.getRealUser() != null) {
      realUser = new Text(ugi.getRealUser().getUserName());
    }
    DelegationTokenIdentifier ident =
      new DelegationTokenIdentifier(owner, new Text(renewer), realUser);
    Token<DelegationTokenIdentifier> t = new Token<DelegationTokenIdentifier>(
        ident, this);
    if(tokenSignature != null) {
      t.setService(new Text(tokenSignature));
    }
    return t.encodeToUrlString();
  }

  public synchronized String getDelegationToken(String renewer) throws IOException {
    return getDelegationToken(renewer, null);
  }
}

