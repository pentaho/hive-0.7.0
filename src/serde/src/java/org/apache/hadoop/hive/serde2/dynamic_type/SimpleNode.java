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

  /*
   * You can override these two methods in subclasses of SimpleNode to customize
   * the way the node appears when the tree is dumped. If your output uses more
   * than one line you should override toString(String), otherwise overriding
   * toString() is probably all you need to do.
   */

  @Override
  public String toString() {
    return thrift_grammarTreeConstants.jjtNodeName[id];
  }

  public String toString(String prefix) {
    return prefix + toString();
  }

  /*
   * Override this method if you want to customize how the node dumps out its
   * children.
   */

  public void dump(String prefix) {
    System.out.println(toString(prefix));
    if (children != null) {
      for (int i = 0; i < children.length; ++i) {
        SimpleNode n = (SimpleNode) children[i];
        if (n != null) {
          n.dump(prefix + " ");
        }
      }
    }
  }
}
