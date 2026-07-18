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


package org.apache.hadoop.hive.ql.io;


/**
 * IOContext basically contains the position information of the current
 * key/value. For blockCompressed files, isBlockPointer should return true,
 * and currentBlockStart refers to the RCFile Block or SequenceFile Block. For
 * non compressed files, isBlockPointer should return false, and
 * currentBlockStart refers to the beginning offset of the current row,
 * nextBlockStart refers the end of current row and beginning of next row.
 */
public class IOContext {


  private static ThreadLocal<IOContext> threadLocal = new ThreadLocal<IOContext>(){
    @Override
    protected synchronized IOContext initialValue() { return new IOContext(); }
 };

  public static IOContext get() {
    return IOContext.threadLocal.get();
  }

  long currentBlockStart;
  long nextBlockStart;
  boolean isBlockPointer;
  boolean ioExceptions;

  String inputFile;

  public IOContext() {
    this.currentBlockStart = 0;
    this.nextBlockStart = -1;
    this.isBlockPointer = true;
    this.ioExceptions = false;
  }

  public long getCurrentBlockStart() {
    return currentBlockStart;
  }

  public void setCurrentBlockStart(long currentBlockStart) {
    this.currentBlockStart = currentBlockStart;
  }

  public long getNextBlockStart() {
    return nextBlockStart;
  }

  public void setNextBlockStart(long nextBlockStart) {
    this.nextBlockStart = nextBlockStart;
  }

  public boolean isBlockPointer() {
    return isBlockPointer;
  }

  public void setBlockPointer(boolean isBlockPointer) {
    this.isBlockPointer = isBlockPointer;
  }

  public String getInputFile() {
    return inputFile;
  }

  public void setInputFile(String inputFile) {
    this.inputFile = inputFile;
  }

  public void setIOExceptions(boolean ioe) {
    this.ioExceptions = ioe;
  }

  public boolean getIOExceptions() {
    return ioExceptions;
  }
}
