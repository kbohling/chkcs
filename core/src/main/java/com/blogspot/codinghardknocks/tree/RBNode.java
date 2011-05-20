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

/**
 * Node used to represent Red-Black Tree nodes.
 *
 * @author kbohling
 */
public class RBNode<K extends Comparable<K>> extends AbstractBSTNode<K, RBNode<K>> {

    // XXX: Type safety snafu here, hence the icky casts.
    private static final RBNode<?> NIL = new RBNode<Integer>(0);

    /** The color of the node. */
    private RBTree.Color color;

    /**
     * Constructor.
     *
     * @param key The key associated with this node.
     */
    public RBNode(K key) {
        this(key, RBTree.Color.BLACK);
    }

    /**
     *
     * @param key
     * @param color
     */
    public RBNode(K key, RBTree.Color color) {
        super(key);
        this.color = color;
        this.setParent(RBNode.<K>getNil());
        this.setRight(RBNode.<K>getNil());
        this.setLeft(RBNode.<K>getNil());
    }

    /**
     * @return the color
     */
    public RBTree.Color getColor() {
        return color;
    }

    /**
     * @param color the color to set
     */
    public void setColor(RBTree.Color color) {
        this.color = color;
    }

    protected static <K extends Comparable<K>> RBNode<K> getNil() {
        @SuppressWarnings("unchecked")
        RBNode<K> tmp = (RBNode<K>) NIL;
        return tmp;
    }

}

