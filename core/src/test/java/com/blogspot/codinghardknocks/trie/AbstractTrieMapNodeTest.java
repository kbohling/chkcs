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

import org.junit.Assert;
import org.junit.Test;


/**
 * Unit tests to validate the behavior of {@link AbstractTrieMapNode}.
 *
 * @author kbohling
 *
 */
public class AbstractTrieMapNodeTest {


    @Test
    public void testConstructors() {
        AbstractTrieMapNode<Long, Object> testObj = createTest(false, null);
        Assert.assertTrue(!testObj.isAccepting());
        Assert.assertNull(testObj.value());

        Object obj = new Object();
        testObj = createTest(true, obj);
        Assert.assertTrue(testObj.isAccepting());
        Assert.assertSame(obj, testObj.value());


    }

    private static <K, V> AbstractTrieMapNode<K, V> createTest(boolean accepting, V value) {
        return new AbstractTrieMapNode<K, V>(accepting, value) {
            @Override
            public TrieMapNode<K, V> next(K input) {
                throw new UnsupportedOperationException();
            }

            @Override
            public TrieMapNode<K, V> addTransition(K input) {
                throw new UnsupportedOperationException();
            }

            @Override
            protected Collection<? extends AbstractTrieMapUsingMapNode<K, V>> nextNodes() {
                throw new UnsupportedOperationException();
            }
        };

    }
}
