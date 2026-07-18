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

import java.util.Random;

/**
 * A random dimension is an abstract dimension. It is implicitly associated with
 * every row in data and has a random value
 * 
 **/
public class RandomDimension extends Dimension {

  Random r;

  public RandomDimension(Class t, String id) {
    super(t, id);
    r = new Random();
  }

  @Override
  public int hashCode(Object o) {
    return r.nextInt();
  }
}
