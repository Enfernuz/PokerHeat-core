package com.enfernuz.pokerheat.core;

import com.google.common.collect.ImmutableCollection;

/**
 *
 * @author A. Nerushev
 */
public interface Hand {
    
    ImmutableCollection<Card> getCards();
}
