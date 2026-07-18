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

import org.apache.hadoop.fs.Path;

public interface BucketMatcher {

  public List<Path> getAliasBucketFiles(String currentInputFile, String refTableAlias, String alias);

  public void setAliasBucketFileNameMapping(
      LinkedHashMap<String, LinkedHashMap<String, ArrayList<String>>> aliasBucketFileNameMapping);

  public LinkedHashMap<String, Integer> getBucketFileNameMapping();

  public void setBucketFileNameMapping(LinkedHashMap<String, Integer> bucketFileNameMapping);
}
