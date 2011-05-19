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
public class SplayTree<K extends Comparable<K>> extends BST<K> {

    /**
     * Bottom up implementation of splay.
     *
     * @param node The node to splay to the root of the tree.
     */
    private void splay(BSTNode<K> node) {
        while (node != getRoot()) {
            if (isLeftChild(node)) {
                rotateRight(node.getParent());
            } else {
                rotateLeft(node.getParent());
            }
        }
    }

    @Override
    public BSTNode<K> insert(K key) {
        BSTNode<K> result = super.insert(key);
        splay(result);
        return result;
    }

    @Override
    public BSTNode<K> search(K key) {
        BSTNode<K> result =  super.search(key);
        if (!isNull(result)) {
            splay(result);
        }
        return result;
    }

    @Override
    public BSTNode<K> delete(K key) {
        BSTNode<K> result =  super.delete(key);
        if (!isNull(result)) {
            splay(result.getParent());
        }
        return result;
    }

}
