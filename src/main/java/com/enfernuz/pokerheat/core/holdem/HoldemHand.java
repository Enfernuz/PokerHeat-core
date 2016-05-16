package com.enfernuz.pokerheat.core.holdem;

import com.enfernuz.pokerheat.core.Card;
import com.enfernuz.pokerheat.core.Hand;
import java.util.Objects;

import com.google.common.collect.ImmutableSet;

import static com.google.common.base.Preconditions.*;

/**
 *
 * @author A. Nerushev
 */
public class HoldemHand implements Hand {

    public static final int HAND_SIZE;
    
    static {
        HAND_SIZE = 2;
    }
    
    private final ImmutableSet<Card> cards;
    
    private transient int hashCode;
    
    private HoldemHand(Card first, Card second) {
        
        this.cards = ImmutableSet.of(first, second);
    }
    
    private HoldemHand(ImmutableSet<Card> cards) {
        
        this.cards = cards;
    }
    
    public static HoldemHand of(Card first, Card second) {
        
        Objects.requireNonNull(first, "The parameter 'first' must not be null.");
        Objects.requireNonNull(second, "The parameter 'second' must not be null.");
        
        checkArgument(!Objects.equals(first, second), "The cards must not be equal.");
        
        return new HoldemHand(first, second);
    }
    
    public static HoldemHand of(ImmutableSet<Card> cards) {
        
        Objects.requireNonNull(cards, "The parameter 'cards' must not be null.");
        
        checkArgument(
                HAND_SIZE == cards.size(), 
                String.format("The size of the parameter 'cards' must be of %d.", HAND_SIZE)
        );
        
        return new HoldemHand(cards);
    }

    @Override
    public ImmutableSet<Card> getCards() {
        return cards;
    }
    
    @Override
    public boolean equals(Object obj) {
        
        if (obj == this) {
            return true;
        } else if ( !(obj instanceof HoldemHand) ) {
            return false;
        } else {
            
            final HoldemHand other = (HoldemHand) obj;
            return Objects.equals(cards, other.cards);
        }
    }
    
    @Override
    public int hashCode() {
        
        switch (hashCode) {
            case 0:
                hashCode = Objects.hash(cards);
            default:
                return hashCode;
        }
    }
}
