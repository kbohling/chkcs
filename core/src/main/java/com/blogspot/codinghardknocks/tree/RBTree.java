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
public class RBTree<K extends Comparable<K>> extends AbstractBST<K, RBNode<K>> {

    enum Color {
        RED,
        BLACK
    }

    public RBTree() {
    }

    @Override
    public RBNode<K> insert(K key) {
        return insertBottomUp(key);
    }

    // Weiss Top Down Insertion Technique.
    public RBNode<K> insertTopDown(K key) {
        if (root == null) {
            root = new RBNode<K>(key, Color.BLACK);
            return root;
        }

        RBNode<K> parent = root.getParent();
        RBNode<K> curr = root;
        int cmp = 0;
        while (curr != null) {

            RBNode<K> left = curr.getLeft();
            RBNode<K> right = curr.getRight();

            if (left.getColor() == Color.RED && right.getColor() == Color.RED) {
                curr.setColor(Color.RED);
                left.setColor(Color.BLACK);
                right.setColor(Color.BLACK);
            }

            cmp = compare(key, curr.getKey());

            if (cmp == 0) {
                return curr;
            }

            if (cmp < 0) {
                parent = curr;
                curr = left;
            } else {
                parent = curr;
                curr = right;
            }
        }

        RBNode<K> child = new RBNode<K>(key, Color.RED);
        child.setParent(parent);

        RBNode<K> grandparent = parent.getParent();

        if (cmp < 0) {
            parent.setLeft(child);

            if (parent.getColor() == Color.RED) {
                RBNode<K> parentSibling = grandparent.getRight();
                if (isRightChild(child)) {
                    parent = rotateLeft(parent);
                    child = parent.getLeft();
                }

                grandparent.setColor(Color.BLACK);
                parentSibling.setColor(Color.RED);
                grandparent = rotateRight(grandparent);
            }
        } else {
            parent.setRight(child);

            if (parent.getColor() == Color.RED) {
                RBNode<K> parentSibling = grandparent.getLeft();

                if (isLeftChild(child)) {
                    parent = rotateRight(parent);
                    child = parent.getRight();
                }

                grandparent.setColor(Color.BLACK);
                parentSibling.setColor(Color.RED);
                grandparent = rotateLeft(grandparent);
            }
        }


        return child;
    }

    // CLR Book Bottom Up Insertion Technique.
    public RBNode<K> insertBottomUp(K key) {
        // Handle the empty tree case first.
        if (root == null) {
            root = new RBNode<K>(key, Color.BLACK);
            return root;
        }

        // Look for where to insert.
        RBNode<K> parent = walk(root, key);

        int cmp = compare(key, parent.getKey());

        if (cmp == 0) {
            // Not, the parent, key already exists, bail.
            return parent;
        }

        RBNode<K> node = new RBNode<K>(key, Color.RED);
        node.setParent(parent);
        if (cmp < 0) {
            parent.setLeft(node);
        } else {
            parent.setRight(node);
        }

        if (parent.getColor() == Color.RED) {
            insertFixup(node);
        }
        return node;
    }

    public void insertFixup(RBNode<K> child) {
        // Knowns:  The root exists, or we wouldn't be here.  We know the parent exists, because we are below the
        // root.  If we are one node below the root, we won't enter the loop.
        while (child.getParent().getColor() == Color.RED) {
            RBNode<K> parent = child.getParent();
            RBNode<K> grandparent = parent.getParent();

            if (isLeftChild(parent)) {
                RBNode<K> parentSibling = grandparent.getRight();

                if (parentSibling.getColor() == Color.RED) {
                    parentSibling.setColor(Color.BLACK);
                    parent.setColor(Color.BLACK);
                    grandparent.setColor(Color.RED);
                    child = grandparent;
                } else {
                    if (isRightChild(child)) {
                        parent = rotateLeft(parent);
                        child = parent.getLeft();
                    }

                    grandparent.setColor(Color.BLACK);
                    parentSibling.setColor(Color.RED);
                    grandparent = rotateRight(grandparent);
                }

            } else {
                RBNode<K> parentSibling = grandparent.getLeft();

                if (parentSibling.getColor() == Color.RED) {
                    parentSibling.setColor(Color.BLACK);
                    parent.setColor(Color.BLACK);
                    grandparent.setColor(Color.RED);
                    child = grandparent;
                } else {
                    if (isLeftChild(child)) {
                        parent = rotateRight(parent);
                        child = parent.getRight();
                    }

                    grandparent.setColor(Color.BLACK);
                    parentSibling.setColor(Color.RED);
                    grandparent = rotateLeft(grandparent);
                }
            }
        }

        root.setColor(Color.BLACK);
    }

    @Override
    public RBNode<K> delete(K key) {
        return null;
    }

    @Override
    public boolean isNull(RBNode<K> node) {
        return node == null || node == RBNode.<K>getNil();
    }

    @Override
    protected RBNode<K> getNull() {
        return RBNode.getNil();
    }


}
