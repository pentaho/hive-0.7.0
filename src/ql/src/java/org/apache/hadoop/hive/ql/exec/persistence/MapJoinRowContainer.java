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


package org.apache.hadoop.hive.ql.exec.persistence;

import java.util.ArrayList;
import java.util.List;

import org.apache.hadoop.hive.ql.metadata.HiveException;

public class MapJoinRowContainer<Row> extends AbstractRowContainer<Row> {

  private List<Row> list;

  private int index;

  public MapJoinRowContainer() {
    index = 0;
    list = new ArrayList<Row>(1);
  }

  @Override
  public void add(Row t) throws HiveException {
    list.add(t);
  }


  @Override
  public Row first() throws HiveException {
    index = 0;
    if (index < list.size()) {
      return list.get(index);
    }
    return null;
  }

  @Override
  public Row next() throws HiveException {
    index++;
    if (index < list.size()) {
      return list.get(index);
    }
    return null;

  }

  /**
   * Get the number of elements in the RowContainer.
   *
   * @return number of elements in the RowContainer
   */
  @Override
  public int size() {
    return list.size();
  }

  /**
   * Remove all elements in the RowContainer.
   */
  @Override
  public void clear() throws HiveException {
    list.clear();
    index = 0;
  }

  public List<Row> getList() {
    return list;
  }

  public void setList(List<Row> list) {
    this.list = list;
  }

  public void reset(MapJoinRowContainer<Object[]> other) throws HiveException {
    list.clear();
    Object[] obj;
    for (obj = other.first(); obj != null; obj = other.next()) {
      ArrayList<Object> ele = new ArrayList(obj.length);
      for (int i = 0; i < obj.length; i++) {
        ele.add(obj[i]);
      }
      list.add((Row) ele);
    }
  }
}
