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
 * The Interface for all trees.  All trees use equivalence not equality for uniqueness.  All trees allow only one
 * copy of a value in a tree.
 *
 * @author kbohling
 *
 */
public interface Tree< K extends Comparable<K>, N extends Node<K, N>> {

    /**
     * Get the maximum value in the tree.
     *
     * @return The node containing the maximum value in the tree.
     */
    N getMax();

    /**
     * Get the minimum value in the tree.
     *
     * @return The minum value in the tree.
     */
    N getMin();

    /**
     * Get the next largest node after this node, or <code>null</code> if this is the largest node.
     * @param node The node to compute the next largest value.
     *
     * @return The next largest node in the tree.
     */
    N getSuccessor(N node);

    /**
     * Get the next smallest node before this node, or <code>null</code> if this is the smallest node in the tree.
     * @param node The node to get the next smallest one.
     * @return The next smaller node.
     */
    N getPredecessor(N node);

    /**
     * Insert the key into the tree.
     * @param key The key to insert.
     * @return The node after it is inserted.
     */
    N insert(K key);

    /**
     * Delete the key.
     *
     * @param key
     * @return
     */
    N delete(K key);

    /**
     * Find the node for this key, return <code>null</code> if the key value cannot be found.
     * @param key The key.
     * @return The node containing the key.
     */
    N search(K key);

    /**
     * Rotate the tree based upon this node to the left.
     *
     * @param oldTop The node to rotate out of the top.
     * @return The new top value.
     */
    N rotateLeft(N oldTop);

    /**
     * Rotate the tree based upon this node to the right.
     *
     * @param oldTop The node to rotate out of the top.
     * @return The new top value.
     */
    N rotateRight(N oldTop);

    /**
     * Get the root node of the tree.
     * @return The root node.
     */
    N getRoot();

    /**
     * Check if the node should be considered <code>null</code> or is a sentinel value for it.
     *
     * @param node The node to check of <code>null</code> status.
     * @return <code>true</code> if the node is <code>null</code> or <code>false</code> otherwise.
     */
    boolean isNull(N node);
}
