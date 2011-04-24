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


/**
 * Class implements a <a href="http://en.wikipedia.org/wiki/Trie">Trie</a>
 * which associates a {@link CharSequence} with a specific value.  It is expected to primarily
 * be used with {@link String} objects, but only uses the {@link CharSequence} interface.
 *
 * @param <V> The type contained by the Trie.
 * @author kbohling
 *
 */
@NotThreadSafe
public class CharSequenceTrieMap<V> {


    /** The internal HashTrieMap used to implement Tries. */
    private final AbstractTrieMap<Character, V> charTrie;

    /**
     * Constructor.
     */
    public CharSequenceTrieMap() {
        charTrie = new SortedMapTrieMap<Character, V>();
    }

    public V addIfAbsent(CharSequence keys, V value) {
        return charTrie.addIfAbsent(iterator(keys), value);
    }

    public void clear() {
        charTrie.clear();
    }

    public boolean contains(CharSequence keys) {
        return charTrie.contains(iterator(keys));
    }

    public boolean isEmpty() {
        return charTrie.isEmpty();
    }

    public V get(CharSequence keys) {
        return get(keys, null);
    }

    public V get(CharSequence keys, V defaultValue) {
        return charTrie.get(iterator(keys), defaultValue);
    }

    public int size() {
        return charTrie.size();
    }

    private static Iterator<Character> iterator(final CharSequence input) {
        return new Iterator<Character>() {
            int currIndex = 0;

            @Override
            public boolean hasNext() {
                return currIndex < input.length();
            }

            @Override
            public Character next() {
                Character next = input.charAt(currIndex);
                ++currIndex;
                return next;
            }

            @Override
            public void remove() {
                throw new UnsupportedOperationException();
            }
        };
    }

}
