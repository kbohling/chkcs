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

import com.blogspot.codinghardknocks.annotations.NotThreadSafe;

import static com.blogspot.codinghardknocks.trie.TrieUtil.walk;
import static com.blogspot.codinghardknocks.trie.TrieUtil.walkAndAdd;


/**
 * A {@link TrieMap<K, V>} which uses a {@link HashMap<K, V>} as the underlying
 * data structure for computing transitions between internal states.
 *
 * @author kbohling
 *
 */
@NotThreadSafe
public abstract class AbstractTrieMap<K, V> implements TrieMap<K, V> {

    private final TrieMapNode<K, V> root;

    protected AbstractTrieMap(TrieMapNode<K, V> root) {
        this.root = root;
    }

    @Override
    public V addIfAbsent(Iterator<K> keys, V value) {
        TrieMapNode<K, V> currNode = walkAndAdd(keys, root);

        if (currNode.isAccepting()) {
            return currNode.value();
        }
        currNode.setAccepting(true);
        currNode.setValue(value);
        return value;
    }

    @Override
    public void clear() {
        root.clear();
    }

    @Override
    public boolean contains(Iterator<K> keys) {
        TrieMapNode<K, V> node = walk(keys, root);
        return (node != null && node.isAccepting());
    }

    @Override
    public boolean isEmpty() {
        return !root.hasAccepting();
    }

    @Override
    public V get(Iterator<K> keys) {
        return get(keys, null);
    }

    @Override
    public V get(Iterator<K> keys, V defaultValue) {
        TrieMapNode<K, V> currNode = walk(keys, root);

        if (currNode != null && currNode.isAccepting()) {
            return currNode.value();
        }
        return defaultValue;
    }

    @Override
    public int size() {
        return root.countAccepting();
    }
}
