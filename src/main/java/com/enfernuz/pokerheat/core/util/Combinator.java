package com.enfernuz.pokerheat.core.util;

import java.util.Collection;

import com.google.common.collect.ImmutableCollection;

/**
 *
 * @author A. Nerushev
 */

@FunctionalInterface
public interface Combinator<T> {

    Collection<Collection<T>> kCombinationsFromN(Collection<? extends T> elements, int k);
}
