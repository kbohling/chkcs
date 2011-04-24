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

import java.util.SortedMap;
import java.util.TreeMap;

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
public class StringTrieMap<V> {

    private Node<V> root;

    /**
     * Constructor.
     */
    public StringTrieMap() {
        root = new Node<V>();
    }

    public V addIfAbsent(String key, V value) {
        Node<V> node = walkAndAdd(key, root);
        if (!node.accepting) {
            node.accepting = true;
            node.setValue(value);
        }
        return node.value();
    }

    public void clear() {
        root.clear();
    }

    public boolean contains(String key) {
        Node<V> node = walk(key, root);
        return (node != null && node.accepting);
    }

    public boolean isEmpty() {
        return isEmpty(root);
    }

    private boolean isEmpty(Node<V> node) {
        if (node.accepting) {
            return false;
        }

        if (node.next == null) {
            return false;
        }

        for (Node<V> n : node.next.values()) {
            if (!isEmpty(n)) {
                return false;
            }
        }

        return true;
    }

    public V get(String key) {
        return get(key, null);
    }

    public V get(String key, V defaultValue) {
        Node<V> node = walk(key, root);
        if (node.accepting) {
            return node.value();
        }
        return defaultValue;
    }

    public int size() {
        return size(root);
    }

    private int size(Node<V> node) {
        int count = node.accepting ? 1 : 0;

        if (node.next == null) {
            return count;
        }

        for (Node<V> n : node.next.values()) {
            count += size(n);
        }

        return count;
    }

    private static <V> Node<V> walk(String key, Node<V> root) {
        int len = key.length();
        Node<V> curr = root;
        for (int i = 0; i < len; ++i) {
            curr = curr.next(key.charAt(i));
            if (null == curr) {
                return null;
            }
        }
        return curr;
    }

    private static <V> Node<V> walkAndAdd(String key, Node<V> root) {
        int len = key.length();
        Node<V> curr = root;
        for (int i = 0; i < len; ++i) {
            curr = curr.addIfAbsent(key.charAt(i));
        }
        return curr;
    }


    private static class Node<V> {
        private boolean accepting;
        private SortedMap<Character, Node<V>> next;
        private V value;

        public Node() {
        }

        private static <V> SortedMap<Character, Node<V>> createNext() {
            return new TreeMap<Character, Node<V>>();
        }

        public Node<V> addIfAbsent(char charAt) {
            if (null == next) {
                next = createNext();
                Node<V> node = new Node<V>();
                next.put(charAt, node);
                return node;
            }

            Node<V> node = next.get(charAt);
            if (node == null) {
                node = new Node<V>();
                next.put(charAt, node);
            }
            return node;
        }

        public void clear() {
            accepting = false;
            next = null;
            value = null;
        }

        public void setValue(V obj) {
            this.value = obj;
        }

        public V value() {
            return value;
        }

        public Node<V> next(char c) {
            if (next == null) {
                return null;
            }
            return next.get(c);
        }
    }


}
