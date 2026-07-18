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
import java.util.Enumeration;
import java.util.Properties;
import java.util.LinkedHashMap;
import java.util.Map;

import org.apache.hadoop.hive.ql.io.HiveFileFormatUtils;
import org.apache.hadoop.hive.ql.io.HiveOutputFormat;
import org.apache.hadoop.hive.serde2.Deserializer;
import org.apache.hadoop.mapred.InputFormat;

/**
 * TableDesc.
 *
 */
public class TableDesc implements Serializable, Cloneable {
  private static final long serialVersionUID = 1L;
  private Class<? extends Deserializer> deserializerClass;
  private Class<? extends InputFormat> inputFileFormatClass;
  private Class<? extends HiveOutputFormat> outputFileFormatClass;
  private java.util.Properties properties;
  private String serdeClassName;
  private Map<String, String> jobProperties;

  public TableDesc() {
  }

  public TableDesc(final Class<? extends Deserializer> serdeClass,
      final Class<? extends InputFormat> inputFileFormatClass,
      final Class<?> class1, final java.util.Properties properties) {
    deserializerClass = serdeClass;
    this.inputFileFormatClass = inputFileFormatClass;
    outputFileFormatClass = HiveFileFormatUtils
        .getOutputFormatSubstitute(class1);
    this.properties = properties;
    serdeClassName = properties
        .getProperty(org.apache.hadoop.hive.serde.Constants.SERIALIZATION_LIB);
    ;
  }

  public Class<? extends Deserializer> getDeserializerClass() {
    return deserializerClass;
  }

  public void setDeserializerClass(
      final Class<? extends Deserializer> serdeClass) {
    deserializerClass = serdeClass;
  }

  public Class<? extends InputFormat> getInputFileFormatClass() {
    return inputFileFormatClass;
  }

  /**
   * Return a deserializer object corresponding to the tableDesc.
   */
  public Deserializer getDeserializer() throws Exception {
    Deserializer de = deserializerClass.newInstance();
    de.initialize(null, properties);
    return de;
  }

  public void setInputFileFormatClass(
      final Class<? extends InputFormat> inputFileFormatClass) {
    this.inputFileFormatClass = inputFileFormatClass;
  }

  public Class<? extends HiveOutputFormat> getOutputFileFormatClass() {
    return outputFileFormatClass;
  }

  public void setOutputFileFormatClass(final Class<?> outputFileFormatClass) {
    this.outputFileFormatClass = HiveFileFormatUtils
        .getOutputFormatSubstitute(outputFileFormatClass);
  }

  @Explain(displayName = "properties", normalExplain = false)
  public java.util.Properties getProperties() {
    return properties;
  }

  public void setProperties(final java.util.Properties properties) {
    this.properties = properties;
  }

  public void setJobProperties(Map<String, String> jobProperties) {
    this.jobProperties = jobProperties;
  }

  @Explain(displayName = "jobProperties", normalExplain = false)
  public Map<String, String> getJobProperties() {
    return jobProperties;
  }

  /**
   * @return the serdeClassName
   */
  @Explain(displayName = "serde")
  public String getSerdeClassName() {
    return serdeClassName;
  }

  /**
   * @param serdeClassName
   *          the serde Class Name to set
   */
  public void setSerdeClassName(String serdeClassName) {
    this.serdeClassName = serdeClassName;
  }

  @Explain(displayName = "name")
  public String getTableName() {
    return properties
        .getProperty(org.apache.hadoop.hive.metastore.api.Constants.META_TABLE_NAME);
  }

  @Explain(displayName = "input format")
  public String getInputFileFormatClassName() {
    return getInputFileFormatClass().getName();
  }

  @Explain(displayName = "output format")
  public String getOutputFileFormatClassName() {
    return getOutputFileFormatClass().getName();
  }

  public boolean isNonNative() {
    return (properties.getProperty(
        org.apache.hadoop.hive.metastore.api.Constants.META_TABLE_STORAGE)
      != null);
  }
  
  @Override
  public Object clone() {
    TableDesc ret = new TableDesc();
    ret.setSerdeClassName(serdeClassName);
    ret.setDeserializerClass(deserializerClass);
    ret.setInputFileFormatClass(inputFileFormatClass);
    ret.setOutputFileFormatClass(outputFileFormatClass);
    Properties newProp = new Properties();
    Enumeration<Object> keysProp = properties.keys();
    while (keysProp.hasMoreElements()) {
      Object key = keysProp.nextElement();
      newProp.put(key, properties.get(key));
    }

    ret.setProperties(newProp);
    if (jobProperties != null) {
      ret.jobProperties = new LinkedHashMap<String, String>(jobProperties);
    }
    return ret;
  }
}
