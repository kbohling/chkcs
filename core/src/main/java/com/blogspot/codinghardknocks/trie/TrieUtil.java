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
package com.blogspot.codinghardknocks.trie;

import java.util.Iterator;

import com.blogspot.codinghardknocks.annotations.ThreadSafe;

/**
 * Utility methods commonly used by many subclasses, but are difficult to implement in a base class.
 *
 * @author kbohling
 *
 */
@ThreadSafe
public class TrieUtil {

    /**
     * Constructor.
     */
    private TrieUtil() {
    }

    /**
     * Walk the {@link TrieMapNode} structure to the end point.
     *
     * @param <K> The type of keys used by the {@link TrieMapNode}.
     * @param <V> The values associated with the {@link TrieMapNode}.
     * @param keys The keys to walk.
     * @param root The origin node of the root.
     * @return The final node, or <code>null</code> if the path does not exist.
     */
    public static <K, V> TrieMapNode<K, V> walk(Iterator<K> keys, TrieMapNode<K, V> root) {
        TrieMapNode<K, V> currNode = root;

        while (keys.hasNext()) {
            K currKey = keys.next();
            currNode = currNode.next(currKey);

            if (null == currNode) {
                return null;
            }
        }

        return currNode;
    }

    /**
     * Walk and add any missing key values along the path.  Return the last node in the path, all
     * nodes created are not accepting.
     *
     * @param <K> The type of key used by the {@link TrieMap}.
     * @param <V> The type of value stored in the {@link TrieMap}.
     * @param keys The key sequence used to walk.
     * @param root The root node to start the walk from.
     * @return The final node in the walk, should never be null.
     */
    public static <K, V> TrieMapNode<K, V> walkAndAdd(Iterator<K> keys, TrieMapNode<K, V> root) {
        TrieMapNode<K, V> currNode = root;
        while (keys.hasNext()) {
            K currKey = keys.next();
            currNode = currNode.addTransition(currKey);
        }
        return currNode;
    }
}
