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

/**
 * The interface for a <a href="http://en.wikipedia.org/wiki/Trie">Trie</a>
 * which associates a sequence of key objects with a single value.
 *
 * @param <K> The key type.
 * @param <V> The value associated with a key sequence.
 *
 * @author kbohling
 */
public interface TrieMap<K, V> {

    /**
     * Add the value if there is no value stored at that key.
     *
     * @param keys The keys used to transition to the final node.
     * @param value The value to insert if there is no node associated with
     *        the series of keys.
     * @return The value associated with the keys, either the <code>value</code>
     * passed in, or the pre-existing value already associated.  It can return
     * <code>null</code> if the sequence of keys is associated with
     * <code>null</code> or if the given value is <code>null</code> and there
     * is no previously associated value.
     */
    V addIfAbsent(Iterator<K> keys, V value);

    /**
     * Remove all key sequences associated with the nodes.
     */
    void clear();

    /**
     * Check if the given sequence of keys is associated with a state.
     *
     * @param keys
     * @return
     */
    boolean contains(Iterator<K> keys);

    /**
     * Check if any key sequences are contained inside of this object.
     *
     * @return <code>true</code> if no sequence of keys is associated with
     * this object, <code>false</code> if at least one sequence of keys is
     * associated with a value.
     */
    boolean isEmpty();

    /**
     * Get the value associated with this key sequence.
     *
     * @param keys The sequence of keys to lookup.
     * return The value associated with this key sequence, or <code>null</code>
     * if no sequence is associated.
     */
    V get(Iterator<K> keys);

    /**
     * Get the value associated with this key sequence.
     *
     * @param keys The sequence of keys to lookup.
     * @param defaultValue The default value to return if no value is associated
     * with the given key sequence.  The default value will not be returned if
     * the value associated with the key sequence is associated with
     * <code>null</code>.
     *
     * @return The value associated with this key sequence, or the default
     * value if no value is associated with the key sequence.
     */
    V get(Iterator<K> keys, V defaultValue);

    /**
     * Returns the number of elements stored in this trie.
     *
     * @return The number number of elements associated with this trie.
     */
    int size();

}
