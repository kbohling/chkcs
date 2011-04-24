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

import java.util.Map;
import java.util.TreeMap;

import com.blogspot.codinghardknocks.annotations.NotThreadSafe;

@NotThreadSafe
public class SortedMapTrieMap<K, V>  extends AbstractTrieMap<K, V> {

    protected SortedMapTrieMap() {
        super(new TrieMapUsedSortedMapNode<K, V>(false, null));
    }

    private static class TrieMapUsedSortedMapNode<K, V> extends AbstractTrieMapUsingMapNode<K, V> {

        public TrieMapUsedSortedMapNode(boolean accepting, V value) {
            super(accepting, value);
        }

        @Override
        protected Map<K, AbstractTrieMapUsingMapNode<K, V>> createMap() {
            return new TreeMap<K, AbstractTrieMapUsingMapNode<K, V>>();
        }

    }
}

