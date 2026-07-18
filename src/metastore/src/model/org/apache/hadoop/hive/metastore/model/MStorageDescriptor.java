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


package org.apache.hadoop.hive.metastore.model;

import java.util.List;
import java.util.Map;

public class MStorageDescriptor {
  private List<MFieldSchema> cols;
  private String location;
  private String inputFormat;
  private String outputFormat;
  private boolean isCompressed = false;
  private int numBuckets = 1;
  private MSerDeInfo serDeInfo;
  private List<String> bucketCols;
  private List<MOrder> sortCols;
  private Map<String, String> parameters;
  
  public MStorageDescriptor() {}

  
  /**
   * @param cols
   * @param location
   * @param inputFormat
   * @param outputFormat
   * @param isCompressed
   * @param numBuckets
   * @param serDeInfo
   * @param bucketCols
   * @param sortOrder
   * @param parameters
   */
  public MStorageDescriptor(List<MFieldSchema> cols, String location, String inputFormat,
      String outputFormat, boolean isCompressed, int numBuckets, MSerDeInfo serDeInfo,
      List<String> bucketCols, List<MOrder> sortOrder, Map<String, String> parameters) {
    this.cols = cols;
    this.location = location;
    this.inputFormat = inputFormat;
    this.outputFormat = outputFormat;
    this.isCompressed = isCompressed;
    this.numBuckets = numBuckets;
    this.serDeInfo = serDeInfo;
    this.bucketCols = bucketCols;
    this.sortCols = sortOrder;
    this.parameters = parameters;
  }


  /**
   * @return the location
   */
  public String getLocation() {
    return location;
  }

  /**
   * @param location the location to set
   */
  public void setLocation(String location) {
    this.location = location;
  }

  /**
   * @return the isCompressed
   */
  public boolean isCompressed() {
    return isCompressed;
  }

  /**
   * @param isCompressed the isCompressed to set
   */
  public void setCompressed(boolean isCompressed) {
    this.isCompressed = isCompressed;
  }

  /**
   * @return the numBuckets
   */
  public int getNumBuckets() {
    return numBuckets;
  }

  /**
   * @param numBuckets the numBuckets to set
   */
  public void setNumBuckets(int numBuckets) {
    this.numBuckets = numBuckets;
  }

  /**
   * @return the bucketCols
   */
  public List<String> getBucketCols() {
    return bucketCols;
  }

  /**
   * @param bucketCols the bucketCols to set
   */
  public void setBucketCols(List<String> bucketCols) {
    this.bucketCols = bucketCols;
  }

  /**
   * @return the parameters
   */
  public Map<String, String> getParameters() {
    return parameters;
  }

  /**
   * @param parameters the parameters to set
   */
  public void setParameters(Map<String, String> parameters) {
    this.parameters = parameters;
  }

  /**
   * @return the inputFormat
   */
  public String getInputFormat() {
    return inputFormat;
  }

  /**
   * @param inputFormat the inputFormat to set
   */
  public void setInputFormat(String inputFormat) {
    this.inputFormat = inputFormat;
  }

  /**
   * @return the outputFormat
   */
  public String getOutputFormat() {
    return outputFormat;
  }

  /**
   * @param outputFormat the outputFormat to set
   */
  public void setOutputFormat(String outputFormat) {
    this.outputFormat = outputFormat;
  }

  /**
   * @return the cols
   */
  public List<MFieldSchema> getCols() {
    return cols;
  }

  /**
   * @param cols the cols to set
   */
  public void setCols(List<MFieldSchema> cols) {
    this.cols = cols;
  }

  /**
   * @return the serDe
   */
  public MSerDeInfo getSerDeInfo() {
    return serDeInfo;
  }

  /**
   * @param serDe the serDe to set
   */
  public void setSerDeInfo(MSerDeInfo serDe) {
    this.serDeInfo = serDe;
  }


  /**
   * @param sortOrder the sortOrder to set
   */
  public void setSortCols(List<MOrder> sortOrder) {
    this.sortCols = sortOrder;
  }


  /**
   * @return the sortOrder
   */
  public List<MOrder> getSortCols() {
    return sortCols;
  }
}
