package com.enfernuz.pokerheat.core;

import java.util.*;

import com.google.common.collect.*;

import static com.google.common.base.Preconditions.*;

/**
 *
 * @author A. Nerushev
 */
abstract class AbstractLimitedSet<E> extends AbstractLimitedCollection<E> {

    private final Set<E> elements;
    
    protected AbstractLimitedSet(int size) {
        
        super(size);
        
        elements = new LinkedHashSet<>();
    }
    
    protected AbstractLimitedSet(int size, ImmutableSet<? extends E> elements) {
        
        this(size);
        
        checkArgument( elements.size() == getLimit(), String.format("The set must consist of %d elements (passed %d).", getLimit(), elements.size() ) );
        
        this.elements.addAll(elements);
    }

    @Override
    protected final void addElement(E e) {
        
        checkState(!elements.contains(e), "The set already contains this element.");
        
        elements.add(e);
    }

    @Override
    protected final E getElement() {
        
        final Iterator<E> iterator = elements.iterator();
        final E e = iterator.next();
        elements.remove(e);
        
        return e;
    }

    @Override
    public final ImmutableCollection<E> getElements() {
        return ImmutableSet.copyOf(elements);
    }

    @Override
    public final boolean removeAll(E e) {
        return elements.removeIf( e::equals );
    }
    
}
