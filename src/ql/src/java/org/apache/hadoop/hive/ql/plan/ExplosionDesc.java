/*!
* Copyright 2010 - 2013 Pentaho Corporation.  All rights reserved.
*
* Licensed under the Apache License, Version 2.0 (the "License");
* you may not use this file except in compliance with the License.
* You may obtain a copy of the License at
*
* http://www.apache.org/licenses/LICENSE-2.0
*
* Unless required by applicable law or agreed to in writing, software
* distributed under the License is distributed on an "AS IS" BASIS,
* WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
* See the License for the specific language governing permissions and
* limitations under the License.
*
*/

package org.apache.hadoop.hive.ql.plan;

import java.io.Serializable;

/**
 * ExplosionDesc.
 *
 */
@Explain(displayName = "Explosion")
public class ExplosionDesc implements Serializable {
  private static final long serialVersionUID = 1L;
  private String fieldName;
  private int position;

  public ExplosionDesc() {
  }

  public ExplosionDesc(final String fieldName, final int position) {
    this.fieldName = fieldName;
    this.position = position;
  }

  public String getFieldName() {
    return fieldName;
  }

  public void setFieldName(final String fieldName) {
    this.fieldName = fieldName;
  }

  public int getPosition() {
    return position;
  }

  public void setPosition(final int position) {
    this.position = position;
  }
}
