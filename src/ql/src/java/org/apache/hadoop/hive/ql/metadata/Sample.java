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

/**
 * A sample defines a subset of data based on sampling on a given dimension.
 * 
 **/
public class Sample {

  protected int sampleNum;
  protected int sampleFraction;
  protected Dimension sampleDimension;
  protected int moduloNum;

  @SuppressWarnings("nls")
  public Sample(int num, int fraction, Dimension d) throws HiveException {
    if ((num <= 0) || (num > fraction)) {
      throw new HiveException("Bad sample spec: " + num + "/" + fraction);
    }
    sampleNum = num;
    moduloNum = sampleNum - 1;
    sampleFraction = fraction;
    sampleDimension = d;
  }

  /**
   * Given an arbitrary object, determine if it falls within this sample.
   */
  public boolean inSample(Object o) {
    return (((sampleDimension.hashCode(o) & Integer.MAX_VALUE) % sampleFraction) == moduloNum);
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null) {
      return false;
    }
    if (o instanceof Sample) {
      Sample s = (Sample) o;
      return ((sampleNum == s.sampleNum)
          && (sampleFraction == s.sampleFraction) && sampleDimension
          .equals(s.sampleDimension));
    }
    return (false);
  }

  public int getSampleNum() {
    return sampleNum;
  }

  public int getSampleFraction() {
    return sampleFraction;
  }

  public Dimension getSampleDimension() {
    return sampleDimension;
  }

  @SuppressWarnings("nls")
  @Override
  public String toString() {
    return sampleNum + "/" + sampleFraction + "@(" + sampleDimension + ")";
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result
        + ((sampleDimension == null) ? 0 : sampleDimension.hashCode());
    result = prime * result + sampleFraction;
    result = prime * result + sampleNum;
    return result;
  }
}
