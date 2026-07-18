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


package org.apache.hadoop.hive.ql.metadata;

import java.util.Map;

import junit.framework.TestCase;

import org.apache.hadoop.hive.conf.HiveConf;
import org.apache.hadoop.hive.conf.HiveConf.ConfVars;
import org.apache.hadoop.hive.metastore.MetaStoreUtils;
import org.apache.hadoop.hive.ql.Driver;
import org.apache.hadoop.hive.ql.processors.CommandProcessorResponse;
import org.apache.hadoop.hive.ql.session.SessionState;

public class TestSemanticAnalyzerHookLoading extends TestCase {

  public void testHookLoading() throws Exception{

    HiveConf conf = new HiveConf(this.getClass());
    conf.set(ConfVars.SEMANTIC_ANALYZER_HOOK.varname, DummySemanticAnalyzerHook.class.getName());
    conf.set(ConfVars.HIVE_SUPPORT_CONCURRENCY.varname, "false");
    SessionState.start(conf);
    Driver driver = new Driver(conf);

    driver.run("drop table testDL");
    CommandProcessorResponse resp = driver.run("create table testDL (a int) as select * from tbl2");
    assertEquals(10, resp.getResponseCode());
    assertTrue(resp.getErrorMessage().contains("CTAS not supported."));

    resp = driver.run("create table testDL (a int)");
    assertEquals(0, resp.getResponseCode());
    assertNull(resp.getErrorMessage());

    Map<String,String> params = Hive.get(conf).getTable(MetaStoreUtils.DEFAULT_DATABASE_NAME, "testDL").getParameters();

    assertEquals(DummyCreateTableHook.class.getName(),params.get("createdBy"));
    assertEquals("Open Source rocks!!", params.get("Message"));

    driver.run("drop table testDL");
  }
}
