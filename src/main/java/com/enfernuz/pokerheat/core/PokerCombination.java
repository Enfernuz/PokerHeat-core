package com.enfernuz.pokerheat.core;

import java.util.Objects;

import com.google.common.collect.*;

import static java.util.Objects.*;

import static com.google.common.base.Preconditions.*;
import java.util.Collection;

/**
 *
 * @author A. Nerushev
 */
public class PokerCombination {
    
    public static final int COMBINATION_SIZE = 5;

    private CombinationType type;
    private Collection<Card> cards;
    
    public PokerCombination() {}
    
    public PokerCombination(CombinationType type, Collection<Card> cards) {

        this.type = requireNonNull(type, "The parameter 'combinationType' must not be null.");
        this.cards = requireNonNull(cards, "The parameter 'cards' must not be null.");
        
        checkArgument( cards.size() == COMBINATION_SIZE, String.format("The collection parameter 'cards' must be the size of %d.", COMBINATION_SIZE) );
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(type, cards);
    }
    
    @Override
    public boolean equals(Object obj) {
        
        if (obj == this) {
            return true;
        } else if( !(obj instanceof PokerCombination) ) {
            return false;
        } else {
            
            final PokerCombination other = (PokerCombination) obj;
            return Objects.equals(type, other.type)
                    && Objects.equals(cards, other.cards);
        }
    }

    public CombinationType getType() {
        return type;
    }
    
    public Collection<Card> getCards() {
        return cards;
    }

    public void setType(CombinationType type) {
        this.type = type;
    }

    public void setCards(ImmutableCollection<Card> cards) {
        this.cards = cards;
    }
    
    public enum CombinationType {

        HIGH_CARD,
        PAIR,
        TWO_PAIRS,
        THREE_OF_A_KIND,
        STRAIGHT,
        FLUSH,
        FULL_HOUSE,
        FOUR_OF_A_KIND,
        FLUSH_STRAIGHT,
        ROYAL_FLUSH;

    }
}
