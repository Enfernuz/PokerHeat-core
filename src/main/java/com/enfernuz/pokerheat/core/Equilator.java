package com.enfernuz.pokerheat.core;

import com.google.common.collect.ImmutableMap;

/**
 *
 * @author A. Nerushev
 */
public interface Equilator<T extends Hand> {

    ImmutableMap<T, Equity> calculateEquities(Board board, T heroHand);
    
}