package com.enfernuz.pokerheat.core;

import com.google.common.collect.ImmutableCollection;

/**
 *
 * @author A. Nerushev
 */
public interface HandExtractor<T extends Hand> {

    ImmutableCollection<T> getPossibleHandsFrom(CommonCardDeck deck);

}
