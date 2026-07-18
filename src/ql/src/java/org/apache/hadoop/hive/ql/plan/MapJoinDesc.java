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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

/**
 * Map Join operator Descriptor implementation.
 *
 */
@Explain(displayName = "Map Join Operator")
public class MapJoinDesc extends JoinDesc implements Serializable {
  private static final long serialVersionUID = 1L;

  private Map<Byte, List<ExprNodeDesc>> keys;
  private TableDesc keyTblDesc;
  private List<TableDesc> valueTblDescs;
  private List<TableDesc> valueFilteredTblDescs;

  private int posBigTable;

  private Map<Byte, List<Integer>> retainList;

  private transient String bigTableAlias;

  private LinkedHashMap<String, LinkedHashMap<String, ArrayList<String>>> aliasBucketFileNameMapping;
  private LinkedHashMap<String, Integer> bucketFileNameMapping;

  public MapJoinDesc() {
    bucketFileNameMapping = new LinkedHashMap<String, Integer>();
  }

  public MapJoinDesc(MapJoinDesc clone) {
    super(clone);
    this.keys = clone.keys;
    this.keyTblDesc = clone.keyTblDesc;
    this.valueTblDescs = clone.valueTblDescs;
    this.posBigTable = clone.posBigTable;
    this.retainList = clone.retainList;
    this.bigTableAlias = clone.bigTableAlias;
    this.aliasBucketFileNameMapping = clone.aliasBucketFileNameMapping;
    this.bucketFileNameMapping = clone.bucketFileNameMapping;
  }

  public MapJoinDesc(final Map<Byte, List<ExprNodeDesc>> keys,
      final TableDesc keyTblDesc, final Map<Byte, List<ExprNodeDesc>> values,
      final List<TableDesc> valueTblDescs,final List<TableDesc> valueFilteredTblDescs,  List<String> outputColumnNames,
      final int posBigTable, final JoinCondDesc[] conds,
      final Map<Byte, List<ExprNodeDesc>> filters, boolean noOuterJoin) {
    super(values, outputColumnNames, noOuterJoin, conds, filters);
    this.keys = keys;
    this.keyTblDesc = keyTblDesc;
    this.valueTblDescs = valueTblDescs;
    this.valueFilteredTblDescs = valueFilteredTblDescs;
    this.posBigTable = posBigTable;
    this.bucketFileNameMapping = new LinkedHashMap<String, Integer>();
    initRetainExprList();
  }

  private void initRetainExprList() {
    retainList = new HashMap<Byte, List<Integer>>();
    Set<Entry<Byte, List<ExprNodeDesc>>> set = super.getExprs().entrySet();
    Iterator<Entry<Byte, List<ExprNodeDesc>>> setIter = set.iterator();
    while (setIter.hasNext()) {
      Entry<Byte, List<ExprNodeDesc>> current = setIter.next();
      List<Integer> list = new ArrayList<Integer>();
      for (int i = 0; i < current.getValue().size(); i++) {
        list.add(i);
      }
      retainList.put(current.getKey(), list);
    }
  }

  public Map<Byte, List<Integer>> getRetainList() {
    return retainList;
  }

  public void setRetainList(Map<Byte, List<Integer>> retainList) {
    this.retainList = retainList;
  }

  /**
   * @return the keys
   */
  @Explain(displayName = "keys")
  public Map<Byte, List<ExprNodeDesc>> getKeys() {
    return keys;
  }

  /**
   * @param keys
   *          the keys to set
   */
  public void setKeys(Map<Byte, List<ExprNodeDesc>> keys) {
    this.keys = keys;
  }

  /**
   * @return the position of the big table not in memory
   */
  @Explain(displayName = "Position of Big Table")
  public int getPosBigTable() {
    return posBigTable;
  }

  /**
   * @param posBigTable
   *          the position of the big table not in memory
   */
  public void setPosBigTable(int posBigTable) {
    this.posBigTable = posBigTable;
  }

  /**
   * @return the keyTblDesc
   */
  public TableDesc getKeyTblDesc() {
    return keyTblDesc;
  }

  /**
   * @param keyTblDesc
   *          the keyTblDesc to set
   */
  public void setKeyTblDesc(TableDesc keyTblDesc) {
    this.keyTblDesc = keyTblDesc;
  }

  public List<TableDesc> getValueFilteredTblDescs() {
    return valueFilteredTblDescs;
  }

  public void setValueFilteredTblDescs(List<TableDesc> valueFilteredTblDescs) {
    this.valueFilteredTblDescs = valueFilteredTblDescs;
  }

  /**
   * @return the valueTblDescs
   */
  public List<TableDesc> getValueTblDescs() {
    return valueTblDescs;
  }

  /**
   * @param valueTblDescs
   *          the valueTblDescs to set
   */
  public void setValueTblDescs(List<TableDesc> valueTblDescs) {
    this.valueTblDescs = valueTblDescs;
  }

  /**
   * @return bigTableAlias
   */
  public String getBigTableAlias() {
    return bigTableAlias;
  }

  /**
   * @param bigTableAlias
   */
  public void setBigTableAlias(String bigTableAlias) {
    this.bigTableAlias = bigTableAlias;
  }

  public LinkedHashMap<String, LinkedHashMap<String, ArrayList<String>>> getAliasBucketFileNameMapping() {
    return aliasBucketFileNameMapping;
  }

  public void setAliasBucketFileNameMapping(
      LinkedHashMap<String, LinkedHashMap<String, ArrayList<String>>> aliasBucketFileNameMapping) {
    this.aliasBucketFileNameMapping = aliasBucketFileNameMapping;
  }

  public LinkedHashMap<String, Integer> getBucketFileNameMapping() {
    return bucketFileNameMapping;
  }

  public void setBucketFileNameMapping(LinkedHashMap<String, Integer> bucketFileNameMapping) {
    this.bucketFileNameMapping = bucketFileNameMapping;
  }
}
