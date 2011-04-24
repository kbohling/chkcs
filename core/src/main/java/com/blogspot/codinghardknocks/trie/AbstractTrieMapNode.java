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

import java.util.Collection;

import com.blogspot.codinghardknocks.annotations.NotThreadSafe;

/**
 * A base {@link TrieMapNode} implementation which handles all of the boilerplate
 * associated with accepting.
 *
 * @author kbohling
 *
 * @param <K> The key value used to transition from one node to the next.
 * @param <V> The value stored inside the nodes.
 */
@NotThreadSafe
public abstract class AbstractTrieMapNode<K, V> implements TrieMapNode<K, V> {

    /**
     * <code>true</code> if this node is associated with an accepting state,
     * <code> false</code> otherwise.
     */
    protected boolean accepting;

    /** The value associated with this node. */
    protected V value;

    /**
     * Constructor.
     *
     * @param accepting <code>true</code> if the node is accepting, <
     *  code>false</code> otherwise.
     * @param value The value associated with this code, can be <code>null</code>.
     */
    public AbstractTrieMapNode(boolean accepting, V value) {
        this.accepting = accepting;
        this.value = value;
    }

    @Override
    public boolean isAccepting() {
        return accepting;
    }

    @Override
    public void setAccepting(boolean state) {
        this.accepting = state;
    }

    @Override
    public V value() {
        return value;
    }

    @Override
    public void setValue(V obj) {
        this.value = obj;
    }

    @Override
    public void clear() {
        accepting = false;
        value = null;
    }

    @Override
    public int countAccepting() {
        int count = isAccepting() ? 1 : 0;

        // An effective depth first search of the nodes.
        for (TrieMapNode<K, V> node : nextNodes()) {
            count += node.countAccepting();
        }

        return count;
    }

    @Override
    public boolean hasAccepting() {
        if (isAccepting()) {
            return true;
        }

        for (TrieMapNode<K, V> node : nextNodes()) {
            if (node.isAccepting()) {
                return true;
            }
        }
        return false;
    }

    /**
     * Return a collection of next nodes available from this node.
     *
     * @return A collection of direct children from this node.
     */
    protected abstract Collection<? extends AbstractTrieMapUsingMapNode<K, V>> nextNodes();

}