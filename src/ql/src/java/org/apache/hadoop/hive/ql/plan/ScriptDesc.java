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

import org.apache.hadoop.hive.ql.exec.RecordReader;
import org.apache.hadoop.hive.ql.exec.RecordWriter;

/**
 * ScriptDesc.
 *
 */
@Explain(displayName = "Transform Operator")
public class ScriptDesc implements Serializable {
  private static final long serialVersionUID = 1L;
  private String scriptCmd;
  // Describe how to deserialize data back from user script
  private TableDesc scriptOutputInfo;
  private Class<? extends RecordWriter> inRecordWriterClass;

  // Describe how to serialize data out to user script
  private TableDesc scriptInputInfo;
  private Class<? extends RecordReader> outRecordReaderClass;

  private TableDesc scriptErrInfo;
  private Class<? extends RecordReader> errRecordReaderClass;

  public ScriptDesc() {
  }

  public ScriptDesc(final String scriptCmd, final TableDesc scriptInputInfo,
      final Class<? extends RecordWriter> inRecordWriterClass,
      final TableDesc scriptOutputInfo,
      final Class<? extends RecordReader> outRecordReaderClass,
      final Class<? extends RecordReader> errRecordReaderClass,
      final TableDesc scriptErrInfo) {

    this.scriptCmd = scriptCmd;
    this.scriptInputInfo = scriptInputInfo;
    this.inRecordWriterClass = inRecordWriterClass;
    this.scriptOutputInfo = scriptOutputInfo;
    this.outRecordReaderClass = outRecordReaderClass;
    this.errRecordReaderClass = errRecordReaderClass;
    this.scriptErrInfo = scriptErrInfo;
  }

  @Explain(displayName = "command")
  public String getScriptCmd() {
    return scriptCmd;
  }

  public void setScriptCmd(final String scriptCmd) {
    this.scriptCmd = scriptCmd;
  }

  @Explain(displayName = "output info")
  public TableDesc getScriptOutputInfo() {
    return scriptOutputInfo;
  }

  public void setScriptOutputInfo(final TableDesc scriptOutputInfo) {
    this.scriptOutputInfo = scriptOutputInfo;
  }

  public TableDesc getScriptErrInfo() {
    return scriptErrInfo;
  }

  public void setScriptErrInfo(final TableDesc scriptErrInfo) {
    this.scriptErrInfo = scriptErrInfo;
  }

  public TableDesc getScriptInputInfo() {
    return scriptInputInfo;
  }

  public void setScriptInputInfo(TableDesc scriptInputInfo) {
    this.scriptInputInfo = scriptInputInfo;
  }

  /**
   * @return the outRecordReaderClass
   */
  public Class<? extends RecordReader> getOutRecordReaderClass() {
    return outRecordReaderClass;
  }

  /**
   * @param outRecordReaderClass
   *          the outRecordReaderClass to set
   */
  public void setOutRecordReaderClass(
      Class<? extends RecordReader> outRecordReaderClass) {
    this.outRecordReaderClass = outRecordReaderClass;
  }

  /**
   * @return the errRecordReaderClass
   */
  public Class<? extends RecordReader> getErrRecordReaderClass() {
    return errRecordReaderClass;
  }

  /**
   * @param errRecordReaderClass
   *          the errRecordReaderClass to set
   */
  public void setErrRecordReaderClass(
      Class<? extends RecordReader> errRecordReaderClass) {
    this.errRecordReaderClass = errRecordReaderClass;
  }

  /**
   * @return the inRecordWriterClass
   */
  public Class<? extends RecordWriter> getInRecordWriterClass() {
    return inRecordWriterClass;
  }

  /**
   * @param inRecordWriterClass
   *          the inRecordWriterClass to set
   */
  public void setInRecordWriterClass(
      Class<? extends RecordWriter> inRecordWriterClass) {
    this.inRecordWriterClass = inRecordWriterClass;
  }

}
