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


package org.apache.hadoop.hive.serde2.dynamic_type;

import java.util.ArrayList;
import java.util.List;

import org.apache.hadoop.hive.serde.Constants;
import org.apache.hadoop.hive.serde2.SerDeException;
import org.apache.hadoop.hive.serde2.objectinspector.ListObjectInspector;
import org.apache.hadoop.hive.serde2.objectinspector.ObjectInspector;
import org.apache.hadoop.hive.serde2.thrift.WriteNullsProtocol;
import org.apache.thrift.TException;
import org.apache.thrift.protocol.TList;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.protocol.TType;

/**
 * DynamicSerDeTypeList.
 *
 */
public class DynamicSerDeTypeList extends DynamicSerDeTypeBase {

  @Override
  public boolean isPrimitive() {
    return false;
  }

  @Override
  public boolean isList() {
    return true;
  }

  // production is: list<FieldType()>

  private static final int FD_TYPE = 0;

  @Override
  public Class getRealType() {
    return java.util.ArrayList.class;
  }

  public DynamicSerDeTypeList(int i) {
    super(i);
  }

  public DynamicSerDeTypeList(thrift_grammar p, int i) {
    super(p, i);
  }

  public DynamicSerDeTypeBase getElementType() {
    return ((DynamicSerDeFieldType) jjtGetChild(FD_TYPE)).getMyType();
  }

  @Override
  public String toString() {
    return Constants.LIST_TYPE_NAME + "<" + getElementType().toString() + ">";
  }

  @Override
  public ArrayList<Object> deserialize(Object reuse, TProtocol iprot)
      throws SerDeException, TException, IllegalAccessException {
    TList thelist = iprot.readListBegin();
    if (thelist == null) {
      return null;
    }

    ArrayList<Object> deserializeReuse;
    if (reuse != null) {
      deserializeReuse = (ArrayList<Object>) reuse;
      // Trim to the size needed
      while (deserializeReuse.size() > thelist.size) {
        deserializeReuse.remove(deserializeReuse.size() - 1);
      }
    } else {
      deserializeReuse = new ArrayList<Object>();
    }
    deserializeReuse.ensureCapacity(thelist.size);
    for (int i = 0; i < thelist.size; i++) {
      if (i + 1 > deserializeReuse.size()) {
        deserializeReuse.add(getElementType().deserialize(null, iprot));
      } else {
        deserializeReuse.set(i, getElementType().deserialize(
            deserializeReuse.get(i), iprot));
      }
    }
    // in theory, the below call isn't needed in non thrift_mode, but let's not
    // get too crazy
    iprot.readListEnd();
    return deserializeReuse;
  }

  @Override
  public void serialize(Object o, ObjectInspector oi, TProtocol oprot)
      throws TException, SerDeException, NoSuchFieldException, IllegalAccessException {
    ListObjectInspector loi = (ListObjectInspector) oi;
    ObjectInspector elementObjectInspector = loi
        .getListElementObjectInspector();
    DynamicSerDeTypeBase mt = getElementType();

    WriteNullsProtocol nullProtocol =
        (oprot instanceof WriteNullsProtocol) ? (WriteNullsProtocol) oprot : null;

    if (o instanceof List) {
      List<?> list = (List<?>) o;
      oprot.writeListBegin(new TList(mt.getType(), list.size()));
      for (Object element : list) {
        if (element == null) {
          assert (nullProtocol != null);
          nullProtocol.writeNull();
        } else {
          mt.serialize(element, elementObjectInspector, oprot);
        }
      }
    } else {
      Object[] list = (Object[]) o;
      oprot.writeListBegin(new TList(mt.getType(), list.length));
      for (Object element : list) {
        if (element == null && nullProtocol != null) {
          assert (nullProtocol != null);
          nullProtocol.writeNull();
        } else {
          mt.serialize(element, elementObjectInspector, oprot);
        }
      }
    }
    // in theory, the below call isn't needed in non thrift_mode, but let's not
    // get too crazy
    oprot.writeListEnd();
  }

  @Override
  public byte getType() {
    return TType.LIST;
  }

}
