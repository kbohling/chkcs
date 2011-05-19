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
 * @author kbohling
 * @param <K> The key type.
 *
 */
@NotThreadSafe
public class BST<K extends Comparable<K>> extends AbstractBST<K, BSTNode<K>> {

    @Override
    public BSTNode<K> insert(K key) {
        if (isNull(root)) {
            root = new BSTNode<K>(key);
            return root;
        }

        BSTNode<K> curr = walk(root, key);

        int cmp = compare(key, curr.getKey());

        if (cmp < 0) {
            BSTNode<K> child = new BSTNode<K>(key);
            curr.setLeft(child);
            child.setParent(curr);
        } else if (cmp > 0) {
            BSTNode<K> child = new BSTNode<K>(key);
            curr.setRight(child);
            child.setParent(curr);
        }

        return curr;
    }

    @Override
    public BSTNode<K> delete(K key) {
        BSTNode<K> curr = walk(root, key);

        int cmp = compare(key, curr.getKey());
        if (cmp != 0) {
            return null;
        }

        if (isNull(curr.getLeft())) {
            transplant(curr, curr.getRight());
        } else if (isNull(curr.getRight())) {
            transplant(curr, curr.getLeft());
        } else {
            BSTNode<K> replacement = min(curr.getRight());

            if (replacement.getParent() != curr) {
                transplant(replacement, replacement.getRight());
                replacement.setRight(curr.getRight());
                replacement.getRight().setParent(replacement);
            }

            transplant(curr, replacement);
            replacement.setLeft(curr.getLeft());
            replacement.getLeft().setParent(replacement);
        }

        return curr;
    }

    @Override
    public boolean isNull(BSTNode<K> node) {
        return node == null;
    }

    @Override
    protected BSTNode<K> getNull() {
        return null;
    }

    @Override
    protected void setRoot(BSTNode<K> root) {
        this.root = root;
    }



}
