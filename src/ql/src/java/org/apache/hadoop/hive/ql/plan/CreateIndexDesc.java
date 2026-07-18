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


package org.apache.hadoop.hive.ql.plan;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * create index descriptor
 */
public class CreateIndexDesc extends DDLDesc implements Serializable {

  private static final long serialVersionUID = 1L;
  String tableName;
  String indexName;
  List<String> indexedCols;
  String indexTableName;
  boolean deferredRebuild;
  String inputFormat;
  String outputFormat;
  String serde;
  String storageHandler;
  String indexTypeHandlerClass;
  String location;
  Map<String, String> idxProps;
  Map<String, String> tblProps;
  Map<String, String> serdeProps;
  String collItemDelim;
  String fieldDelim;
  String fieldEscape;
  String lineDelim;
  String mapKeyDelim;

  String indexComment;

  public CreateIndexDesc() {
    super();
  }

  public CreateIndexDesc(String tableName, String indexName,
      List<String> indexedCols, String indexTableName, boolean deferredRebuild,
      String inputFormat, String outputFormat, String storageHandler,
      String typeName, String location, Map<String, String> idxProps, Map<String, String> tblProps,
      String serde, Map<String, String> serdeProps, String collItemDelim,
      String fieldDelim, String fieldEscape, String lineDelim,
      String mapKeyDelim, String indexComment) {
    super();
    this.tableName = tableName;
    this.indexName = indexName;
    this.indexedCols = indexedCols;
    this.indexTableName = indexTableName;
    this.deferredRebuild = deferredRebuild;
    this.inputFormat = inputFormat;
    this.outputFormat = outputFormat;
    this.serde = serde;
    this.storageHandler = storageHandler;
    this.indexTypeHandlerClass = typeName;
    this.location = location;
    this.idxProps = idxProps;
    this.tblProps = tblProps;
    this.serde = serde;
    this.serdeProps = serdeProps;
    this.collItemDelim = collItemDelim;
    this.fieldDelim = fieldDelim;
    this.fieldEscape = fieldEscape;
    this.lineDelim = lineDelim;
    this.mapKeyDelim = mapKeyDelim;
    this.indexComment = indexComment;
  }

  public String getTableName() {
    return tableName;
  }

  public void setTableName(String tableName) {
    this.tableName = tableName;
  }

  public String getIndexName() {
    return indexName;
  }

  public void setIndexName(String indexName) {
    this.indexName = indexName;
  }

  public List<String> getIndexedCols() {
    return indexedCols;
  }

  public void setIndexedCols(List<String> indexedCols) {
    this.indexedCols = indexedCols;
  }

  public String getIndexTableName() {
    return indexTableName;
  }

  public void setIndexTableName(String indexTableName) {
    this.indexTableName = indexTableName;
  }

  public boolean isDeferredRebuild() {
    return deferredRebuild;
  }

  public boolean getDeferredRebuild() {
    return deferredRebuild;
  }

  public void setDeferredRebuild(boolean deferredRebuild) {
    this.deferredRebuild = deferredRebuild;
  }

  public String getInputFormat() {
    return inputFormat;
  }

  public void setInputFormat(String inputFormat) {
    this.inputFormat = inputFormat;
  }

  public String getOutputFormat() {
    return outputFormat;
  }

  public void setOutputFormat(String outputFormat) {
    this.outputFormat = outputFormat;
  }

  public String getSerde() {
    return serde;
  }

  public void setSerde(String serde) {
    this.serde = serde;
  }

  public String getStorageHandler() {
    return storageHandler;
  }

  public void setStorageHandler(String storageHandler) {
    this.storageHandler = storageHandler;
  }

  public String getLocation() {
    return location;
  }

  public void setLocation(String location) {
    this.location = location;
  }

  public Map<String, String> getIdxProps() {
    return idxProps;
  }

  public void setIdxProps(Map<String, String> idxProps) {
    this.idxProps = idxProps;
  }

  public Map<String, String> getTblProps() {
    return tblProps;
  }

  public void setTblProps(Map<String, String> tblProps) {
    this.tblProps = tblProps;
  }

  public Map<String, String> getSerdeProps() {
    return serdeProps;
  }

  public void setSerdeProps(Map<String, String> serdeProps) {
    this.serdeProps = serdeProps;
  }

  public String getCollItemDelim() {
    return collItemDelim;
  }

  public void setCollItemDelim(String collItemDelim) {
    this.collItemDelim = collItemDelim;
  }

  public String getFieldDelim() {
    return fieldDelim;
  }

  public void setFieldDelim(String fieldDelim) {
    this.fieldDelim = fieldDelim;
  }

  public String getFieldEscape() {
    return fieldEscape;
  }

  public void setFieldEscape(String fieldEscape) {
    this.fieldEscape = fieldEscape;
  }

  public String getLineDelim() {
    return lineDelim;
  }

  public void setLineDelim(String lineDelim) {
    this.lineDelim = lineDelim;
  }

  public String getMapKeyDelim() {
    return mapKeyDelim;
  }

  public void setMapKeyDelim(String mapKeyDelim) {
    this.mapKeyDelim = mapKeyDelim;
  }

  public String getIndexTypeHandlerClass() {
    return indexTypeHandlerClass;
  }

  public void setIndexTypeHandlerClass(String indexTypeHandlerClass) {
    this.indexTypeHandlerClass = indexTypeHandlerClass;
  }

  public String getIndexComment() {
    return indexComment;
  }

  public void setIndexComment(String indexComment) {
    this.indexComment = indexComment;
  }

}
