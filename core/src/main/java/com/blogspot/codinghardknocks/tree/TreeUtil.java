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
 * Utility implementations of various {@link Tree} functions.
 *
 * @author kbohling
 *
 */
public class TreeUtil {

    /**
     * Balance the tree using the DSW methodology.
     * <p>
     * <ul>
     * <li><a href="http://penguin.ewu.edu/~trolfe/DSWpaper">Trolfe's DSW page</a>.
     * <li><a href="http://www.eecs.umich.edu/~qstout/pap/CACM86.pdf">Original Paper</a>.
     * </ul>
     *
     * @param <K> The type of key used.
     * @param <N> The type of node used.
     * @param tree The tree to balance.
     */
    public static <K extends Comparable<K>, N extends Node<K, N>> void balanceTree(Tree<K, N> tree) {
        if (tree == null) {
            return;
        }

        int nodeCount = constructVine(tree);

        if (nodeCount <= 1) {
            return;
        }

        int fullTreeSize = powerOf2Smaller(nodeCount) - 1;

        compression(tree, nodeCount - fullTreeSize);
        for (int size = fullTreeSize / 2; size > 0; size = size / 2) {
            compression(tree, size);
        }
    }

    /**
     * Compression is the name given to it by DSW, but it executes the following operation count times:
     * <code>
     * curr = tree.rotateLeft(curr);
     * curr = curr.getRight();
     * </code>
     *
     * @param <K> The key type used by the nodes.
     * @param <N> The node type used by the tree.
     * @param vine The 'vine' to compress (it is a tree, but this expects the tree to be vine shaped.
     * @param count The number of operations to run.
     */
    private static <K extends Comparable<K>, N extends Node<K, N>> void compression(Tree<K, N> vine, int count) {
        N curr = vine.getRoot();
        for (int i = 0; i < count; ++i) {
            curr = vine.rotateLeft(curr);
            // NOTE: If this becomes null, we did the math wrong.
            curr = curr.getRight();
        }
    }

    /**
     * Translate the tree into a vine (the term used for a tree where all the nodes are right sub-children).
     *
     * @param <K> The type of key used.
     * @param <N> The type of node used.
     * @param tree The tree to turn into a vine.
     * @return The number of nodes in the vine.
     */
    private static <K extends Comparable<K>, N extends Node<K, N>> int constructVine(Tree<K, N> tree) {
        int count = 0;
        N curr = tree.getRoot();
        while (!tree.isNull(curr)) {
            while (!tree.isNull(curr.getLeft())) {
                curr = tree.rotateRight(curr);
            }
            curr = curr.getRight();
            count++;
        }
        return count;
    }

    /**
     * Compute the power of 2 which is less than or equal to the current value, throws an exception if passed a value
     * less than or equal to zero.
     *
     * @param value The value to compute the smaller power of two.
     * @return
     */
    private static int powerOf2Smaller(int value) {
        // XXX: Profile this and verify this is sane.  A binary search or a hardcoded set of nested if/else cases
        // would likely do the job.

        // Handle the underflow case.
        if (value <= 0) {
            throw new ArithmeticException("No power of two less than or equal to zero.");
        } else if (value == 1) {
            return 1;
        } else if (value > (1 << 31)) {
            // The while loop will blow up if let this go, so handle it special.
            return 1 << 31;
        }

        int result = 1;
        while (value <= result * 2) {
            result = result * 2;
        }
        return result;
    }

}
