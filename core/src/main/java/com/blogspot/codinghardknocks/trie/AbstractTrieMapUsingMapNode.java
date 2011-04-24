package com.blogspot.codinghardknocks.trie;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import com.blogspot.codinghardknocks.annotations.NotThreadSafe;

/**
 * A base node implementation which uses a Map for computing the traversal
 * to the next state.  This implementation has a final field {@link #next},
 * where it would likely be more memory efficient to only allocate the next
 * once a traversal state is added.
 *
 * @param <K> The type of input used during the traversal.
 * @param <V> The type of value associated with this node.
 *
 * @author kbohling
 */
@NotThreadSafe
public class AbstractTrieMapUsingMapNode<K, V> extends AbstractTrieMapNode<K, V> {

    private final Map<K, AbstractTrieMapUsingMapNode<K, V>> next;

    /**
     * Constructor.
     *
     * @param accepting <code>true</code> if ending on this node corresponds
     * with a value added to the trie, <code>false</code> otherwise.
     * @param value The value to associate with this node.
     */
    public AbstractTrieMapUsingMapNode(boolean accepting, V value) {
        super(accepting, value);
        next = createMap();
    }

    @Override
    public void clear() {
        super.clear();
        next.clear();
    }

    /**
     * This is intended to be overridden by subclasses to chose optimal
     * implementations for the map function.
     *
     * @return A newly created map with the appropriate properties.
     */
    protected Map<K, AbstractTrieMapUsingMapNode<K, V>> createMap() {
        return new HashMap<K, AbstractTrieMapUsingMapNode<K, V>>();
    }

    @Override
    public TrieMapNode<K, V> next(K input) {
        return next.get(input);
    }

    @Override
    public TrieMapNode<K, V> addTransition(K input) {
        AbstractTrieMapUsingMapNode<K, V> node = next.get(input);

        if (node != null) {
            return node;
        }

        node = new AbstractTrieMapUsingMapNode<K, V>(false, null);
        next.put(input, node);
        return node;
    }

    /**
     * Return a collection of next nodes available from this node.
     *
     * @return A collection of direct children from this node.
     */
    protected Collection<? extends AbstractTrieMapUsingMapNode<K, V>> nextNodes() {
        return Collections.unmodifiableCollection(next.values());
    }

}