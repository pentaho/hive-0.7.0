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


package org.apache.hadoop.hive.ql.lib;

import java.util.Stack;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.hadoop.hive.ql.parse.SemanticException;

/**
 * Rule interface for Nodes Used in Node dispatching to dispatch process/visitor
 * functions for Nodes.
 */
public class RuleRegExp implements Rule {

  private final String ruleName;
  private final Pattern pattern;

  /**
   * The rule specified by the regular expression. Note that, the regular
   * expression is specified in terms of Node name. For eg: TS.*RS -> means
   * TableScan Node followed by anything any number of times followed by
   * ReduceSink
   * 
   * @param ruleName
   *          name of the rule
   * @param regExp
   *          regular expression for the rule
   **/
  public RuleRegExp(String ruleName, String regExp) {
    this.ruleName = ruleName;
    pattern = Pattern.compile(regExp);
  }

  /**
   * This function returns the cost of the rule for the specified stack. Lower
   * the cost, the better the rule is matched
   * 
   * @param stack
   *          Node stack encountered so far
   * @return cost of the function
   * @throws SemanticException
   */
  public int cost(Stack<Node> stack) throws SemanticException {
    int numElems = (stack != null ? stack.size() : 0);
    String name = new String();
    for (int pos = numElems - 1; pos >= 0; pos--) {
      name = stack.get(pos).getName() + "%" + name;
      Matcher m = pattern.matcher(name);
      if (m.matches()) {
        return m.group().length();
      }
    }

    return -1;
  }

  /**
   * @return the name of the Node
   **/
  public String getName() {
    return ruleName;
  }
}
