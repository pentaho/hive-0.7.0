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

import org.apache.hadoop.conf.Configuration;
import org.apache.thrift.TProcessor;
import org.apache.thrift.transport.TTransport;
import org.apache.thrift.transport.TTransportException;
import org.apache.thrift.transport.TTransportFactory;
 /**
  * This class is only overridden by the secure hadoop shim. It allows
  * the Thrift SASL support to bridge to Hadoop's UserGroupInformation
  * & DelegationToken infrastructure.
  */
 public class HadoopThriftAuthBridge {
   public Client createClient() {
     throw new UnsupportedOperationException(
       "The current version of Hadoop does not support Authentication");
   }

   public Server createServer(String keytabFile, String principalConf)
     throws TTransportException {
     throw new UnsupportedOperationException(
       "The current version of Hadoop does not support Authentication");
   }


   public static abstract class Client {
   /**
    *
    * @param principalConfig In the case of Kerberos authentication this will
    * be the kerberos principal name, for DIGEST-MD5 (delegation token) based
    * authentication this will be null
    * @param host The metastore server host name
    * @param methodStr "KERBEROS" or "DIGEST"
    * @param tokenStrForm This is url encoded string form of
    * org.apache.hadoop.security.token.
    * @param underlyingTransport the underlying transport
    * @return the transport
    * @throws IOException
    */
     public abstract TTransport createClientTransport(
       String principalConfig, String host,
       String methodStr,String tokenStrForm, TTransport underlyingTransport)
       throws IOException;
   }

   public static abstract class Server {
     public abstract TTransportFactory createTransportFactory() throws TTransportException;
     public abstract TProcessor wrapProcessor(TProcessor processor);
     public abstract void startDelegationTokenSecretManager(Configuration conf) throws IOException;
     public abstract String getDelegationToken(String renewer) throws IOException;
     public abstract long renewDelegationToken(String tokenStrForm) throws IOException;
     public abstract String getDelegationToken(String renewer, String token_signature) throws IOException;
     public abstract void cancelDelegationToken(String tokenStrForm) throws IOException;
   }
 }

