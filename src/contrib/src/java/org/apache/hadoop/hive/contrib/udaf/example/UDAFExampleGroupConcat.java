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


package org.apache.hadoop.hive.contrib.udaf.example;

import java.util.ArrayList;
import java.util.Collections;

import org.apache.hadoop.hive.ql.exec.UDAF;
import org.apache.hadoop.hive.ql.exec.UDAFEvaluator;

/**
 * This is a simple UDAF that concatenates all arguments from different rows
 * into a single string.
 * 
 * It should be very easy to follow and can be used as an example for writing
 * new UDAFs.
 * 
 * Note that Hive internally uses a different mechanism (called GenericUDAF) to
 * implement built-in aggregation functions, which are harder to program but
 * more efficient.
 */
public class UDAFExampleGroupConcat extends UDAF {

  /**
   * The actual class for doing the aggregation. Hive will automatically look
   * for all internal classes of the UDAF that implements UDAFEvaluator.
   */
  public static class UDAFExampleGroupConcatEvaluator implements UDAFEvaluator {

    ArrayList<String> data;

    public UDAFExampleGroupConcatEvaluator() {
      super();
      data = new ArrayList<String>();
    }

    /**
     * Reset the state of the aggregation.
     */
    public void init() {
      data.clear();
    }

    /**
     * Iterate through one row of original data.
     * 
     * This UDF accepts arbitrary number of String arguments, so we use
     * String[]. If it only accepts a single String, then we should use a single
     * String argument.
     * 
     * This function should always return true.
     */
    public boolean iterate(String[] o) {
      if (o != null) {
        StringBuilder sb = new StringBuilder();
        for (String element : o) {
          sb.append(element);
        }
        data.add(sb.toString());
      }
      return true;
    }

    /**
     * Terminate a partial aggregation and return the state.
     */
    public ArrayList<String> terminatePartial() {
      return data;
    }

    /**
     * Merge with a partial aggregation.
     * 
     * This function should always have a single argument which has the same
     * type as the return value of terminatePartial().
     * 
     * This function should always return true.
     */
    public boolean merge(ArrayList<String> o) {
      if (o != null) {
        data.addAll(o);
      }
      return true;
    }

    /**
     * Terminates the aggregation and return the final result.
     */
    public String terminate() {
      Collections.sort(data);
      StringBuilder sb = new StringBuilder();
      for (int i = 0; i < data.size(); i++) {
        sb.append(data.get(i));
      }
      return sb.toString();
    }
  }

}
