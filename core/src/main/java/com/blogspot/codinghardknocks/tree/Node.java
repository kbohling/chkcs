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

import com.blogspot.codinghardknocks.annotations.NotThreadSafe;

/**
 * The interface for nodes.
 *
 * @author kbohling
 * @param <K> The type of node.
 *
 */
@NotThreadSafe
public interface Node<K extends Comparable<K>, N extends Node<K, N>> {
    N getParent();
    N getLeft();
    N getRight();

    void setParent(N v);
    void setLeft(N v);
    void setRight(N v);

    K getKey();
}
