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

import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.util.ArrayList;

import org.apache.hadoop.hive.ql.exec.MapJoinMetaData;
import org.apache.hadoop.hive.ql.exec.HashTableSinkOperator.HashTableSinkObjectCtx;
import org.apache.hadoop.hive.serde2.SerDeException;
import org.apache.hadoop.hive.serde2.objectinspector.ObjectInspectorUtils;
import org.apache.hadoop.hive.serde2.objectinspector.ObjectInspectorUtils.ObjectInspectorCopyOption;
import org.apache.hadoop.io.Writable;

/**
 * Map Join Object used for both key.
 */
public class MapJoinObjectKey  extends AbstractMapJoinKey {


  protected transient Object[] obj;

  public MapJoinObjectKey() {
  }

  /**
   * @param metadataTag
   * @param obj
   */
  public MapJoinObjectKey(Object[] obj) {
    this.obj = obj;
  }

  @Override
  public boolean equals(Object o) {
    if (o instanceof MapJoinObjectKey) {
      MapJoinObjectKey mObj = (MapJoinObjectKey) o;
      Object[] mObjArray = mObj.getObj();
      if ((obj == null) && (mObjArray == null)) {
        return true;
      }
      if ((obj != null) && (mObjArray != null)) {
        if (obj.length == mObjArray.length) {
          for (int i = 0; i < obj.length; i++) {
            if (!obj[i].equals(mObjArray[i])) {
              return false;
            }
          }
          return true;
        }
      }
    }
    return false;
  }

  @Override
  public int hashCode() {
    int hashCode;
    if (obj == null) {
      hashCode = metadataTag;
    } else {
      hashCode = 1;

      for (int i = 0; i < obj.length; i++) {
        Object o = obj[i];
        hashCode = 31 * hashCode + (o == null ? 0 : o.hashCode());
      }

    }
    return hashCode;
  }

  @Override
  public void readExternal(ObjectInput in) throws IOException,
      ClassNotFoundException {
    try {
      // get the tableDesc from the map stored in the mapjoin operator
      HashTableSinkObjectCtx ctx = MapJoinMetaData.get(
          Integer.valueOf(metadataTag));

      Writable val = ctx.getSerDe().getSerializedClass().newInstance();
      val.readFields(in);
      ArrayList<Object> list = (ArrayList<Object>) ObjectInspectorUtils.copyToStandardObject(ctx
          .getSerDe().deserialize(val), ctx.getSerDe().getObjectInspector(),
          ObjectInspectorCopyOption.WRITABLE);
      if(list == null){
        obj = new ArrayList(0).toArray();
      }else{
        obj = list.toArray();
      }

    } catch (Exception e) {
      throw new IOException(e);
    }

  }

  @Override
  public void writeExternal(ObjectOutput out) throws IOException {
    try {
      // get the tableDesc from the map stored in the mapjoin operator
      HashTableSinkObjectCtx ctx = MapJoinMetaData.get(
          Integer.valueOf(metadataTag));

      // Different processing for key and value
      Writable outVal = ctx.getSerDe().serialize(obj, ctx.getStandardOI());
      outVal.write(out);
    } catch (SerDeException e) {
      throw new IOException(e);
    }
  }


  /**
   * @return the obj
   */
  public Object[] getObj() {
    return obj;
  }

  /**
   * @param obj
   *          the obj to set
   */
  public void setObj(Object[] obj) {
    this.obj = obj;
  }

  @Override
  public boolean hasAnyNulls(){
    if (obj != null && obj.length> 0) {
      for (Object k : obj) {
        if (k == null) {
          return true;
        }
      }
    }
    return false;

  }

}
