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

/**
 * A union of HiveDeserializer and HiveSerializer interface.
 * 
 * If a developer wants his hive table to be read-only, then he just want to
 * return
 * 
 * both readable and writable, then
 * 
 * 
 */
public interface SerDe extends Deserializer, Serializer {

}
