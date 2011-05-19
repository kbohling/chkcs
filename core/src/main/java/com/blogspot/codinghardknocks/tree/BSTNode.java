/*
 * Copyright 2011 Kirby C. Bohling
 *
 * This code is licensed under the Apache License, Version 2.0 (the "License")
 * terms.  You may not use this file except in compliance with the License. You
 * may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.blogspot.codinghardknocks.tree;

/**
 * @author kbohling
 *
 */
public class BSTNode<K extends Comparable<K>> extends AbstractBSTNode<K, BSTNode<K>> {

    /**
     * Constructor.
     * @param key The key value used by this node.
     */
    public BSTNode(K key) {
        super(key);
    }

}
