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
 *
 */
@NotThreadSafe
public abstract class AbstractBST<K extends Comparable<K>, N extends Node<K, N>> implements Tree<K, N> {

    /** The root of the binary search tree. */
    protected N root;

    @Override
    public abstract boolean isNull(N node);

    protected abstract N getNull();

    protected N max(N root) {
        N prev = getNull();
        N curr = root;
        while (!isNull(curr)) {
            prev = curr;
            curr = curr.getRight();
        }
        return prev;
    }

    protected N min(N root) {
        N prev = getNull();
        N curr = root;
        while (!isNull(curr)) {
            prev = curr;
            curr = curr.getLeft();
        }
        return prev;
    }

    protected N successor(N node) {
        N right = node.getRight();

        if (!isNull(right)) {
            return min(right);
        }

        N parent = node.getParent();

        while (!isNull(parent) && parent.getRight() == node) {
            node = parent;
            parent = parent.getParent();
        }

        return parent;
    }

    protected N predecessor(N node) {
        N left = node.getLeft();

        if (!isNull(left)) {
            return max(left);
        }

        N parent = node.getParent();

        while (!isNull(parent) && parent.getLeft() == node) {
            node = parent;
            parent = parent.getParent();
        }

        return parent;
    }

    protected int compare(K search, K nodeKey) {
        return search.compareTo(nodeKey);
    }

    protected N walk(N root, K key) {
        N prev = getNull();
        N curr = root;

        int cmp = -1;
        while (cmp != 0 && !isNull(curr)) {
            K currKey = curr.getKey();

            cmp = compare(key, currKey);

            prev = curr;

            if (cmp < 0) {
                curr = curr.getLeft();
            } else if (cmp > 0) {
                prev = curr;
                curr = curr.getRight();
            } else {
                return curr;
            }
        }
        return prev;
    }

    protected boolean isLeftChild(N node) {
        N parent = node.getParent();
        return (!isNull(parent) && parent.getLeft() == node);
    }

    protected boolean isRightChild(N node) {
        N parent = node.getParent();
        return (!isNull(parent) && parent.getRight() == node);
    }

    protected void transplant(N remove, N replacement) {
        if (isNull(remove.getParent())) {
            setRoot(replacement);
        }
        else if (isLeftChild(remove)) {
            remove.getParent().setLeft(replacement);
        } else {
            remove.getParent().setRight(replacement);
        }

        if (!isNull(replacement)) {
            replacement.setParent(remove.getParent());
        }
    }

    @Override
    public N rotateLeft(N oldTop) {
        N newTop = oldTop.getRight();
        oldTop.setRight(newTop.getLeft());

        if (!isNull(newTop.getLeft())) {
            newTop.getLeft().setParent(oldTop);
        }

        newTop.setParent(oldTop.getParent());

        if (!isNull(oldTop.getParent())) {
            setRoot(newTop);
        } else if (isLeftChild(oldTop)) {
            oldTop.getParent().setLeft(newTop);
        } else if (isRightChild(oldTop)) {
            oldTop.getParent().setRight(newTop);
        }

        return newTop;
    }

    @Override
    public N rotateRight(N oldTop) {
        N newTop = oldTop.getLeft();
        oldTop.setRight(newTop.getRight());

        if (!isNull(newTop.getRight())) {
            newTop.getRight().setParent(oldTop);
        }

        newTop.setParent(oldTop.getParent());

        if (!isNull(oldTop.getParent())) {
            setRoot(newTop);
        } else if (isLeftChild(oldTop)) {
            oldTop.getParent().setLeft(newTop);
        } else if (isRightChild(oldTop)) {
            oldTop.getParent().setRight(newTop);
        }
        return newTop;
    }

    @Override
    public N getRoot() {
        return root;
    }

    protected void setRoot(N root) {
        this.root = root;
    }

    @Override
    public N getMax() {
        return max(root);
    }

    @Override
    public N getMin() {
        return min(root);
    }

    @Override
    public N getSuccessor(N node) {
        return successor(node);
    }

    @Override
    public N getPredecessor(N node) {
        return predecessor(node);
    }

    @Override
    public N search(K key) {
        N curr = walk(root, key);
        int cmp = compare(key, curr.getKey());
        return (cmp == 0) ? curr : getNull();
    }

}
