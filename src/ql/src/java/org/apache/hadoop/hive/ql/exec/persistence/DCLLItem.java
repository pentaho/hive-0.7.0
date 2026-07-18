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

/**
 * Doubly circular linked list item.
 */
public class DCLLItem {

  DCLLItem prev;
  DCLLItem next;

  DCLLItem() {
    prev = next = this;
  }

  /**
   * Get the next item.
   * 
   * @return the next item.
   */
  public DCLLItem getNext() {
    return next;
  }

  /**
   * Get the previous item.
   * 
   * @return the previous item.
   */
  public DCLLItem getPrev() {
    return prev;
  }

  /**
   * Set the next item as itm.
   * 
   * @param itm
   *          the item to be set as next.
   */
  public void setNext(DCLLItem itm) {
    next = itm;
  }

  /**
   * Set the previous item as itm.
   * 
   * @param itm
   *          the item to be set as previous.
   */
  public void setPrev(DCLLItem itm) {
    prev = itm;
  }

  /**
   * Remove the current item from the doubly circular linked list.
   */
  public void remove() {
    next.prev = prev;
    prev.next = next;
    prev = next = null;
  }

  /**
   * Add v as the previous of the current list item.
   * 
   * @param v
   *          inserted item.
   */
  public void insertBefore(DCLLItem v) {
    prev.next = v;
    v.prev = prev;
    v.next = this;
    prev = v;
  }

  /**
   * Add v as the previous of the current list item.
   * 
   * @param v
   *          inserted item.
   */
  public void insertAfter(DCLLItem v) {
    next.prev = v;
    v.next = next;
    v.prev = this;
    next = v;
  }
}
