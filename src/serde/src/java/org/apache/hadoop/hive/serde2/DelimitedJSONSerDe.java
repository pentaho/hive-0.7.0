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


package org.apache.hadoop.hive.serde2;

import java.io.IOException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.hadoop.hive.serde2.lazy.LazySimpleSerDe;
import org.apache.hadoop.hive.serde2.objectinspector.ObjectInspector;
import org.apache.hadoop.hive.serde2.objectinspector.ObjectInspector.Category;
import org.apache.hadoop.hive.serde2.objectinspector.primitive.PrimitiveObjectInspectorFactory;
import org.apache.hadoop.io.Writable;

/**
 * DelimitedJSONSerDe.
 *
 * This serde can only serialize, because it is just intended for use by the FetchTask class and the
 * TRANSFORM function.
 */
public class DelimitedJSONSerDe extends LazySimpleSerDe {

  public static final Log LOG = LogFactory.getLog(DelimitedJSONSerDe.class.getName());

  public DelimitedJSONSerDe() throws SerDeException {
  }

  /**
   * Not implemented.
   */
  @Override
  public Object deserialize(Writable field) throws SerDeException {
    LOG.error("DelimitedJSONSerDe cannot deserialize.");
    throw new SerDeException("DelimitedJSONSerDe cannot deserialize.");
  }

  @Override
  protected void serializeField(ByteStream.Output out, Object obj, ObjectInspector objInspector,
      SerDeParameters serdeParams) throws SerDeException {
    if (!objInspector.getCategory().equals(Category.PRIMITIVE)) {
      try {
        serialize(out, SerDeUtils.getJSONString(obj, objInspector),
            PrimitiveObjectInspectorFactory.javaStringObjectInspector, serdeParams.getSeparators(),
            1, serdeParams.getNullSequence(), serdeParams.isEscaped(), serdeParams.getEscapeChar(),
            serdeParams.getNeedsEscape());

      } catch (IOException e) {
        throw new SerDeException(e);
      }

    } else {
      super.serializeField(out, obj, objInspector, serdeParams);
    }
  }
}
