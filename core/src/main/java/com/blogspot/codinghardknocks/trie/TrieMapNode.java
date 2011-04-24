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

/**
 * Interface representing a TrieNode which contains data.
 *
 * @param <T>
 * @author kbohling
 */
public interface TrieMapNode<K, V> {

    /**
     * Remove all children from this node.
     */
    public void clear();

    /**
     * Getter for the current value.
     * @return The value associated with this node.
     */
    public V value();

    /**
     * Set the value associated with this node.
     * @param value The new value to associated with this node.
     */
    public void setValue(V value);

    /**
     * Return the next node.
     *
     * @param input The input to pick the next state.
     * @return The next node after the transition, or <code>null</code> if
     * no such transition exists.
     */
    public TrieMapNode<K, V> next(K input);

    /**
     * Add the transition for the given input.
     *
     * @param input The input to add the transition for.
     * @return The new transition node if it does not exist, or the existing
     *         node if the transition exists prior to this call.
     */
    public TrieMapNode<K, V> addTransition(K input);

    /**
     * Getter to check if the node is an accepting state.
     * @return <code>true</code> if the node is accepting, <code>false</code>
     *         otherwise.
     */
    public boolean isAccepting();

    /**
     * Set the accepting state of the node.
     * @param state The new state of the node.
     */
    public void setAccepting(boolean state);

    /**
     * Count the number of accepting states reachable from this node.
     *
     * @return The number of accepting nodes reachable from this node.
     */
    public int countAccepting();

    /**
     * Computes if there is any accepting states reachable from this node.
     * @return <code>true</code> if there is any accepting state,
     * <code>false</code> otherwise.
     */
    public boolean hasAccepting();

}
