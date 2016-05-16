package com.enfernuz.pokerheat.core;

import java.util.*;

import java.util.function.*;

import com.google.common.collect.*;

import static com.google.common.base.Preconditions.*;

/**
 *
 * @author A. Nerushev
 */
abstract class AbstractLimitedCollection<E> implements Consumer<E>, Supplier<E> {

    private final int limit;

    protected AbstractLimitedCollection(int limit) {

        checkArgument( limit > 0, "The parameter 'limit' must be greater than 0." );
        
        this.limit = limit;
    }
    
    protected abstract void addElement(E element);
    
    protected abstract E getElement();
    
    public abstract ImmutableCollection<E> getElements();
    
    public abstract boolean removeAll(E e);

    @Override
    public final void accept(E e) {
        
        checkState(
                !isFull(), 
                String.format("This bunch is already full (the limit of %d elements is reached).", limit) 
        );
        
        addElement(e);
    }

    @Override
    public final E get() {
        
        checkState(!isEmpty(), "This bunch is already empty (contains no elements).");

        return getElement();
    }

    public final boolean isFull() {
        return ( limit == getElements().size() );
    }
    
    public final boolean isEmpty() {
        return getElements().isEmpty();
    }

    public final int getLimit() {
        return limit;
    }
    
    public final int remaining() {
        return getElements().size();
    }
    
    @Override
    public String toString() {
        
        final StringBuilder strBuilder = new StringBuilder();
        getElements().forEach( strBuilder::append );
        
        return strBuilder.toString();
    }

}
