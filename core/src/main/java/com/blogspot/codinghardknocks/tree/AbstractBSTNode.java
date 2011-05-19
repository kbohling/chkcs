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

import com.blogspot.codinghardknocks.annotations.NotThreadSafe;

/**
 * Core implementation of a binary search tree node.
 *
 * @author kbohling
 */
@NotThreadSafe
public abstract class AbstractBSTNode<K extends Comparable<K>, N extends Node<K, N>>
        implements Node<K, N> {

    private N left;
    private N right;
    private N parent;
    private K key;

    /**
     * Constructor.
     * @param key The key value of the node.
     */
    public AbstractBSTNode(K key) {
        this.left = null;
        this.right = null;
        this.parent = null;
        this.key = key;
    }

    @Override
    public N getParent() {
        return parent;
    }

    @Override
    public N getLeft() {
        return left;
    }

    @Override
    public N getRight() {
        return right;
    }

    public void setLeft(N left) {
        this.left = left;
    }

    public void setRight(N right) {
        this.right = right;
    }

    public void setParent(N parent) {
        this.parent = parent;
    }

    @Override
    public K getKey() {
        return key;
    }

}
