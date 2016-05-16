package com.enfernuz.pokerheat.core;

import com.google.common.collect.ImmutableCollection;

/**
 *
 * @author A. Nerushev
 */
public interface CardParser {

    ImmutableCollection<Card> parse(String str);
    
}
