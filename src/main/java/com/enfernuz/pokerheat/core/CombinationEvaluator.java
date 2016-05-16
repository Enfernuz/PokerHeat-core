package com.enfernuz.pokerheat.core;

import java.util.*;

import com.google.common.collect.ImmutableCollection;

/**
 *
 * @author A. Nerushev
 */
public interface CombinationEvaluator extends Comparator<PokerCombination> {

    PokerCombination evaluate(Collection<? extends Card> cards);
}
