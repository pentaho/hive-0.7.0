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

package org.apache.hadoop.hive.contrib.metastore.hooks;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hive.conf.HiveConf;
import org.apache.hadoop.hive.metastore.hooks.JDOConnectionURLHook;

/**
 * First returns a url for a blank DB, then returns a URL for the original DB.
 * For testing the feature in url_hook.q
 */
public class TestURLHook implements JDOConnectionURLHook {

  static String originalUrl = null;
  @Override
  public String getJdoConnectionUrl(Configuration conf) throws Exception {
    if (originalUrl == null) {
      originalUrl = conf.get(HiveConf.ConfVars.METASTORECONNECTURLKEY.varname, "");
      return "jdbc:derby:;databaseName=../build/test/junit_metastore_db_blank;create=true";
    } else {
      return originalUrl;
    }

  }

  @Override
  public void notifyBadConnectionUrl(String url) {

  }

}
