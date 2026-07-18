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

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import org.apache.hadoop.fs.Path;

public class DefaultBucketMatcher implements BucketMatcher {

  protected Log LOG = LogFactory.getLog(this.getClass().getName());

  //MAPPING: bucket_file_name_in_big_table->{alias_table->corresonding_bucket_file_names}
  private LinkedHashMap<String, LinkedHashMap<String, ArrayList<String>>> aliasBucketMapping;

  private LinkedHashMap<String, Integer> bucketFileNameMapping;

  public DefaultBucketMatcher(){
    bucketFileNameMapping = new LinkedHashMap<String, Integer>();
  }

  public List<Path> getAliasBucketFiles(String refTableInputFile, String refTableAlias, String alias) {
    List<String> pathStr=aliasBucketMapping.get(alias).get(refTableInputFile);
    List<Path> paths = new ArrayList<Path>();
    if(pathStr!=null) {
      for (String p : pathStr) {
        LOG.info("Loading file " + p + " for " + alias + ". (" + refTableInputFile + ")");
        paths.add(new Path(p));
      }
    }
    return paths;
  }

  public void setAliasBucketFileNameMapping(
      LinkedHashMap<String, LinkedHashMap<String, ArrayList<String>>> aliasBucketFileNameMapping) {
    this.aliasBucketMapping = aliasBucketFileNameMapping;
  }

  public LinkedHashMap<String, Integer> getBucketFileNameMapping() {
    return bucketFileNameMapping;
  }

  public void setBucketFileNameMapping(LinkedHashMap<String, Integer> bucketFileNameMapping) {
    this.bucketFileNameMapping = bucketFileNameMapping;
  }

}
