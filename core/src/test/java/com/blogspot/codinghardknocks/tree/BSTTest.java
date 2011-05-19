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

import static org.hamcrest.core.IsEqual.equalTo;
import static org.hamcrest.core.IsNull.notNullValue;
import static org.hamcrest.core.IsNull.nullValue;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import org.hamcrest.core.IsSame;
import org.junit.Test;


/**
 * Tests to validate BST.
 *
 * @author kbohling
 *
 */
public class BSTTest {


    @Test
    public void testEmptyTreeNullRoot() {
        BST<Integer> tree = new BST<Integer>();
        assertThat(tree.getRoot(), nullValue());
    }

    @Test
    public void testInitialInsert() {
        BST<Integer> tree = new BST<Integer>();
        tree.insert(10);
        assertThat(tree.getRoot(), notNullValue());
        assertThat(tree.getRoot().getKey(), equalTo(10));
        assertThat(tree.getRoot().getLeft(), nullValue());
        assertThat(tree.getRoot().getRight(), nullValue());
    }

    @Test
    public void testSecondLayerLeftInsert() {
        BST<Integer> tree = new BST<Integer>();
        tree.insert(10);
        tree.insert(5);

        assertThat(tree.search(5).getKey(), equalTo(5));
        assertThat(tree.getRoot().getLeft(), IsSame.sameInstance(tree.search(5)));
        assertThat(tree.getRoot(), IsSame.sameInstance(tree.search(5).getParent()));

        assertThat(tree.search(15), nullValue());
    }

    @Test
    public void testSecondLayerRightInsert() {
        BST<Integer> tree = new BST<Integer>();
        tree.insert(10);
        tree.insert(15);

        assertThat(tree.search(15).getKey(), equalTo(15));
        assertThat(tree.getRoot().getRight(), IsSame.sameInstance(tree.search(15)));
        assertThat(tree.getRoot(), IsSame.sameInstance(tree.search(15).getParent()));
        assertThat(tree.search(5), nullValue());
    }

    @Test
    public void testGetMin() {
        BST<Integer> tree = new BST<Integer>();
        tree.insert(10);
        tree.insert(15);
        tree.insert(5);
        tree.insert(0);

        BSTNode<Integer> curr = tree.getMin();
        assertThat(curr.getKey(), equalTo(0));
    }

    @Test
    public void testSuccessor() {
        BST<Integer> tree = new BST<Integer>();
        tree.insert(10);
        tree.insert(15);
        tree.insert(5);
        tree.insert(0);

        List<Integer> expected = Arrays.asList(0, 5, 10, 15);
        Iterator<Integer> it = expected.iterator();

        BSTNode<Integer> curr = tree.getMin();

        while (curr != null) {
            Integer actual = curr.getKey();
            assertThat(actual, equalTo(it.next()));
            curr = tree.successor(curr);
        }

        assertThat(curr, nullValue());
    }

    @Test
    public void testGetMax() {
        BST<Integer> tree = new BST<Integer>();
        tree.insert(10);
        tree.insert(15);
        tree.insert(5);
        tree.insert(0);

        BSTNode<Integer> curr = tree.getMax();
        assertThat(curr.getKey(), equalTo(15));
    }

    @Test
    public void testPredecessor() {
        BST<Integer> tree = new BST<Integer>();
        tree.insert(10);
        tree.insert(15);
        tree.insert(5);
        tree.insert(0);

        List<Integer> expected = Arrays.asList(15, 10, 5, 0);
        Iterator<Integer> it = expected.iterator();

        BSTNode<Integer> curr = tree.getMax();

        while (curr != null) {
            Integer actual = curr.getKey();
            assertThat(actual, equalTo(it.next()));
            curr = tree.successor(curr);
        }

        assertThat(curr, nullValue());
    }

    @Test
    public void testIsNull() {
        BST<Integer> tree = new BST<Integer>();

        assertTrue(tree.isNull(null));

        BSTNode<Integer> node = new BSTNode<Integer>(10);
        assertFalse(tree.isNull(node));
    }

    @Test
    public void testTestDeleteRootOnly() {
        BST<Integer> tree = new BST<Integer>();

        assertThat(tree.getRoot(), nullValue());
        tree.insert(10);

        assertThat(tree.getRoot(), notNullValue());
        tree.delete(10);
        assertThat(tree.getRoot(), nullValue());
    }

    @Test
    public void testTestDeleteGeneralCase() {
        BST<Integer> tree = new BST<Integer>();

        List<Integer> insertList = Arrays.asList(10, 15, 5, 0);

        for(Integer value : insertList) {
            tree.insert(value);
        }

        List<Integer> missing = new ArrayList<Integer>();
        List<Integer> inserted = new ArrayList<Integer>(insertList);

        while (!inserted.isEmpty()) {
            for (Integer m : missing) {
                assertThat(m + " should be removed", tree.search(m), nullValue());
            }

            for (Integer i : inserted) {
                assertThat(tree.search(i), notNullValue());
            }

            Integer deleteValue = inserted.get(0);
            missing.add(deleteValue);
            inserted.remove(deleteValue);
            tree.delete(deleteValue);
        }

        assertThat(tree.getRoot(), nullValue());
    }

    // XXX: Implement a random tree insertion/deletion and a walker which validates all invariants
    // after every operation.

}
