/*!
* Copyright 2010 - 2013 Pentaho Corporation.  All rights reserved.
*
* Licensed under the Apache License, Version 2.0 (the "License");
* you may not use this file except in compliance with the License.
* You may obtain a copy of the License at
*
* http://www.apache.org/licenses/LICENSE-2.0
*
* Unless required by applicable law or agreed to in writing, software
* distributed under the License is distributed on an "AS IS" BASIS,
* WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
* See the License for the specific language governing permissions and
* limitations under the License.
*
*/

package org.apache.hadoop.hive.serde2.thrift;

import org.apache.thrift.protocol.TProtocolFactory;

/**
 * TReflectionUtils.
 *
 */
public final class TReflectionUtils {
  public static final String thriftReaderFname = "read";
  public static final String thriftWriterFname = "write";

  public static final Class<?>[] thriftRWParams;
  static {
    try {
      thriftRWParams = new Class[] {Class
          .forName("org.apache.thrift.protocol.TProtocol")};
    } catch (ClassNotFoundException e) {
      throw new RuntimeException(e);
    }
  }

  public static TProtocolFactory getProtocolFactoryByName(String protocolName)
      throws Exception {
    Class<?> protoClass = Class.forName(protocolName + "$Factory");
    return ((TProtocolFactory) protoClass.newInstance());
  }

  private TReflectionUtils() {
    // prevent instantiation
  }
}
